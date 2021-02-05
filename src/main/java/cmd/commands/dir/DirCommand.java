package cmd.commands.dir;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

@Command(
        name = "dir",
        aliases = {"d"},
        description = "command can be used to list the files",
        mixinStandardHelpOptions = true)
public class DirCommand implements Runnable {

    @CommandLine.Option(names = {"-f", "--files"})
    private boolean filesOnly;

    @CommandLine.Option(names = {"-s", "--sort"})
    private String sortOder;

    // TODO(Feature-1): introduce feature, print files with relative path (--short)
    // TODO(Feature-2): introduce feature, pass desired path/directory as argument (--path)
    // TODO(Feature-3): introduce feature, check if passed file path is a file (--check)

    @Override
    public void run() {
        list(new File("."));
    }

    private void list(File dir) {
        System.out.println(dir.getAbsolutePath());
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                Stream.of(files).sorted(getFileListComparator()).forEach(this::printLine);
            }
        }
    }

    private Comparator<File> getFileListComparator() {
        // TODO(Issue-1): introduce bug, sorting direction is incorrect (asc vs desc)
        return Comparator.comparing(File::getName,
                (s1, s2) -> Objects.equals(sortOder, "asc")
                        ? s1.compareTo(s2)
                        : Objects.equals(sortOder, "desc") ? s2.compareTo(s1) : 0);
    }

    private void printLine(File f) {
        // TODO(Issue-2): introduce bug, path should be printed only in specific conditions (file vs directory)
        if (filesOnly || !f.isDirectory()) {
            System.out.println(f.getAbsolutePath());
        } else {
            list(f);
        }
    }
}
