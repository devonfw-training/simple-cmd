package cmd.commands.del;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * "Delete File" command class
 * <p/>
 * Executing the command deletes a file, which is denoted by the parameter.
 */
@Command(
        name = "del",
        description = "Delete a file",
        mixinStandardHelpOptions = true)
public class DelCommand implements Runnable {

    public static final Logger LOG = LoggerFactory.getLogger(DelCommand.class);

    @Parameters(index = "0", description = "path of the file to delete")
    private File file;

    public DelCommand() {
        /* intentionally empty */
    }

    @Override
    public void run() {
        String absolutePath = file.getAbsolutePath();
        boolean delete = false;
        try {
            delete = Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOG.info("{} was {} deleted\n", absolutePath, (delete ? "successfully" : "not"));
    }
}
