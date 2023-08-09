package cmd.commands.copy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * "Copy File" command class
 * <p/>
 * Executing the command copies a file from one point to another.
 * If the file already exists in the target destination, the existing file is overwritten.
 */
@Command(
        name = "copy",
        description = "Copy a file",
        mixinStandardHelpOptions = true)
public class CopyCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(CopyCommand.class);

    @Parameters(index = "0", description = "path of the file to copy")
    private File source;

    @Parameters(index = "1", description = "path to copy file to")
    private File target;

    public CopyCommand() {
        /* intentionally empty */
    }

    @Override
    public void run() {
        try {
            if (!target.exists()) {
                copyFile();
                return;
            }

            if (target.exists() && getConfirmation()) {
                copyFile();
                return;
            }

            LOG.info("File was NOT copied\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFile() throws IOException {
        Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        LOG.info("File was copied\n");
    }

    private boolean getConfirmation() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        LOG.info("Do you want replacing existing file y/n:");
        String input = reader.readLine();
        return input.contains("y");
    }
}
