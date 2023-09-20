package org.example;

public class Divisor {
	private final StringBuilder result = new StringBuilder();
	private final StringBuilder quotient = new StringBuilder();
	private final StringBuilder reminder = new StringBuilder();
	private final String NEW_LINE = "\n";

	public String makeDivision(int dividend, int divisor) {
		try {
			if (validation(dividend,divisor)){
			String[] digits = String.valueOf(dividend).split("");
			int reminderNumber;
			int multiplyResult;
			int divisorDigit = calculateDigit(divisor);
			int modify;

			for (int i = 0; i < digits.length; i++) {
				reminder.append(digits[i]);
				reminderNumber = Integer.parseInt(reminder.toString());
				int width = i + 2;

				if (reminderNumber >= divisor) {
					modify = reminderNumber % divisor;
					multiplyResult = reminderNumber / divisor * divisor;

					String lastReminder = String.format("%" + width + "s", "_" + reminderNumber);
					result.append(lastReminder).append(NEW_LINE);

					String multiply = String.format("%" + width + "d", multiplyResult);
					result.append(multiply).append(NEW_LINE);

					Integer tab = lastReminder.length() - calculateDigit(multiplyResult);
					result.append(makeDivider(multiplyResult, tab)).append(NEW_LINE);

					quotient.append(reminderNumber / divisor);

					reminder.replace(0, reminder.length(), Integer.toString(modify));
					reminderNumber = Integer.parseInt(reminder.toString());
				} else {
					if (i >= divisorDigit) {
						quotient.append(0);
					}
				}

				if (i == digits.length - 1) {
					result.append(String.format("%" + width + "s", reminderNumber)).append(NEW_LINE);
				}
			}
			modifyResultToView(dividend, divisor);}
		} catch (IllegalArgumentException exception){
			System.out.println(exception.getMessage());
		}
		return result.toString();
	}

	private boolean validation(int dividend, int divisor){
		if (divisor == 0) {
			throw new IllegalArgumentException("Divisor cannot be 0, division by zero");
		}

		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);
		if (dividend < divisor) {
			System.out.printf("%d / %d = 0", dividend, divisor);
			return false;
		}
		return true;
	}

	private String makeDivider(Integer multiplyResult, Integer tab) {
		return assemblyString(tab, ' ') + assemblyString(calculateDigit(multiplyResult), '-');
	}

	private void modifyResultToView(Integer dividend, Integer divisor) {
		int[] index = new int[3];

		for (int i = 0, j = 0; i < result.length(); i++) {
			if (result.charAt(i) == '\n') {
				index[j] = i;
				j++;
			}

			if (j == 3) {
				break;
			}
		}

		int tab = calculateDigit(dividend) + 1 - index[0];
		result.insert(index[2], assemblyString(tab, ' ') +"│" + quotient);
		result.insert(index[1], assemblyString(tab, ' ') +"│" + assemblyString(quotient.length(), '-'));
		result.insert(index[0], "│" + divisor);
		result.replace(1, index[0], dividend.toString());
	}

	private int calculateDigit(int i) {
		return (int) Math.log10(i) + 1;
	}

	private String assemblyString(int numberOfSymbols, char symbol) {
		return String.valueOf(symbol).repeat(Math.max(0, numberOfSymbols));
	}
}
