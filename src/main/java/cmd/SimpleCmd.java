package cmd;

import cmd.commands.BaseCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Starting point of the Simple CMD. Contains the main method.
 */
public class SimpleCmd {

  /**
   * Logger to output useful information to the user
   */
  private static final Logger LOG = LoggerFactory.getLogger(SimpleCmd.class);

  /**
   * register the {@link BaseCommand} in the new {@link CommandLine} Object
   */
  public static final CommandLine commandLine = new CommandLine(new BaseCommand());
  private static File currentLocation = new File(".");


  /**
   * Main starting point of the CLI. Here we parse the arguments from the user.
   *
   * @param args list of arguments the user has passed
   */
  public static void main(String... args) {
    try (Scanner scanner = new Scanner(System.in)) {
      while (true) {
        System.out.println("Please input a line");
        long then = System.currentTimeMillis();
        String line = scanner.nextLine();
        long now = System.currentTimeMillis();
        System.out.printf("Waited %.3fs for user input%n", (now - then) / 1000d);
        System.out.printf("User input was: %s%n", line);

        if (line.equals("exit")) {
          break;
        }

        String[] arguments = line.split(" ");

        LOG.debug("Current working directory: {}", System.getProperty("user.dir"));

        if (commandLine.execute(arguments) == 0) {
          LOG.debug("Commands were executed correctly");
        }
      }
    } catch (IllegalStateException | NoSuchElementException e) {
      // System.in has been closed
      System.out.println("System.in was closed; exiting");
    }
  }

  public static File getCurrentLocation() {
    return currentLocation;
  }

  public static void setCurrentLocation(File currentLocation) {
    SimpleCmd.currentLocation = currentLocation;
  }
}
