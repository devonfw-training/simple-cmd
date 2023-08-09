package cmd.commands.move;

import cmd.SimpleCmd;
import cmd.commands.AbstractCommandTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveCommandTest extends AbstractCommandTest {

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
    void testSourceDoesntExist()
    {
        SimpleCmd.setCurrentLocation(tempDir.toFile());
        final File currentLocation = SimpleCmd.getCurrentLocation();
        System.out.println(SimpleCmd.getCurrentLocation().toString());
        String[] args = {"", currentLocation.toString(), "-n=myFile3.txt"};
        MoveCommand moveCommand = CommandLine.populateCommand(new MoveCommand(), args);

        moveCommand.run();

        String expected = "Source does not exist";
        String actual = getOutputStream().toString();
        assertTrue(actual.contains(expected));
    }

    private void prepareTestFolder() throws IOException {
        Path myFile = tempDir.resolve("myFile.txt");
        Files.write(myFile, Collections.singletonList(""));

        Path folder1 = tempDir.resolve("folder1");
        Path directory = Files.createDirectory(folder1, noAttributes);

        Path folder2 = tempDir.resolve("folder2");
        Files.createDirectory(folder2, noAttributes);

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
