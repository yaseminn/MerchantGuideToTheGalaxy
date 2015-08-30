package guide;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Assert;

public class MerchantGuideToTheGalaxy {
	public static void main(String[] args) throws IOException {
		
		SentenceRecognizer recognizer ;
		String inputFile = null;
		String outputFile = null;
		
		if (args.length > 0) {
		    try {
		    	inputFile = args[0];

		    	String sentence = new String(Files.readAllBytes(Paths.get(inputFile)));
		    	String lines[] = sentence.split("\\r?\\n");
		    	outputFile = args[1];
		    	
		    	for(String line:lines){
		    		recognizer = new SentenceRecognizer(line);
		    		recognizer.recognizeSentence();
		    		
		    		if(recognizer.isQuestion()){
		    			Files.write(Paths.get(outputFile), recognizer.toString().getBytes(),StandardOpenOption.APPEND);
		    		}
		    	}
		    } catch (NumberFormatException e) {
		        System.err.println("Files should be given by arguments");
		        System.exit(1);
		    }
		}
       
	}
}
