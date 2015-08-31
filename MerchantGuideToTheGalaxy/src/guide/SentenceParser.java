/*
 * @author yasemin
 */
package guide;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class SentenceParser {
	private static Logger logger = Logger
			.getLogger(SentenceParser.class.getCanonicalName());

	private String sentence;
	private ConvertRomanToDecimal converter;
	private List<String> splittedSentence;
	private static Map<String, String> units = new HashMap<>();
	private static Map<String, Double> merchandise = new HashMap<>();
	static Set<String> validRomanNumerals = new HashSet<>(Arrays.asList("I",
			"V", "X", "L", "C", "D", "M"));

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

	public Map<String, Double> getMerchandise() {
		return merchandise;
	}

	public void unitParser() {
		if (splitSentence()) {
			String alienUnit = splittedSentence.get(0);
			String romanNumber = splittedSentence.get(2);

			if (isValidRomanNumber(romanNumber)) {
				units.put(alienUnit, romanNumber);
			}
		}
	}

	public void merchandiseParser() {
		if (splitSentence()) {
			double total = calculateUnits();
			double result = Integer.parseInt(splittedSentence.get(4)) / total;
			merchandise.put(splittedSentence.get(2), result);
		}
	}

	public Double calculateUnits() {
		if (splitSentence()) {
			String total = "";

			for (Map.Entry<String, String> entry : units.entrySet()) {
				for (String word : splittedSentence) {
					if (word.equalsIgnoreCase(entry.getKey())) {
						total = total.concat(entry.getValue());
					}
				}
			}

			if (!sentence.contains("Credits")) {
				converter = new ConvertRomanToDecimal(total);
				return converter.parseRomanToDecimal();
			}
			converter = new ConvertRomanToDecimal(new StringBuilder(total)
					.reverse().toString());
		}
		logger.info("Calculated units");
		return converter.parseRomanToDecimal();
	}

	public Double calculateMerchandise() {
		double total = 0;
		double unitsTotal = 0;

		if (splitSentence()) {
			unitsTotal = calculateUnits();
			for (Map.Entry<String, Double> entry : merchandise.entrySet()) {
				for (String word : splittedSentence) {
					if (word.equalsIgnoreCase(entry.getKey())) {
						total = entry.getValue() * unitsTotal;
					}
				}
			}

		}
		logger.info("Calculated merchandise.");
		return total;
	}

	public boolean isIncludeUnit(List<String> splittedSentece) {
		for (Map.Entry<String, String> entry : units.entrySet()) {
			for (String word : splittedSentece) {
				if (word.equalsIgnoreCase(entry.getKey())) {
					return true;
				}
			}
		}
		
		logger.warning("Not included in units.");
		return false;
	}

	public boolean isIncludeMerchandise(List<String> splittedSentece) {
		for (Map.Entry<String, Double> entry : merchandise.entrySet()) {
			for (String word : splittedSentece) {
				if (word.equalsIgnoreCase(entry.getKey())) {
					return true;
				}
			}
		}

		logger.warning("Not included in merchandises.");
		return false;
	}

	public boolean splitSentence() {
		if (sentence == null || sentence.isEmpty()) {
			logger.warning("The sentence is empty or null");
			return false;
		} else {
			splittedSentence = Arrays.asList(sentence.trim().split(" "));
			return true;
		}

	}

	public boolean isValidRomanNumber(String romanNumber) {
		for (String str : validRomanNumerals) {
			if (str.equalsIgnoreCase(romanNumber)) {
				logger.warning("This is not valid roman number");
				return true;
			}
		}

		return false;
	}

}
