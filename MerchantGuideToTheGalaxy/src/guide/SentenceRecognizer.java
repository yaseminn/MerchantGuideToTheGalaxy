package guide;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SentenceRecognizer {

	private String inputSentecence;
	private String outputSentecence;

	private SentenceParser parser;

	public SentenceRecognizer(String sentence) {
		inputSentecence = sentence;
		parser = new SentenceParser(inputSentecence);
	}

	public String getInputSentecence() {
		return inputSentecence;
	}

	public String getOutputSentecence() {
		return outputSentecence;
	}

	public void setOutputSentecence(String outputSentence) {
		this.outputSentecence = outputSentence;
	}

	@Override
	public String toString() {
		return getOutputSentecence() + "\n";
	}
	
	public void recognizeSentence() {

		List<String> splittedSentece = Arrays
				.asList(inputSentecence.split(" "));

		// glob is I
		if ((splittedSentece.size() == 3)
				&& (splittedSentece.get(1).equalsIgnoreCase("is"))) {
			parser.unitParser();
		}  
		// glob glob Silver is 34 Credits
		if(splittedSentece.contains("Credits") && parser.isIncludeUnit(splittedSentece)){
			if (splittedSentece.get(5).equalsIgnoreCase("Credits")) {
				parser.goldParser();
			} 
		}
		// how much is pish tegj glob glob ? pish tegj glob glob is 42
		if (splittedSentece.get(1).equalsIgnoreCase("much")
				&& parser.isIncludeUnit(splittedSentece)) {
			String outputResultMuch = inputSentecence.substring(inputSentecence.indexOf("is")+2,
					inputSentecence.length() - 2).trim()
					+ " is ";
			setOutputSentecence(outputResultMuch
					+ Double.toString(parser.calculateUnits()));
		} 
		// how many Credits is glob prok Iron ? glob prok Iron is 782
		// Credits
		if (splittedSentece.get(1).equalsIgnoreCase("many") && splittedSentece.get(2).equalsIgnoreCase("Credits")
				&& parser.isIncludeUnit(splittedSentece) && parser.isIncludeGold(splittedSentece)) {

			
			String outputResultMany = inputSentecence.substring(inputSentecence.indexOf("is")+2,
					inputSentecence.length() - 2).trim()
					+ " is ";
			
			setOutputSentecence(outputResultMany + parser.calculateGold()
					+ " Credits");
		} 

		// unknown sentence
	}
	
	public boolean isQuestion(){
		return inputSentecence.contains("?");
	}

}
