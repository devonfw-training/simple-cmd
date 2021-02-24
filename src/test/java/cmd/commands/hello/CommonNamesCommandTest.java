package cmd.commands.hello;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonNamesCommandTest {

    private final ByteArrayOutputStream outStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outStreamCaptor));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void out() {
        //given
        CommonNamesCommand testUnit = new CommonNamesCommand();
        //when
        testUnit.run();
        //then
        assertEquals("Commonly used names are:\r\n" +
                "Bred\r\n" +
                "Helga\r\n" +
                "Kai\r\n", outStreamCaptor.toString());
    }

}