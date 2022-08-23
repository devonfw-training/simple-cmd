package cmd.commands.dir;

import cmd.SimpleCmd;
import cmd.commands.AbstractCommandTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DirCommandTest extends AbstractCommandTest {

    @Test
    void testDirWithF(@TempDir Path tempDir) throws IOException {
        // given
        prepareTestFolder(tempDir);
        SimpleCmd.setCurrentLocation(tempDir.toFile());
        String[] args = {"-f"};
        DirCommand dirCommand = CommandLine.populateCommand(new DirCommand(), args);
        // when
        dirCommand.run();
        // then
        String expected = tempDir.toAbsolutePath() + File.separator + "myFile.txt";
        String actual = getOutputStream().toString();
        assertTrue(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
    }

    @Test
    void testDirWithoutArguments(@TempDir Path tempDir) throws IOException {
        // given
        prepareTestFolder(tempDir);
        SimpleCmd.setCurrentLocation(tempDir.toFile());
        String[] args = {};
        DirCommand dirCommand = CommandLine.populateCommand(new DirCommand(), args);
        // when
        dirCommand.run();
        // then
        String expected = tempDir.toAbsolutePath() + File.separator + "myFile.txt";
        String actual = getOutputStream().toString();
        assertTrue(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
    }

    private void prepareTestFolder(@TempDir Path tempDir) throws IOException {
        // for other possible usages of @TempDir see https://www.baeldung.com/junit-5-temporary-directory
        Path myFile = tempDir.resolve("myFile.txt");
        Files.write(myFile, Collections.singletonList(""));

        Path folder1 = tempDir.resolve("folder1");
        Path directory = Files.createDirectory(folder1, noAttributes);

        Path myFile2 = directory.resolve("myFile2.txt");
        Files.write(myFile2, Collections.singletonList(""));
    }
}