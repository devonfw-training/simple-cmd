package cmd.commands.dir;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmd.SimpleCmd;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/**
 * "List Directory" command class
 * <p/>
 * Executing the command lists all the files and folders in the current working directory.
 *
 * @see SimpleCmd#getCurrentLocation()
 * @see SimpleCmd#setCurrentLocation(File)
 */
@Command(name = "dir", description = "Displays files and directories of current working directory", mixinStandardHelpOptions = true)
public class DirCommand implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(DirCommand.class);

  @Option(names = { "-f", "--files" }, description = "print only file names, directories will not be listed")
  private boolean filesOnly;

  @Option(names = { "-s", "--sort" }, description = "possible values are {asc, desc} for ascending / descending order")
  private String sortOrder;

  @Parameters(index = "0", description = "path of the directory", arity = "0..1")
  private File file;

  /**
   * The constructor.
   */
  public DirCommand() {

    /* intentionally empty */
  }

  @Override
  public void run() {

    if (this.file == null) {
      listFilesInDirectory(SimpleCmd.getCurrentLocation());
    } else {
      listFilesInDirectory(this.file);
    }
  }

  private void listFilesInDirectory(File directory) {

    if (directory.isDirectory()) {
      File[] files = directory.listFiles();
      if (null != files) {
        List<File> dateien = Stream.of(files).sorted(getFileListComparator()).collect(Collectors.toList());
        String type;
        int maximaleLaenge = 0;
        for (File datei : dateien) {
          maximaleLaenge = Math.max(maximaleLaenge, datei.toString().length());
        }
        maximaleLaenge += 2;
        System.out.println(String.format("%-" + maximaleLaenge + "s %s\n", "Path", "Type"));
        for (File datei : dateien) {
          type = datei.isFile() ? "file" : "directory";
          System.out.println(String.format("%-" + maximaleLaenge + "s %s\n", datei.toString(), type));
        }

      }
    } else {
      LOG.info("The path '{}' does not exist.\n", directory.toString());
    }
  }

  private Comparator<String> getSortOrderAwareFileNameComparator(final String sortOrder) {

    if ("desc".equals(sortOrder)) {
      return String::compareTo;
    } else if ("asc".equals(sortOrder)) {
      return Comparator.reverseOrder();
    } else {
      return (fileName1, fileName2) -> 0;
    }
  }

  private Comparator<File> getFileListComparator() {

    return Comparator.comparing(File::getName, getSortOrderAwareFileNameComparator(this.sortOrder));
  }

  private void printLine(File f) {

    if (this.filesOnly) {
      if (!f.isDirectory()) {
        LOG.info("{}\n", f.getAbsolutePath());
      }
    } else {
      LOG.info("{}\n", f.getAbsolutePath());
    }
  }
}
