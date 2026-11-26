package ust.tad.helmplugin.analysis;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Tests the handling of the command results. Shell commands stand in for Helm; the third word of a
 * template command is the name of the template file, as in "helm template <release> <chart>".
 */
public class HelmCommandExecuterTest {

  @TempDir Path outputDirectory;

  private final HelmCommandExecuter executer = new HelmCommandExecuter();

  @BeforeEach
  public void setOutputDirectory() {
    ReflectionTestUtils.setField(executer, "outputPath", outputDirectory.toString());
  }

  @Test
  public void renderTemplate_writesOutput() throws IOException, InterruptedException {
    String path = executer.renderTemplate("echo template release");

    assertEquals(outputDirectory.resolve("release.yaml").toString(), path);
    assertEquals("template release\n", Files.readString(Path.of(path)));
  }

  @Test
  public void renderTemplate_failsWithErrorOutput() {
    IOException exception =
        assertThrows(
            IOException.class,
            () -> executer.renderTemplate("ls template missing-release-file"));

    assertTrue(exception.getMessage().contains("missing-release-file"), exception.getMessage());
    assertFalse(Files.exists(outputDirectory.resolve("missing-release-file.yaml")));
  }

  @Test
  public void executeHelmCommand_doesNotFail() {
    assertDoesNotThrow(() -> executer.executeHelmCommand("ls repo add missing-repository"));
  }
}
