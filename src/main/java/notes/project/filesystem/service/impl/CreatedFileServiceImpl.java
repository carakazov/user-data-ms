package notes.project.filesystem.service.impl;

import java.util.UUID;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.filesystem.dto.*;
import notes.project.filesystem.exception.ExceptionCode;
import notes.project.filesystem.exception.ResourceNotFoundException;
import notes.project.filesystem.file.FileManager;
import notes.project.filesystem.file.ZipManager;
import notes.project.filesystem.mapper.FileCreationMapper;
import notes.project.filesystem.mapper.ReadFileMapper;
import notes.project.filesystem.mapper.ReplacingHistoryMapper;
import notes.project.filesystem.model.CreatedFile;
import notes.project.filesystem.model.Directory;
import notes.project.filesystem.model.ReplacingHistory;
import notes.project.filesystem.repository.CreatedFileRepository;
import notes.project.filesystem.service.*;
import notes.project.filesystem.service.ObjectExistingStatusChanger;
import notes.project.filesystem.validation.Validator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatedFileServiceImpl implements CreatedFileService {
    private final CreatedFileRepository repository;
    private final FileManager fileManager;
    private final DirectoryService directoryService;
    private final FileCreationMapper fileCreationMapper;
    private final ClusterService clusterService;
    private final Validator<AddFileRequestDto> addFileValidator;
    private final DeleteHistoryService deleteHistoryService;
    private final ZipManager zipManager;
    private final ObjectExistingStatusChanger objectExistingStatusChanger;
    private final ReadFileMapper readFileMapper;
    private final ReplacingHistoryService replacingHistoryService;
    private final ReplacingHistoryMapper replacingHistoryMapper;
    private final ArchiveService archiveService;
    private final Validator<UpdateFileRequestDto> updateFileValidator;

    private final static Object LOCK = new Object();

    @Override
    @Transactional
    public AddFileResponseDto addFile(AddFileRequestDto request) {
        addFileValidator.validate(request);
        Directory directory = directoryService.findNotDeletedDirectoryByExternalId(request.getDirectoryExternalId());
        CreatedFile file = new CreatedFile();
        file.setDirectory(directory);
        file.setTitle(request.getTitle());
        fileManager.createFile(file, request.getContent());
        clusterService.updateClusterLastRequestedTime(directory.getCluster());
        return fileCreationMapper.to(repository.save(file));
    }

    @Override
    public CreatedFile findFileByExternalId(UUID externalId) {
        return repository.findByExternalId(externalId)
            .orElseThrow(() -> new ResourceNotFoundException(ExceptionCode.RESOURCE_NOT_FOUND));
    }

    @Override
    @Transactional
    public void deleteCreatedFile(UUID fileExternalId) {
        CreatedFile createdFile = findNotDeletedFileByExternalId(fileExternalId);
        deleteHistoryService.createCreatedFileDeleteHistory(createdFile);
        clusterService.updateClusterLastRequestedTime(createdFile.getDirectory().getCluster());
        synchronized(LOCK) {
            zipManager.zipCreatedFile(createdFile);
            objectExistingStatusChanger.changeCreatedFileExistingStatus(createdFile);
        }
    }

    @Override
    public ReadCreatedFileDto readFile(UUID externalId) {
        CreatedFile createdFile = findNotDeletedFileByExternalId(externalId);
        clusterService.updateClusterLastRequestedTime(createdFile.getDirectory().getCluster());
        return readFileMapper.to(createdFile, fileManager.readFile(createdFile));
    }

    @Override
    @Transactional
    public MoveCreatedFileResponseDto moveFile(MoveCreatedFileRequestDto request) {
        CreatedFile createdFile = findNotDeletedFileByExternalId(request.getCreatedFileExternalId());
        Directory directory = directoryService.findNotDeletedDirectoryByExternalId(request.getNewDirectoryExternalId());
        ReplacingHistory replacingHistory = replacingHistoryService.create(createdFile, directory);
        clusterService.updateClusterLastRequestedTime(createdFile.getDirectory().getCluster());
        synchronized(LOCK) {
            fileManager.moveFile(createdFile, directory);
            createdFile.setDirectory(directory);
        }
        return replacingHistoryMapper.from(replacingHistory);
    }

    @Override
    public CreatedFile findNotDeletedFileByExternalId(UUID externalId) {
        return repository.findByExternalIdAndDeletedFalse(externalId)
            .orElseThrow(() -> new ResourceNotFoundException(ExceptionCode.RESOURCE_NOT_FOUND));
    }

    @Override
    @Transactional
    public void updateFile(UUID externalId, UpdateFileRequestDto request) {
        updateFileValidator.validate(request);
        CreatedFile createdFile = findNotDeletedFileByExternalId(externalId);
        UUID versionFileId = UUID.randomUUID();
        archiveService.create(createdFile, versionFileId);
        clusterService.updateClusterLastRequestedTime(createdFile.getDirectory().getCluster());
        synchronized(LOCK) {
            zipManager.zipFileForUpdate(createdFile, versionFileId);
            fileManager.updateFile(createdFile, request.getContent());
        }
    }
}
