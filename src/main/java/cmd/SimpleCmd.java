package cmd;

import cmd.commands.BaseCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Starting point of the Simple CMD. Contains the main method.
 */
public class SimpleCmd {

    /**
     * Logger to output useful information to the user
     */
    final private static Logger LOG = LoggerFactory.getLogger(SimpleCmd.class);

    /**
     * register the {@link BaseCommand} in the new {@link CommandLine} Object
     */
    public static CommandLine commandLine = new CommandLine(new BaseCommand());

    /**
     * Main starting point of the CLI. Here we parse the arguments from the user.
     *
     * @param args list of arguments the user has passed
     */
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Please input a line");
                long then = System.currentTimeMillis();
                String line = scanner.nextLine();
                long now = System.currentTimeMillis();
                System.out.printf("Waited %.3fs for user input%n", (now - then) / 1000d);
                System.out.printf("User input was: %s%n", line);
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            // System.in has been closed
            System.out.println("System.in was closed; exiting");
        }

        boolean verbose = Arrays.asList(args).contains("-v");
        LOG.debug("Current working directory: {}", System.getProperty("user.dir"));

        if (commandLine.execute(args) == 0) {
            LOG.debug("Commands were executed correctly");
        }


    }

}