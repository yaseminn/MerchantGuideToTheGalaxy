/*
 * @author yasemin
 * 
 * The program includes the calculations between Roman Numbers to Alien units.
 * Input and outfile which are in source file should be given as an argument. 
 * The logs helps you to understand the program is working correctly or not.
 * Before the begining create input and output file both, and every time run the
 * program clear the output's content.
 * 
 * To run from terminal:
 * java -jar <jar-file-name>.jar input.txt output.txt 
 *
 */

package guide;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

import org.junit.Assert;

public class MerchantGuideToTheGalaxy {
	private static Logger logger = Logger
			.getLogger(MerchantGuideToTheGalaxy.class.getCanonicalName());

	public static void main(String[] args) throws IOException {

		SentenceRecognizer recognizer;
		String inputFile = null;
		String outputFile = null;

		if (args.length > 0) {
			try {
				logger.info("Reading arguments successfully.");
				inputFile = args[0];

				String sentence = new String(Files.readAllBytes(Paths
						.get(inputFile)));
				if (sentence.isEmpty()) {
					logger.warning("File is empty or can't be read");
				}
				logger.info("File read successfully");
				String lines[] = sentence.split("\\r?\\n");
				outputFile = args[1];

				for (String line : lines) {
					recognizer = new SentenceRecognizer(line);
					recognizer.recognizeSentence();

					if (recognizer.isQuestion()
							|| !recognizer.recognizeSentence()) {
						Files.write(Paths.get(outputFile), recognizer
								.toString().getBytes(),
								StandardOpenOption.APPEND);
						logger.info("Writing file successfully");
					}
				}
			} catch (NumberFormatException e) {
				System.err.println("Files should be given by arguments");
				System.exit(1);
			}
		}

	}
}
