package cmd.commands.hello;

import cmd.commands.AbstractTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonNamesCommandTest extends AbstractTest {

    @Test
    public void out() {
        //given
        CommonNamesCommand testUnit = new CommonNamesCommand();
        //when
        testUnit.run();
        //then
        assertEquals("Commonly used names are:\n" +
                "Bred, Helga, Kai\n", getOutStreamCaptor().toString());
    }

}