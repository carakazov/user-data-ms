package notes.project.filesystem.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import notes.project.filesystem.dto.ClusterCreationRequestDto;
import notes.project.filesystem.dto.ClusterCreationResponseDto;
import notes.project.filesystem.exception.ExceptionCode;
import notes.project.filesystem.exception.ResourceNotFoundException;
import notes.project.filesystem.file.FileManager;
import notes.project.filesystem.file.ZipManager;
import notes.project.filesystem.mapper.ClusterCreationMapper;
import notes.project.filesystem.model.Cluster;
import notes.project.filesystem.repository.ClusterRepository;
import notes.project.filesystem.service.ClusterService;
import notes.project.filesystem.service.DeleteHistoryService;
import notes.project.filesystem.service.ObjectExistingStatusChanger;
import notes.project.filesystem.validation.Validator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ClusterServiceImpl implements ClusterService {
    private final ClusterRepository clusterRepository;
    private final FileManager fileManager;
    private final ClusterCreationMapper clusterCreationMapper;
    private final Validator<ClusterCreationRequestDto> createClusterValidator;
    private final DeleteHistoryService deleteHistoryService;
    private final ObjectExistingStatusChanger objectExistingStatusChanger;
    private final ZipManager zipManager;
    private final Validator<Cluster> deleteClusterValidator;

    private final static Object LOCK = new Object();

    @Override
    @Transactional
    public ClusterCreationResponseDto createCluster(ClusterCreationRequestDto request) {
        createClusterValidator.validate(request);
        Cluster cluster = clusterRepository.save(clusterCreationMapper.from(request));
        fileManager.createCluster(cluster);
        return clusterCreationMapper.to(cluster);
    }

    @Override
    public Cluster findByExternalId(UUID clusterExternalId) {
        return clusterRepository.findByExternalId(clusterExternalId)
            .orElseThrow(() -> new ResourceNotFoundException(ExceptionCode.RESOURCE_NOT_FOUND));
    }

    @Override
    @Transactional
    public void updateClusterLastRequestedTime(Cluster cluster) {
        cluster.setLastRequestDate(LocalDateTime.now());
    }

    @Override
    @Transactional
    public void deleteCluster(UUID externalId) {
        Cluster cluster = findByExternalId(externalId);
        deleteClusterValidator.validate(cluster);
        deleteHistoryService.createClusterDeleteHistory(cluster);
        synchronized(LOCK) {
            zipManager.zipCluster(cluster);
            objectExistingStatusChanger.changeClusterExistingStatus(cluster);
        }
    }
}
