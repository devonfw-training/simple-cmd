package cmd.commands.date;

import picocli.CommandLine;

import java.time.LocalDate;

@CommandLine.Command(
        name = "date",
        description = "command can be used to output date",
        mixinStandardHelpOptions = true)

public class DateCommand implements Runnable {

    public DateCommand() {
    }

    @Override
    public void run() {
        System.out.println(LocalDate.now());
    }

}
