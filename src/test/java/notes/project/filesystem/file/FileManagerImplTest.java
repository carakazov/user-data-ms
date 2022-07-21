package notes.project.filesystem.file;

import notes.project.filesystem.file.impl.FileManagerImpl;
import notes.project.filesystem.model.CreatedFile;
import notes.project.filesystem.utils.DbUtils;
import notes.project.filesystem.utils.PathHelper;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static notes.project.filesystem.utils.TestDataConstants.*;

@ExtendWith(MockitoExtension.class)
class FileManagerImplTest {
    @Mock
    private PathHelper pathHelper;

    private FileManager fileManager;

    @BeforeEach
    void init() {
        fileManager = new FileManagerImpl(pathHelper);
    }

    @Test
    void createClusterSuccess() throws IOException {
        when(pathHelper.createPathToCluster(any())).thenReturn(RESOLVED_PATH_FOR_CREATE_CLUSTER);
        fileManager.createCluster(DbUtils.cluster());
        assertFileCreated(RESOLVED_PATH_FOR_CREATE_CLUSTER);

        verify(pathHelper).createPathToCluster(DbUtils.cluster());
    }

    @Test
    void createDirectorySuccess() throws IOException {
        createClusterPath(RESOLVED_PATH_FOR_CREATE_CLUSTER);
        when(pathHelper.createPathToDirectory(any())).thenReturn(RESOLVED_PATH_FOR_CREATE_DIRECTORY);
        when(pathHelper.createPathToCluster(any())).thenReturn(RESOLVED_PATH_FOR_CREATE_CLUSTER);
        fileManager.createDirectory(DbUtils.directory());
        assertFileCreated(RESOLVED_PATH_FOR_CREATE_DIRECTORY);

        verify(pathHelper).createPathToDirectory(DbUtils.directory());
    }

    @Test
    void createFileSuccess() throws IOException{
        when(pathHelper.createPathToFile(any())).thenReturn(RESOLVED_PATH_FOR_CREATE_FILE);
        when(pathHelper.createPathToDirectory(any())).thenReturn(RESOLVED_PATH_FOR_CREATE_DIRECTORY);
        CreatedFile file = DbUtils.createdFile();
        Files.createDirectories(RESOLVED_PATH_FOR_CREATE_DIRECTORY);
        fileManager.createFile(file, FILE_CONTENT);
        assertFileCreated(RESOLVED_PATH_FOR_CREATE_FILE);


        verify(pathHelper).createPathToFile(DbUtils.createdFile());
    }

    private void createClusterPath(Path path) throws IOException {
        Files.createDirectories(path);
    }

    private void assertFileCreated(Path path) throws IOException {
        assertTrue(Files.exists(path));
        FileUtils.deleteDirectory(new File(ROOT_DIRECTORY_PATH));
    }
}