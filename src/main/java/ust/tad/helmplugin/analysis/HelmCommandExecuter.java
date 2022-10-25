package ust.tad.helmplugin.analysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelmCommandExecuter {
	
    @Value("${kube.output.directory}")
    private String outputPath;

	/**
	 * Execute a Helm command on the local command line.
	 * 
	 * @param command
	 * @throws IOException
	 */
	public void executeHelmCommand(String command) throws IOException {
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		ProcessBuilder builder = new ProcessBuilder();
		if (isWindows) {
			builder.command("cmd.exe", "/c", command);
		} else {
			builder.command("sh", "-c", command);
		}
		builder.start();
	}


	/**
	 * Render the Kubernetes template of a Helm chart.
	 * Executes the Helm template command for that purpose.
	 * Saves the result as a yaml file to the local file system.
	 * 
	 * @param templateCommand
	 * @return the path where the result is saved to.
	 * @throws IOException
	 * @throws InterruptedException
	 */
    public String renderTemplate(String templateCommand) throws IOException, InterruptedException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		String fileName = templateCommand.split(" ")[2];

		ProcessBuilder builder = new ProcessBuilder();
		if (isWindows) {
			builder.command("cmd.exe", "/c", templateCommand);
		} else {
			builder.command("sh", "-c", templateCommand);
		}
		
		Process process = builder.start();
		StringBuilder output = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

		String line;
		while ((line = reader.readLine()) != null) {
			output.append(line + "\n");
		}
		int exitCode = process.waitFor();
		assert exitCode == 0;

		File outputFile = Path.of(outputPath,fileName+".yaml").toFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		writer.write(output.toString());
		
		writer.close();
		return outputFile.getAbsolutePath();
    }
    
}
