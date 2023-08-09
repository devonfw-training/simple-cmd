package cmd.commands.dir;

import cmd.SimpleCmd;
import cmd.commands.AbstractCommandTest;
import cmd.commands.mkdir.MkdirCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MkdirCommandTest extends AbstractCommandTest {

    private Path tempDir;

    @BeforeEach
    public void setUp() throws IOException
    {
        tempDir = Files.createTempDirectory("temp-dir");
        prepareTestFolder();
    }

    @AfterEach
    public void cleanup() throws IOException {
        // Delete the temporary directory after each test
        if (tempDir != null) {
            deleteDirectory(tempDir);
        }
    }

    @Test
    public void testCreateNewFile()
    {
        // given
        SimpleCmd.setCurrentLocation(tempDir.toFile());
        String[] args = {"--name=MyTestFile.txt",  "--file"};
        MkdirCommand dirCommand = CommandLine.populateCommand(new MkdirCommand(), args);

        // when
        dirCommand.run();

        // then
        String expected = "File created";
        String actual = getOutputStream().toString();
        assertTrue(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
    }

    @Test
    public void testCreateNewDirectory()
    {
        // given
        SimpleCmd.setCurrentLocation(tempDir.toFile());
        String[] args = {"--name=MyTestDirectory",  "--directory"};
        MkdirCommand dirCommand = CommandLine.populateCommand(new MkdirCommand(), args);

        // when
        dirCommand.run();

        // then
        String expected = "Directory created";
        String actual = getOutputStream().toString();
        assertTrue(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
    }

    @Test
    public void testCreateWithoutSpecifiedParams()
    {
        // given
        SimpleCmd.setCurrentLocation(tempDir.toFile());
        String[] args = {"--name=MyTestName"};
        MkdirCommand dirCommand = CommandLine.populateCommand(new MkdirCommand(), args);

        // when
        dirCommand.run();

        // then
        String expected = "Please specifiy if you're adding File -f or Directory -d";
        String actual = getOutputStream().toString();
        assertTrue(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
    }

    @Test
    public void testCreateWithDoubleSpecifiedParams()
    {
        // given
        SimpleCmd.setCurrentLocation(tempDir.toFile());
        String[] args = {"--name=MyTestName", "-d", "-f"};
        MkdirCommand dirCommand = CommandLine.populateCommand(new MkdirCommand(), args);

        // when
        dirCommand.run();

        // then
        String expected = "Cannot be both file and directory";
        String actual = getOutputStream().toString();
        assertTrue(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
    }

    private void prepareTestFolder() throws IOException {
        // for other possible usages of @TempDir see https://www.baeldung.com/junit-5-temporary-directory
        Path myFile = tempDir.resolve("myFile.txt");
        Files.write(myFile, Collections.singletonList(""));

        Path folder1 = tempDir.resolve("folder1");
        Path directory = Files.createDirectory(folder1, noAttributes);

        Path myFile2 = directory.resolve("myFile2.txt");
        Files.write(myFile2, Collections.singletonList(""));
    }

    private void deleteDirectory(Path path) throws IOException {
        Files.walk(path)
                .sorted((a, b) -> -a.compareTo(b))
                .forEach(file -> {
                    try {
                        Files.delete(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
