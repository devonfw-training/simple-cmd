package cmd.commands.copy;

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

public class CopyCommandTest extends AbstractCommandTest {
    @Test
    void testWithNonExistingFile(@TempDir Path tempDir) throws IOException {
        // given
        prepareTestFolder(tempDir);
        SimpleCmd.setCurrentLocation(tempDir.toFile());
        String[] args = {tempDir.resolve("./myFile.txt").toFile().getAbsolutePath(), tempDir.resolve("./myFile2.txt").toFile().getAbsolutePath()};
        CopyCommand dirCommand = CommandLine.populateCommand(new CopyCommand(), args);
        // when
        dirCommand.run();
        // then
        assertTrue(tempDir.resolve("./myFile2.txt").toFile().exists());
    }

    private void prepareTestFolder(@TempDir Path tempDir) throws IOException {
        // for other possible usages of @TempDir see https://www.baeldung.com/junit-5-temporary-directory
        Path myFile = tempDir.resolve("myFile.txt");
        Files.write(myFile, Collections.singletonList(""));
    }
}