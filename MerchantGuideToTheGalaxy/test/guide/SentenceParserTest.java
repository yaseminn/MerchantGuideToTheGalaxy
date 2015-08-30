package guide;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SentenceParserTest {

	@Test
	public void unitParserTest() {
		SentenceParser sentence = new SentenceParser("glob is I");
		sentence.unitParser() ;
		Integer result = 1;
		Assert.assertEquals(sentence.getUnits().get("glob"),result);
	}
	
	@Test
	public void goldParserTest() {
		SentenceParser sentence = new SentenceParser("glob glob Silver is 34 Credits.");
		sentence.goldParser() ;
		Integer result = 17;
		Assert.assertEquals(sentence.getGold().get("Silver"),result);
	}
	
	@Test
	public void calculateUnitsTest() {
		SentenceParser sentence = new SentenceParser("How much is glob glob ?");
		int result = 2;
		Assert.assertEquals(sentence.calculateUnits(),result);
	}
	
	
	@Test
	public void calculateGoldTest() {
		SentenceParser sentence = new SentenceParser("How many glob Silver ?");
		int result = 17;
		Assert.assertEquals(sentence.calculateGold(),result);
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
	public void isIncludeGoldTest() {
		SentenceParser sentence = new SentenceParser("glob glob Silver is 34 Credits");
		sentence.goldParser();
		String input = "How many glob Silver ?";
		List<String> inputSplitted = Arrays.asList(input.split(" "));
		
		Assert.assertTrue(sentence.isIncludeGold(inputSplitted));
	}
}
