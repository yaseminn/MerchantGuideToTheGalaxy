/*
 * @author yasemin
 */
package guide;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ConvertRomanToDecimal {
	private static Logger logger = Logger.getLogger(ConvertRomanToDecimal.class
			.getCanonicalName());

	private String romans;

	public ConvertRomanToDecimal(String romanNumber) {
		romans = romanNumber;
	}

	public void setNumber(String romanNumber) {
		this.romans = romanNumber;
	}

	public String getNumber() {
		return romans;
	}

	public static List<Character> unRepeated = Arrays.asList('D', 'L', 'V');
	public static List<Character> repeated = Arrays.asList('I', 'X', 'C', 'M');

	public static Map<Character, Integer> romanNumbers = Collections
			.unmodifiableMap(new HashMap<Character, Integer>() {
				{
					put('I', 1);
					put('V', 5);
					put('X', 10);
					put('L', 50);
					put('C', 100);
					put('D', 500);
					put('M', 1000);
				}
			});

	public static boolean isSmaller(char firstNum, char secondNum) {
		return romanNumbers.get(firstNum) < romanNumbers.get(secondNum);
	}

	/*
	 * Convert the given roman number to decimal.
	 */
	public Double parseRomanToDecimal() {
		Double total = 0.0;
		int previousValue = Integer.MAX_VALUE;
		int index = 0;
		char previousDigit = '\0';
		int sameDigitCounter = 1;

		while (index < romans.length()) {
			
			char digit = romans.charAt(index);

			int value = romanNumbers.get(digit);

			if (value <= previousValue) {
				// Find the numbers not repeatable
				if (previousValue == value) {
					if (repeated(digit)) {
						sameDigitCounter++;
						// If the number repeatable, detects how many times it repeat
						if ((sameDigitCounter == 3)
								&& (romans.length() > (index + 2))) {
							//If repeats more than 3, check the fourth and fifth number according to rule
							if (romans.charAt(index + 2) == digit) {
								if (!isSmaller(romans.charAt(index + 1), digit)) {
									logger.warning(digit
											+ " can't be same as previous.");
									break;
								}
							} else {
								break;
							}
						}
					} else {
						logger.warning(digit + " can't repeat.");
						break;
					}
				}

				previousValue = value;
				total += value;
			} else {
				if (subtractable(previousDigit, digit)) {
					total -= previousValue;
					total += (value - previousValue);
				}
			}
			previousValue = value;
			previousDigit = digit;
			index++;
		}

		return total;
	}

	
	/*
	 * Check the given first number is smaller than the second one according to rule.
	 */
	public static boolean subtractable(Character firstNum, Character secondNum) {

		if ((firstNum.equals('I'))
				&& ((secondNum.equals('V')) || (secondNum.equals('X')))) {
			return true;
		} else if ((firstNum.equals('X'))
				&& ((secondNum.equals('L')) || (secondNum.equals('C')))) {
			return true;
		} else if ((firstNum.equals('C'))
				&& ((secondNum.equals('D')) || (secondNum.equals('M')))) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * Check the given digit repeats according to rule.
	 */
	public static boolean repeated(char digit) {
		boolean result = false;
		for (Character c : repeated) {
			if (c.equals(digit)) {
				result = true;
			}
		}
		return result;
	}

}
