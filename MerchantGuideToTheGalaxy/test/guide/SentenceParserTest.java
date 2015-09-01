/*
 * @author yasemin
 */

package guide;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SentenceParserTest {

	@Test
	public void alienUnitParserTest() {
		SentenceParser sentence = new SentenceParser("glob is I");
		sentence.unitParser();
		String result = "I";
		Assert.assertEquals(sentence.getAlienUnits().get("glob"),result);
	}
	
	@Test
	public void merchandiseParserTest() {
		SentenceParser sentence = new SentenceParser("glob glob Silver is 34 Credits.");
		sentence.merchandiseParser() ;
		Double result = 17.0;
		Assert.assertEquals(sentence.getMerchandise().get("Silver"),result);
	}
	
	@Test
	public void calculateAlienUnitsTest() {
		SentenceParser sentence = new SentenceParser("How much is glob glob ?");
		Double result = 2.0;
		Assert.assertEquals(sentence.calculateAlienUnits(),result);
	}
	
	
	@Test
	public void calculateMerchandiseTest() {
		SentenceParser sentence = new SentenceParser("How many glob Silver ?");
		Double result = 17.0;
		Assert.assertEquals(sentence.calculateMerchandise(),result);
	}
	
	@Test
	public void isIncludeUnitTest() {
		SentenceParser sentence = new SentenceParser("glob is I");
		sentence.unitParser();
		String input = "How many glob Silver ?";
		List<String> inputSplitted = Arrays.asList(input.split(" "));
		
		Assert.assertTrue(sentence.isIncludeUnit(inputSplitted));
	}

	@Test
	public void isIncludeMerchandiseTest() {
		SentenceParser sentence = new SentenceParser("glob glob Silver is 34 Credits");
		sentence.merchandiseParser();
		String input = "How many glob Silver ?";
		List<String> inputSplitted = Arrays.asList(input.split(" "));
		
		Assert.assertTrue(sentence.isIncludeMerchandise(inputSplitted));
	}
	
	@Test
	public void splitSentenceTest() {
		SentenceParser sentence = new SentenceParser("");
		Assert.assertTrue(!sentence.splitSentence());
	}
	
	@Test
	public void isValidRomanNumberTest() {
		SentenceParser sentence = new SentenceParser("");
		Assert.assertTrue(sentence.isValidRomanNumber("I"));
		Assert.assertTrue(!sentence.isValidRomanNumber("Q"));
	}

}
