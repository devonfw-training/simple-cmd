package cmd.commands.date;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cmd.commands.AbstractTest;
import cmd.commands.BaseCommand;
import org.junit.jupiter.api.Disabled;
import picocli.CommandLine;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

@Disabled
class DateCommandTest extends AbstractTest {

  private static final String DATE_COMMAND = "date";
  private static final String DATE_FORMAT_OPTION = "-f";
  private static final CommandLine commandLine = new CommandLine(new BaseCommand());

  @Test
  void getCurrentDateInDefaultFormat() throws IOException {
    // given
    String[] args = {DATE_COMMAND};
    // when
    commandLine.execute(args);
    // then
    String expected = LocalDate.now().toString();
    String actual = getOutputStream().toString();
    actual = cleanOutput(actual);
    assertEquals(actual, expected, "Expected : " + expected + " But was: " + actual);
  }

  @Test
  void getCurrentDateInDesiredFormat() throws IOException {
    // given
    String dateFormat = "dd-MM-yyyy";
    String[] args = {DATE_COMMAND, DATE_FORMAT_OPTION, dateFormat};
    // when
    commandLine.execute(args);
    // then
    String expected = LocalDate.now().format(DateTimeFormatter.ofPattern(dateFormat));
    String actual = getOutputStream().toString();
    actual = cleanOutput(actual);
    assertEquals(actual, expected, "Expected : " + expected + " But was: " + actual);
  }
}
