package guide;

import java.util.Arrays;
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

	public void recognizeSentence() {

		List<String> splittedSentece = Arrays
				.asList(inputSentecence.split(" "));

		// glob is I
		if ((splittedSentece.size() == 3)
				&& (splittedSentece.get(1).equalsIgnoreCase("is"))) {
			parser.unitParser();
			// glob glob Silver is 34 Credits
		} else if (splittedSentece.get(5).equalsIgnoreCase("Credits")) {
			parser.goldParser();
			// how much is pish tegj glob glob ?
		} else if (splittedSentece.get(1).equalsIgnoreCase("much")
				&& parser.isIncludeUnit(splittedSentece)) {
			parser.calculateUnits();
			// how many Credits is glob prok Iron ?
		} else if (splittedSentece.get(1).equalsIgnoreCase("many")
				&& parser.isIncludeUnit(splittedSentece)
				&& parser.isIncludeGold(splittedSentece)) {
			parser.calculateGold();
			// unknown sentence
		} else {
			System.out.println("I have no idea what you are talking about");
		}

	}

}
