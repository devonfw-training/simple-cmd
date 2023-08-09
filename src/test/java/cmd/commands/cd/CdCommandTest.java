package cmd.commands.cd;

import cmd.SimpleCmd;
import cmd.commands.AbstractCommandTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CdCommandTest extends AbstractCommandTest {
    @Test
    void testCdWithoutArguments(@TempDir Path tempDir) {
        // given
        Path currentLocation = tempDir.toAbsolutePath();
        SimpleCmd.setCurrentLocation(currentLocation.toFile());
        String[] args = {};
        CdCommand cdCommand = CommandLine.populateCommand(new CdCommand(), args);
        // when
        cdCommand.run();
        // then
        String expected = currentLocation.toString();
        String actual = SimpleCmd.getCurrentLocation().getAbsolutePath();
        assertEquals(expected, actual, "Expected : " + expected + " But was: " + actual);
    }

    @Test
    void testCdWithFile(@TempDir Path tempDir) {
        // given
        Path currentLocation = tempDir.toAbsolutePath();
        SimpleCmd.setCurrentLocation(currentLocation.toFile());
        String[] args = {"/temp/temp.text"};
        CdCommand cdCommand = CommandLine.populateCommand(new CdCommand(), args);
        // when
        cdCommand.run();
        // then
        String expected = currentLocation.toString();
        String actual = SimpleCmd.getCurrentLocation().getAbsolutePath();
        assertEquals(expected, actual, "Expected : " + expected + " But was: " + actual);
    }

    @Test
    void testCdWithRelativeDirPath(@TempDir Path tempDir) {
        // given
        Path currentLocation = tempDir.toAbsolutePath();
        SimpleCmd.setCurrentLocation(currentLocation.toFile());
        String[] args = {"./temp"};
        CdCommand cdCommand = CommandLine.populateCommand(new CdCommand(), args);
        // when
        cdCommand.run();
        // then
        String expected = currentLocation.toString();
        String actual = SimpleCmd.getCurrentLocation().getAbsolutePath();
        assertEquals(expected, actual, "Expected : " + expected + " But was: " + actual);
    }

    @Test
    void testCdWithAbsoluteDirPath(@TempDir Path tempDir) throws IOException {
        // given
        Path currentLocation = tempDir.toAbsolutePath();
        SimpleCmd.setCurrentLocation(currentLocation.toFile());
        String[] args = {tempDir.resolve("temp").toAbsolutePath().toString()};
        Files.createDirectory(tempDir.resolve("temp"), noAttributes);
        CdCommand cdCommand = CommandLine.populateCommand(new CdCommand(), args);
        // when
        cdCommand.run();
        // then
        String expected = args[0];
        String actual = SimpleCmd.getCurrentLocation().getAbsolutePath();
        assertEquals(expected, actual, "Expected : " + expected + " But was: " + actual);
    }
}