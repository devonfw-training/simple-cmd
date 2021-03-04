package cmd.commands.hello;

import picocli.CommandLine.Command;

@Command(description = "return list 3 top used names", name = "top",
        mixinStandardHelpOptions = true)
public class CommonNamesCommand implements Runnable {

    public CommonNamesCommand() {
    }

    @Override
    public void run() {
        System.out.println("Commonly used names are:");
        System.out.println("Bred, Helga, Kai");
    }
}
