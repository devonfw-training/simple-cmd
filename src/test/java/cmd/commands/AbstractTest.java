package cmd.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.attribute.FileAttribute;

import static java.lang.System.lineSeparator;

public abstract class AbstractTest {

    protected static final FileAttribute<?>[] noAttributes = new FileAttribute[0];
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOutputStream = System.out;

    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }

    private PrintStream getOriginalOutputStream() {
        return originalOutputStream;
    }

    public String cleanOutput(String output) {
      return output.replaceAll(lineSeparator(), "");
    }

    @BeforeEach
    public void setupOutputStream() {
        System.setOut(new PrintStream(getOutputStream()));
    }

    @AfterEach
    public void restoreOutputStream() {
        System.setOut(getOriginalOutputStream());
        getOutputStream().reset();
    }
}
