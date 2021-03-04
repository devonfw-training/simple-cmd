package cmd.commands;

import cmd.commands.copy.CopyCommand;
import cmd.commands.del.DelCommand;
import cmd.commands.dir.DirCommand;
import cmd.commands.hello.HelloCommand;
import picocli.CommandLine.Command;

/**
 * this is the basic Commmand object used to register all other Commands via subcommands parameter in @Command annotation
 */
@Command(
        name = "cmd",
        description = "base command",
        mixinStandardHelpOptions = true,
        subcommands = {HelloCommand.class, DirCommand.class, DelCommand.class, CopyCommand.class})
public class BaseCommand implements Runnable {
    public BaseCommand() {
    }

    @Override
    public void run() {
        // nothing to do here
    }

}
