package cmd.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.attribute.FileAttribute;

public class AbstractTest {

    protected static final FileAttribute<?>[] noAttributes = new FileAttribute[0];

    private final ByteArrayOutputStream outStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    public ByteArrayOutputStream getOutStreamCaptor() {
        return outStreamCaptor;
    }

    public PrintStream getOriginalOut() {
        return originalOut;
    }

    public String cleanOutput(String output) {
      return output.replaceAll("\r\n", "");
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(getOutStreamCaptor()));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(getOriginalOut());
    }
}
