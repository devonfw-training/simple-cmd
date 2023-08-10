package cmd.commands.date;

import picocli.CommandLine;

import java.util.Date;

@CommandLine.Command(name = "date", description = "Returns the current DateCommand", mixinStandardHelpOptions = true)
public class DateCommand implements Runnable {

    @Override
    public void run() {
        System.out.println(new Date());
    }
}
