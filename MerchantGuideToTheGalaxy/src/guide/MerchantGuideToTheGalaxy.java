package guide;

import org.junit.Assert;

public class MerchantGuideToTheGalaxy {
	public static void main(String[] args) {
		ConvertRomanToDecimal convertRoman = new ConvertRomanToDecimal("XCCC");
		System.out.println(convertRoman.parseRomanToDecimal());
		
		SentenceParser sentence = new SentenceParser("glob is I");
		sentence.unitParser();
		
		System.out.println("sonuç : " +sentence.getUnits().get("glob"));

		SentenceParser sentence2 = new SentenceParser("glob glob Silver is 34 Credits");
		sentence2.goldParser();
		System.out.println("sonuç : " +sentence.getGold().get("Silver"));
		
		SentenceParser sentence3 = new SentenceParser("How much is glob glob ?");
		System.out.println("sonuç : " +sentence3.calculateUnits());
		
		SentenceParser sentence4 = new SentenceParser("How many glob Silver ?");
		System.out.println("sonuç : " +sentence4.calculateGold());
		
		String str1 = "how much is glob ?";
		String str2 = "how many Credits is glob Silver ?";
		
		System.out.println(str1.substring(str1.indexOf("is") + 2, str1.length()-2).trim());
		System.out.println(str2.substring(str2.indexOf("is") + 2, str2.length()-2).trim());
		
		SentenceRecognizer recognizer1 = new SentenceRecognizer("glob is I");
		SentenceRecognizer recognizer2 = new SentenceRecognizer("glob glob Silver is 34 Credits");
		SentenceRecognizer recognizer3 = new SentenceRecognizer("how much is glob ?");
		SentenceRecognizer recognizer4 = new SentenceRecognizer("how many Credits is glob Silver ?");
		
		recognizer1.recognizeSentence();
		recognizer2.recognizeSentence();
		
		recognizer3.recognizeSentence();
		System.out.println(recognizer3.getOutputSentecence());
		
		recognizer4.recognizeSentence();
		System.out.println(recognizer4.getOutputSentecence());

	}
}
