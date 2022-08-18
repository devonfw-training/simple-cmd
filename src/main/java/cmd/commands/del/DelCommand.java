package cmd.commands.del;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;

@Command(
        name = "del",
        description = "command can be used to delete files",
        mixinStandardHelpOptions = true)
public class DelCommand implements Runnable {

    @Parameters(index = "0", description = "path of the file to delete")
    private File file;

    public DelCommand() {
        /* intentionally empty */
    }

    @Override
    public void run() {
        String absolutePath = file.getAbsolutePath();
        boolean delete = file.delete();
        System.out.println(absolutePath + "was " + (delete ? "sucessefully" : "not") + " deleted");
    }
}
