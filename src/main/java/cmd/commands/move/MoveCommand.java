package cmd.commands.move;

import picocli.CommandLine;

import java.io.File;

/**
 * Moves a File or Directory and gives the option to give a new name.
 */
@CommandLine.Command(
        name = "move",
        description = "Moves a file or directory to a new path",
        mixinStandardHelpOptions = true)
public class MoveCommand implements Runnable {

    @CommandLine.Parameters(index = "0", description = "Source file/directory to move")
    private String sourcePath;

    @CommandLine.Parameters(index = "1", description = "Destination path to move to")
    private String destinationPath;

    @CommandLine.Option(names = {"-n", "--newname"}, description = "New name for the moved file/directory")
    private String newName;

    @Override
    public void run() {
        moveOrRename();
    }

    private void moveOrRename()
    {
        File source = new File(sourcePath);
        File destination = new File(destinationPath);

        if (!source.exists()) {
            System.out.println("Source does not exist.");
            return;
        }

        if (!destination.exists()) {
            System.out.println("Destination directory does not exist.");
            return;
        }

        File newLocation;
        if (newName != null) {
            newLocation = new File(destination, newName);
        } else {
            newLocation = new File(destination, source.getName());
        }

        if (source.renameTo(newLocation)) {
            System.out.println("File/directory moved successfully.");
        } else {
            System.out.println("Failed to move file/directory.");
        }
    }
}
