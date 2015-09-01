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
	private static Logger logger = Logger.getLogger(SentenceParser.class
			.getCanonicalName());

	private String sentence;
	private ConvertRomanToDecimal converter;
	private List<String> splittedSentence;
	private static Map<String, String> alienUnits = new HashMap<>();
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

	public Map<String, String> getAlienUnits() {
		return alienUnits;
	}

	public Map<String, Double> getMerchandise() {
		return merchandise;
	}

	/*
	 * Parse the sentence such as "glob is I" and getting alien unit "glob"
	 * corresponds the roman number "I".
	 */
	public void unitParser() {
		if (splitSentence()) {
			String alienUnit = splittedSentence.get(0);
			String romanNumber = splittedSentence.get(2);

			if (isValidRomanNumber(romanNumber)) {
				alienUnits.put(alienUnit, romanNumber);
			}
		}
	}

	/*
	 * Save merchandises and corresponding decimal
	 */
	public void merchandiseParser() {
		if (splitSentence()) {
			double total = calculateAlienUnits();
			double result = Integer.parseInt(splittedSentence.get(4)) / total;
			merchandise.put(splittedSentence.get(2), result);
		}
	}

	/*
	 * Parse the sentence such as "glob glob Silver is 34 Credits" and getting
	 * the merchandises' equivalent in decimal.
	 */
	public Double calculateAlienUnits() {
		if (splitSentence()) {
			String total = "";

			for (Map.Entry<String, String> entry : alienUnits.entrySet()) {
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
		logger.info("Calculated alienUnits");
		return converter.parseRomanToDecimal();
	}

	/*
	 * Calculate merchandise's from the sentence such as
	 * "how many Credits is glob prok Iron ?" By calculateAlienUnits() getting
	 * the "glob prok"'s equivalent in decimal and finding the merchandise from
	 * sentence.
	 */
	public Double calculateMerchandise() {
		double total = 0;
		double alienUnitsTotal = 0;

		if (splitSentence()) {
			alienUnitsTotal = calculateAlienUnits();
			for (Map.Entry<String, Double> entry : merchandise.entrySet()) {
				for (String word : splittedSentence) {
					if (word.equalsIgnoreCase(entry.getKey())) {
						total = entry.getValue() * alienUnitsTotal;
					}
				}
			}

		}
		logger.info("Calculated merchandise.");
		return total;
	}

	/*
	 * Check the sentence include alienunit or not.
	 */
	public boolean isIncludeUnit(List<String> splittedSentece) {
		for (Map.Entry<String, String> entry : alienUnits.entrySet()) {
			for (String word : splittedSentece) {
				if (word.equalsIgnoreCase(entry.getKey())) {
					return true;
				}
			}
		}

		logger.warning("Not included in alienUnits.");
		return false;
	}

	/*
	 * Check the sentence include merchandise or not.
	 */
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

	/*
	 *  Check is given sentence empty or not.
	 */
	public boolean splitSentence() {
		if (sentence == null || sentence.isEmpty()) {
			logger.warning("The sentence is empty or null");
			return false;
		} else {
			splittedSentence = Arrays.asList(sentence.trim().split(" "));
			return true;
		}

	}

	/*
	 * Check the number is valid roman number or not.
	 */
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
