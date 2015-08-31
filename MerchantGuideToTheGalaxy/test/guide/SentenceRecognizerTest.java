/*
 * @author yasemin
 */
package guide;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class SentenceRecognizerTest {

	@Test
	public void recognizeSentenceTest() {
		
		
		SentenceRecognizer recognizer1 = new SentenceRecognizer("glob is I");
		SentenceRecognizer recognizer2 = new SentenceRecognizer("glob glob Silver is 34 Credits");
		SentenceRecognizer recognizer3 = new SentenceRecognizer("how much is glob ?");
		SentenceRecognizer recognizer4 = new SentenceRecognizer("how many Credits is glob Silver ?");
		
		recognizer1.recognizeSentence();
		recognizer2.recognizeSentence();
		
		recognizer3.recognizeSentence();
		Assert.assertEquals(recognizer3.getOutputSentecence(),"glob is 1.0");
		
		recognizer4.recognizeSentence();
		Assert.assertEquals(recognizer4.getOutputSentecence(),"glob Silver is 17.0 Credits");
	}
	
	@Test
	public void isQuestionTest() {
		SentenceRecognizer recognizer = new SentenceRecognizer("how much is glob ?");
		Assert.assertTrue(recognizer.isQuestion());
	}
	
	

}
