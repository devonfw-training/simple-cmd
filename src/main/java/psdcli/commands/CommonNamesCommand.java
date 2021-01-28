package psdcli.commands;

import picocli.CommandLine.Command;

@Command(description = "return list 3 top used names", name = "top", aliases = {"t"},
        mixinStandardHelpOptions = true)
public class CommonNamesCommand implements Runnable {

    public CommonNamesCommand() {
        super();
    }

    @Override
    public void run() {
        System.out.println("Commonly used names are:");
        System.out.println("Bred");
        System.out.println("Helga");
        System.out.println("Kai");
    }
}
