package cmd.commands.dir;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import cmd.SimpleCmd;
import cmd.commands.AbstractCommandTest;
import picocli.CommandLine;

class DirCommandTest extends AbstractCommandTest {

  /**
   * Testet den "dir" Befehl mit Einschränkung auf File-Namen.
   */
  @Test
  void testDirWithF(@TempDir Path tempDir) throws IOException {

    // given
    prepareTestFolder(tempDir);
    SimpleCmd.setCurrentLocation(tempDir.toFile());
    String[] args = { "-f" };
    DirCommand dirCommand = CommandLine.populateCommand(new DirCommand(), args);
    // when
    dirCommand.run();
    // then
    String expected = tempDir.toAbsolutePath() + File.separator + "myFile.txt";
    String actual = getOutputStream().toString();
    assertTrue(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
  }

  /**
   * Testet den "dir" Befehl ohne Angabe von Argumenten.
   */
  @Test
  void testDirWithoutArguments(@TempDir Path tempDir) throws IOException {

    // given
    prepareTestFolder(tempDir);
    SimpleCmd.setCurrentLocation(tempDir.toFile());
    String[] args = {};
    DirCommand dirCommand = CommandLine.populateCommand(new DirCommand(), args);
    // when
    dirCommand.run();
    // then
    String expected = tempDir.toAbsolutePath() + File.separator + "myFile.txt";
    String actual = getOutputStream().toString();
    assertTrue(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
  }

  /**
   * Testet den "dir" Befehl unter Angabe eine Pfades und Einschränkung auf File-Namen.
   */
  @Test
  void testDirWithDirectoryPathAndF(@TempDir Path tempDir) throws IOException {

    // given
    prepareTestFolder(tempDir);
    SimpleCmd.setCurrentLocation(new File("C:\\"));
    String[] args = { tempDir.toString(), "-f" };
    DirCommand dirCommand = CommandLine.populateCommand(new DirCommand(), args);
    // when
    dirCommand.run();
    // then
    String expected = tempDir.toAbsolutePath() + File.separator + "myFile.txt";
    String actual = getOutputStream().toString();
    assertTrue(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
  }

  /**
   * Testet den "dir" Befehl unter Angabe eine Pfades.
   */
  @Test
  void testDirWithDirectoryPath(@TempDir Path tempDir) throws IOException {

    // given
    prepareTestFolder(tempDir);
    SimpleCmd.setCurrentLocation(new File("C:\\"));
    String[] args = { tempDir.toString() };
    DirCommand dirCommand = CommandLine.populateCommand(new DirCommand(), args);
    // when
    dirCommand.run();
    // then
    String expected = tempDir.toAbsolutePath() + File.separator + "myFile.txt";
    String actual = getOutputStream().toString();
    assertTrue(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
  }

  /**
   * Testet den "dir" Befehl unter Angabe eines nicht validen Pfades.
   */
  @Test
  void testDirWithDirectoryWrongPath(@TempDir Path tempDir) throws IOException {

    // given
    prepareTestFolder(tempDir);
    SimpleCmd.setCurrentLocation(new File("C:\\"));
    String[] args = { "csjkdfkasfjksdfksdf" };
    DirCommand dirCommand = CommandLine.populateCommand(new DirCommand(), args);
    // when
    dirCommand.run();
    // then
    String expected = tempDir.toAbsolutePath() + File.separator + "myFile.txt";
    String actual = getOutputStream().toString();
    assertFalse(actual.contains(expected), "Expected : " + expected + " But was: " + actual);
  }

  private void prepareTestFolder(@TempDir Path tempDir) throws IOException {

    // for other possible usages of @TempDir see https://www.baeldung.com/junit-5-temporary-directory
    Path myFile = tempDir.resolve("myFile.txt");
    Files.write(myFile, Collections.singletonList(""));

    Path folder1 = tempDir.resolve("folder1");
    Path directory = Files.createDirectory(folder1, noAttributes);

    Path myFile2 = directory.resolve("myFile2.txt");
    Files.write(myFile2, Collections.singletonList(""));
  }
}