package cmd.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.attribute.FileAttribute;

import static java.lang.System.lineSeparator;

/**
 * Abstract Command Test
 * <p/>
 * This class provides some glue code to make it easier to write tests for the different command types.
 * The handling of output streams is done transparently, this happens automatically using JUnit 5 annotations.
 * <p/>
 * Moreover, the class provides a utility method for cleaning the output:
 * @see AbstractCommandTest#cleanOutput(String)
 * @see BeforeEach
 * @see AfterEach
 */
public abstract class AbstractCommandTest {

    protected static final FileAttribute<?>[] noAttributes = new FileAttribute[0];
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOutputStream = System.out;

    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }

    private PrintStream getOriginalOutputStream() {
        return originalOutputStream;
    }

    /**
     * Clean the given string by removing all (system-specific) line separators.
     * <p/>
     * On Windows this is {@code \r\n}, on *nix-like systems this is {@code \n}.
     * @param output the output {@link String} to clean
     * @return the output string with all (system-specific) line separators removed
     */
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
