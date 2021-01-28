package psdcli.commands;

import picocli.CommandLine.Command;

@Command(
        name = "psdcli",
        aliases = {"psd"},
        description = "base command",
        mixinStandardHelpOptions = true,
        subcommands = {HelloWorldCommand.class, DirCommand.class})
public class BaseCommand implements Runnable {
    @Override
    public void run() {
        // nothing to do here
    }

}
