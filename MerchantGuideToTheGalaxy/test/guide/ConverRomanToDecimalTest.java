package guide;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class ConverRomanToDecimalTest {

	@Test
	public void isSmallertest() {
		Assert.assertTrue(ConvertRomanToDecimal.isSmaller('I', 'X'));
		Assert.assertTrue(ConvertRomanToDecimal.isSmaller('X', 'C'));
	}

	@Test
	public void subtractabletest() {
		Assert.assertTrue(ConvertRomanToDecimal.subtractable('I', 'X'));
		Assert.assertTrue(ConvertRomanToDecimal.subtractable('I', 'V'));
		
		Assert.assertTrue(ConvertRomanToDecimal.subtractable('X', 'C'));
		Assert.assertTrue(ConvertRomanToDecimal.subtractable('X', 'L'));
		
		Assert.assertTrue(ConvertRomanToDecimal.subtractable('C', 'D'));
		Assert.assertTrue(ConvertRomanToDecimal.subtractable('C', 'M'));
	}
	
	@Test
	public void parseRomanToDecimaltest() {
		ConvertRomanToDecimal convertRoman = new ConvertRomanToDecimal("XCCC");
		Assert.assertEquals(convertRoman.parseRomanToDecimal(),290);

	}
	
	@Test
	public void repeatedtest() {
		Assert.assertTrue(ConvertRomanToDecimal.repeated('X'));
		Assert.assertTrue(ConvertRomanToDecimal.repeated('I'));
		Assert.assertTrue(ConvertRomanToDecimal.repeated('C'));
		Assert.assertTrue(ConvertRomanToDecimal.repeated('M'));
		Assert.assertTrue(!ConvertRomanToDecimal.repeated('L'));
	}
}
