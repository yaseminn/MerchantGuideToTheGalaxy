package guide;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentenceParser {
	private String sentence;
	private ConvertRomanToDecimal converter;
	private static Map<String, Integer> units = new HashMap<>();
	private static Map<String, Integer> gold = new HashMap<>();

	public SentenceParser(String sentence) {
		this.sentence = sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getSentence() {
		return sentence;
	}

	public Map<String, Integer> getUnits() {
		return units;
	}

	public Map<String, Integer> getGold() {
		return gold;
	}

	public void unitParser() {
		List<String> splittedSentence = Arrays.asList(sentence.split(" "));
		converter = new ConvertRomanToDecimal(splittedSentence.get(2));
		units.put(splittedSentence.get(0), converter.parseRomanToDecimal());
	}

	public void goldParser() {
		List<String> splittedSentence = Arrays.asList(sentence.split(" "));
		int total = 0;

		for (Map.Entry<String, Integer> entry : units.entrySet()) {
			for (String word : splittedSentence) {
				if(word.equalsIgnoreCase(entry.getKey())){
					total += entry.getValue();
				}
			}
		}

		int result = Integer.parseInt(splittedSentence.get(4)) / total;
		gold.put(splittedSentence.get(2), result);
	}
	

}
