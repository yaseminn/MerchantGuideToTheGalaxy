package guide;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentenceParser {
	private String sentence;
	private Map<String, String> units = new HashMap<>();
	private Map<String, String> gold = new HashMap<>();

	public SentenceParser(String sentence) {
		this.sentence = sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getSentence() {
		return sentence;
	}

	public Map<String, String> getUnits (){
		return units;
	}
	
	public Map<String,String> getGold(){
		return gold;
	}
	
	public void unitParser() {
		List<String> splittedSentence = Arrays.asList(sentence.split(" "));
		units.put(splittedSentence.get(0), splittedSentence.get(2));
	}
	
	public void goldParser() {
		List<String> splittedSentence = Arrays.asList(sentence.split(" "));
		units.put(splittedSentence.get(2), splittedSentence.get(4));
	}
	
	
	
}
