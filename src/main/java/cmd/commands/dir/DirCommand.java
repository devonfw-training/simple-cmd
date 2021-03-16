package cmd.commands.dir;

import cmd.SimpleCmd;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

import static picocli.CommandLine.Option;

@Command(
        name = "dir",
        description = "command can be used to list the files",
        mixinStandardHelpOptions = true)
public class DirCommand implements Runnable {

    @Option(names = {"-f", "--files"}, description = "print only file names, directories will not be listed")
    private boolean filesOnly;
    @Option(names = {"-s", "--sort"}, description = "posible values are {asc, desc}")
    private String sortOder;

    public DirCommand() {
    }

    @Override
    public void run() {
        list(SimpleCmd.getCurrentLocation());
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
        return Comparator.comparing(File::getName,
                (s1, s2) -> Objects.equals(sortOder, "desc")
                        ? s1.compareTo(s2)
                        : Objects.equals(sortOder, "asc") ? s2.compareTo(s1) : 0);
    }

    private void printLine(File f) {
        if (filesOnly || !f.isDirectory()) {
            System.out.println(f.getAbsolutePath());
        } else {
            list(f);
        }
    }
}
