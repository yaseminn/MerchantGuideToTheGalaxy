package guide;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentenceParser {
	private String sentence;
	private ConvertRomanToDecimal converter;
	private static Map<String, String> units = new HashMap<>();
	private static Map<String, Double> gold = new HashMap<>();
	private String total;

	public SentenceParser(String sentence) {
		this.sentence = sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getSentence() {
		return sentence;
	}

	public Map<String, String> getUnits() {
		return units;
	}

	public Map<String, Double> getGold() {
		return gold;
	}

	public void unitParser() {
		List<String> splittedSentence = Arrays.asList(sentence.split(" "));
		units.put(splittedSentence.get(0), splittedSentence.get(2));
	}

	public void goldParser() {
		List<String> splittedSentence = Arrays.asList(sentence.split(" "));
		
		double total = calculateUnits();
		double result = Integer.parseInt(splittedSentence.get(4)) / total;
		gold.put(splittedSentence.get(2), result);
	}
	
	public double calculateUnits(){
		List<String> splittedSentence = Arrays.asList(sentence.split(" "));
		
		String total = "";
		
		for (Map.Entry<String, String> entry : units.entrySet()) {
			for (String word : splittedSentence) {
				if(word.equalsIgnoreCase(entry.getKey())){
					total= total.concat(entry.getValue()) ;
				}
			}
		}
		
		if(!sentence.contains("Credits")){
			converter = new ConvertRomanToDecimal(total);
			return converter.parseRomanToDecimal();
		}
		converter = new ConvertRomanToDecimal(new StringBuilder(total).reverse().toString());
		return converter.parseRomanToDecimal();
	}
	
	public double calculateGold(){
		List<String> splittedSentence = Arrays.asList(sentence.split(" "));
		
		double total = 0;
		double unitsTotal = 0;
		unitsTotal = calculateUnits();
		
		for (Map.Entry<String, Double> entry : gold.entrySet()) {
			for (String word : splittedSentence) {
				if(word.equalsIgnoreCase(entry.getKey())){
					total =  entry.getValue() * unitsTotal;
				}
			}
		}
		
		return total;
	}
	
	public boolean isIncludeUnit(List<String> splittedSentece){
		for (Map.Entry<String, String> entry : units.entrySet()) {
			for (String word : splittedSentece) {
				if(word.equalsIgnoreCase(entry.getKey())){
					return true;
				}
			}
		}
		
		return false;
	}

	public boolean isIncludeGold(List<String> splittedSentece){
		for (Map.Entry<String, Double> entry : gold.entrySet()) {
			for (String word : splittedSentece) {
				if(word.equalsIgnoreCase(entry.getKey())){
					return true;
				}
			}
		}
		
		return false;
	}
}
