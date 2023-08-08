package cmd.commands.mkdir;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.io.IOException;

/**
 * Command to either add a file or directory which is specified by given parameters
 */
@Command(
        name = "mkdir",
        description = "Creates a file or directory",
        mixinStandardHelpOptions = true)
public class MkdirCommand implements Runnable {

    @Option(names = {"-f", "--file"}, description = "Add a file")
    private boolean isFile;

    @Option(names = {"-d", "--directory"}, description = "Add a directory")
    private boolean isDirectory;

    @Option(names = {"-n", "--name"}, required = true, description = "Name of the file or directory")
    private String name;

    private static final Logger LOG = LoggerFactory.getLogger(MkdirCommand.class);

    public MkdirCommand()
    {
        // do nothing here
    }

    @Override
    public void run() {
        try {
            createNewFileOrPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNewFileOrPath() throws IOException {
        if(isFile && isDirectory)
        {
            System.out.println("Cannot be both file and directory");
            return;
        }
        if(!isFile && !isDirectory)
        {
            System.out.println("Please specifiy if you're adding File -f or Directory -d");
        }

        final File newFileOrDirectory = new File(name);

        if(isFile)
        {
            if (newFileOrDirectory.exists())
            {
                System.out.println("This file already exists.");
            }
            else
            {
                try
                {
                    if (newFileOrDirectory.createNewFile())
                    {
                        System.out.println("File created");
                    }
                    else {
                        System.out.println("Failed to create file.");
                    }
                }
                catch (IOException ex)
                {
                    LOG.error(ex.getMessage());
                }
            }
        }

        if(isDirectory)
        {
            if(newFileOrDirectory.exists())
            {
                System.out.println("This directory already exists");
            }
            else
            {
                if (newFileOrDirectory.mkdir())
                {
                    System.out.println("Directory created");
                }
                else
                {
                    System.out.println("Failed to create directory.");
                }
            }
        }
    }
}
