/*
 * @author yasemin
 */
package guide;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class SentenceRecognizer {
	private static Logger logger = Logger
			.getLogger(SentenceRecognizer.class.getCanonicalName());
	
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
	
	public boolean recognizeSentence() {

		List<String> splittedSentece = Arrays
				.asList(inputSentecence.split(" "));
		boolean unRuleSentence1 = true;
		boolean unRuleSentence2 = true; 
		boolean unRuleSentence3 = true;
		boolean unRuleSentence4 = true;
		boolean result = true;
		
		// glob is I
		if ((splittedSentece.size() == 3)
				&& (splittedSentece.get(1).equalsIgnoreCase("is"))) {
			parser.unitParser();
			logger.info(inputSentecence + " is translated");
			unRuleSentence1 = false;
		}  
		// glob glob Silver is 34 Credits
		if(splittedSentece.contains("Credits") && parser.isIncludeUnit(splittedSentece)){
			if (splittedSentece.get(5).equalsIgnoreCase("Credits")) {
				parser.merchandiseParser();
				logger.info(inputSentecence + " is translated");
				unRuleSentence2 =false;
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
			logger.info(inputSentecence + " is translated");
			unRuleSentence3 = false;
		} 
		// how many Credits is glob prok Iron ? glob prok Iron is 782
		// Credits
		if (splittedSentece.get(1).equalsIgnoreCase("many") && splittedSentece.get(2).equalsIgnoreCase("Credits")
				&& parser.isIncludeUnit(splittedSentece) && parser.isIncludeMerchandise(splittedSentece)) {

			String outputResultMany = inputSentecence.substring(inputSentecence.indexOf("is")+2,
					inputSentecence.length() - 2).trim()
					+ " is ";
			
			setOutputSentecence(outputResultMany + parser.calculateMerchandise()
					+ " Credits");
			logger.info(inputSentecence + " is translated");
			unRuleSentence4 = false;
		}

		if(unRuleSentence1 && unRuleSentence2 && unRuleSentence3 && unRuleSentence4){
			setOutputSentecence("I have no idea what you are talking about");
			logger.warning("Not a ruled sentence");
			result = false;
		}
		
		return result;
		// unknown sentence
	}
	
	public boolean isQuestion(){
		if(!inputSentecence.contains("?")){
			logger.warning("Not a question sentence.");
		}
		return inputSentecence.contains("?");
	}

}
