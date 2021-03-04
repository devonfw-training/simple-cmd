package cmd.commands.hello;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
        name = "hello",
        description = "Says hello",
        mixinStandardHelpOptions = true,
        subcommands = {CommonNamesCommand.class})
public class HelloCommand implements Runnable {

    @Option(names = {"-A", "--all"})
    private boolean allNames;
    @Option(names = {"-N", "--name"}, required = true)
    private String name;

    public HelloCommand() {
    }

    @Override
    public void run() {

        System.out.println("Hello " + name + "! nice to meet you.");

        if (allNames) {
            System.out.println("You wanted to see all names that I know.");
            System.out.println("Kevin, Hans, Gretel, Voldemort");
        }
    }
}
