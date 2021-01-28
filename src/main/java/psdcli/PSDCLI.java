package psdcli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import psdcli.commands.BaseCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Starting point of the CobiGen CLI. Contains the main method.
 */
public class PSDCLI {

    /**
     * Logger to output useful information to the user
     */
    final private static Logger LOG = LoggerFactory.getLogger(PSDCLI.class);

    /**
     * @return the supported {@link CommandLine} objects
     */
    public static Collection<CommandLine> getCommands() {
        ArrayList<CommandLine> commandLines = new ArrayList<>();
        commandLines.add(new CommandLine(new BaseCommand()));
        return commandLines;
    }

    /**
     * Main starting point of the CLI. Here we parse the arguments from the user.
     *
     * @param args list of arguments the user has passed
     */
    public static void main(String... args) {
        boolean verbose = Arrays.asList(args).contains("-v");
        LOG.debug("Current working directory: {}", System.getProperty("user.dir"));

        getCommands().stream().findFirst().ifPresent(cl -> {
            if (cl.execute(args) == 0) {
                LOG.debug("Commands were executed correctly");
            }
        });

    }

}