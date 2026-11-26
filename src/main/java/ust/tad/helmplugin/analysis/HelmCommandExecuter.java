package ust.tad.helmplugin.analysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelmCommandExecuter {

  private static final Logger LOG = LoggerFactory.getLogger(HelmCommandExecuter.class);

  @Value("${kube.output.directory}")
  private String outputPath;

  /**
   * Execute a Helm command on the local command line. If the command fails, its error output is
   * logged, but no exception is thrown: e.g., "helm repo add" also fails if the repository was
   * already added in an earlier transformation, and a missing repository lets the rendering of the
   * template fail anyway.
   *
   * @param command
   * @throws IOException
   */
  public void executeHelmCommand(String command) throws IOException, InterruptedException {
    CommandResult result = execute(command);
    if (result.exitCode != 0) {
      LOG.warn(
          String.format(
              "Helm command '%s' failed with exit code %d: %s",
              command, result.exitCode, result.error));
    }
  }

  /**
   * Render the Kubernetes template of a Helm chart. Executes the Helm template command for that
   * purpose. Saves the result as a yaml file to the local file system.
   *
   * @param templateCommand
   * @return the path where the result is saved to.
   * @throws IOException if the Helm template command fails, with the error output of Helm.
   * @throws InterruptedException
   */
  public String renderTemplate(String templateCommand) throws IOException, InterruptedException {
    String fileName = templateCommand.split(" ")[2];
    CommandResult result = execute(templateCommand);
    if (result.exitCode != 0) {
      throw new IOException(
          String.format(
              "Helm command '%s' failed with exit code %d: %s",
              templateCommand, result.exitCode, result.error));
    }

    File outputFile = Path.of(outputPath, fileName + ".yaml").toFile();
    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
    writer.write(result.output);

    writer.close();
    return outputFile.getAbsolutePath();
  }

  /**
   * Execute a command on the local command line and collect its standard output, error output, and
   * exit code. The error output is redirected to a temporary file, so that neither output stream
   * can block the process.
   */
  private CommandResult execute(String command) throws IOException, InterruptedException {
    boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
    ProcessBuilder builder = new ProcessBuilder();
    if (isWindows) {
      builder.command("cmd.exe", "/c", command);
    } else {
      builder.command("sh", "-c", command);
    }
    File errorFile = File.createTempFile("helm-error", ".txt");
    try {
      builder.redirectError(errorFile);
      Process process = builder.start();
      String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
      int exitCode = process.waitFor();
      String error = Files.readString(errorFile.toPath(), StandardCharsets.UTF_8).trim();
      return new CommandResult(exitCode, output, error);
    } finally {
      Files.deleteIfExists(errorFile.toPath());
    }
  }

  private static class CommandResult {
    private final int exitCode;
    private final String output;
    private final String error;

    private CommandResult(int exitCode, String output, String error) {
      this.exitCode = exitCode;
      this.output = output;
      this.error = error;
    }
  }
}
