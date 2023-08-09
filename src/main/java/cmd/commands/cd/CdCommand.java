package cmd.commands.cd;

import cmd.SimpleCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.*;

import java.io.File;

/**
 * "Copy File" command class
 * <p/>
 * Executing the command copies a file from one point to another.
 * If the file already exists in the target destination, the existing file is overwritten.
 */
@Command(
        name = "cd",
        description = "Changes the current working directory",
        mixinStandardHelpOptions = true)
public class CdCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(CdCommand.class);

    @Parameters(index = "0", description = "path of the new working directory", arity = "0..1")
    private String path;

    public CdCommand() {
    }

    @Override
    public void run() {
        if (path != null) {
            File directory = new File(path);
            changeDir(directory);
        } else {
            LOG.info(String.format("Current working directory: %s\n", SimpleCmd.getCurrentLocation().getAbsolutePath()));
        }
    }

    private void changeDir(File directory) {
        if (!directory.isAbsolute()) {
            LOG.error(String.format("%s is not an absoulte path\n", directory.getPath()));
            return;
        }
        if (!directory.isDirectory()) {
            LOG.error(String.format("%s is not a directory\n", directory.getAbsolutePath()));
            return;
        }
        SimpleCmd.setCurrentLocation(directory);
        LOG.info(String.format("Current working directory: %s\n", SimpleCmd.getCurrentLocation().getAbsolutePath()));
    }
}