package cmd.commands.copy;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Command(
        name = "copy",
        description = "Copy a file",
        mixinStandardHelpOptions = true)
public class CopyCommand implements Runnable {

    @Parameters(index = "0", description = "path of the file to copy")
    private File sourceFile;

    @Parameters(index = "1", description = "path to copy file to")
    private File target;

    public CopyCommand() {
        /* intentionally empty */
    }

    @Override
    public void run() {
        try {
            Files.copy(sourceFile.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
