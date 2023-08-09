package cmd.commands.dir;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<List<String>> rows = new ArrayList<>();
        List<String> headers = Arrays.asList("Path", "Type");
        rows.add(headers);
        String type;
        for (File datei : dateien) {
          type = datei.isFile() ? "file" : "directory";
          rows.add(Arrays.asList(datei.toString(), type));
        }

        System.out.println(formatAsTable(rows));
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

  /**
   * Formatiere Ausgabe wie Tabelle
   */
  public static String formatAsTable(List<List<String>> rows) {

    int[] maxLengths = new int[rows.get(0).size()];
    for (List<String> row : rows) {
      for (int i = 0; i < row.size(); i++) {
        maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
      }
    }

    StringBuilder formatBuilder = new StringBuilder();
    for (int maxLength : maxLengths) {
      formatBuilder.append("%-").append(maxLength + 2).append("s");
    }
    String format = formatBuilder.toString();

    StringBuilder result = new StringBuilder();
    for (List<String> row : rows) {
      result.append(String.format(format, row.toArray(new String[0]))).append("\n");
    }
    return result.toString();
  }
}
