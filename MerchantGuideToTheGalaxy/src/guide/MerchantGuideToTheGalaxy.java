package guide;

public class MerchantGuideToTheGalaxy {
	public static void main(String[] args) {
		ConvertRomanToDecimal convertRoman = new ConvertRomanToDecimal("XCCC");
		System.out.println(convertRoman.parseRomanToDecimal());
		
		SentenceParser sentence = new SentenceParser("glob is I");
		sentence.unitParser();
		
		System.out.println("sonuç : " +sentence.getUnits().get("glob"));

		SentenceParser sentence2 = new SentenceParser("glob glob Silver is 34 Credits.");
		sentence2.goldParser();
		System.out.println("sonuç : " +sentence.getGold().get("Silver"));
		
	}
}
