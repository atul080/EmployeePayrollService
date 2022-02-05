package com.employeeservice;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class NIOFileAPITest {
    private static String Home = System.getProperty("user.home");
    private static String PLAY_WITH_NIO = "TempPlayGround";

    @Test
    public void givenPathWhenCheckedConfirm() throws IOException {
        // Check File Exists
        Path homePath = Paths.get(Home);
        Assertions.assertTrue(Files.exists(homePath));
        // Delete File and Check File Not Exist
        Path playPath = Paths.get(Home + "/" + PLAY_WITH_NIO);
        if (Files.exists(playPath)) {
            FileUtils.delete(playPath.toFile());
        }
        Assertions.assertTrue(Files.notExists(playPath));
        // Create Directory
        Files.createDirectory(playPath);
        Assertions.assertTrue(Files.exists(playPath));
        // Create File
        IntStream.range(1, 10).mapToObj(cntr -> Paths.get(playPath + "/temp" + cntr)).forEach(tempFile -> {
            Assertions.assertTrue(Files.notExists(tempFile));
            try {
                Files.createFile(tempFile);
            } catch (IOException e) {
            }
            Assertions.assertTrue(Files.exists(tempFile));
        });
        // List Files, Directories as well as Files with Extension
        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
        Files.newDirectoryStream(playPath).forEach(System.out::println);
        Files.newDirectoryStream(playPath, path -> path.toFile().isFile() && path.toString().startsWith("temp"))
        .forEach(System.out::println);
    }
}
