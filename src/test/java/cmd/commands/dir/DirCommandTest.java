package cmd.commands.dir;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

class DirCommandTest {

    @Test
    void run(@TempDir Path tempDir) {

    }

    @Test
    void fail() {
        throw new NullPointerException();
    }
}