package cmd.commands.find;


import java.io.File;
import java.io.FileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cmd.SimpleCmd;
import cmd.commands.dir.DirCommand;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;



/**
 * "find" command class
 * <p/>
 * Executing the command find to get all files with a specific extension in the current directory.
 *
 * @see SimpleCmd#getCurrentLocation()
 */
@Command(name = "find", description = "Find file of a given file type in the current directory",
    mixinStandardHelpOptions = true)
public class FindCommand implements Runnable{

  private static final Logger LOG = LoggerFactory.getLogger(DirCommand.class);


  @Parameters(index = "0", description = "The file extension to search for (without the dot).")
  private String fileExtension;

  public FindCommand(){
    /* intentionally empty */
  }

  @Override
  public void run() {

    File directory = SimpleCmd.getCurrentLocation();
    System.out.println();
    //search files
    File[] foundFiles = directory.listFiles(new FileFilter() {
      @Override
      public boolean accept(File file) {
        return file.isFile() && file.getName().endsWith("." + fileExtension);
      }
    });

    if(foundFiles != null && foundFiles.length > 0) {
      //Print files to standard output
      for (File f : foundFiles) {
        System.out.println(f.getPath().toString());
      }
    }
    else {
      System.err.println("No files found in the current directory!");
    }
  }
}
