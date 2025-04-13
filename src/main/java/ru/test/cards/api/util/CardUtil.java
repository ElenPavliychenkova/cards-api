package ru.test.cards.api.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Взято с просторов интернета, упрощено для тестового задания.
 */
public class CardUtil {

    private static final Random random = new Random();
    private static final int DEFAULT_CVV_LENGTH = 3;

    public static String generateCardNumber() {

        int length = 16;

        StringBuilder sb = new StringBuilder("4");
        while (sb.length() < length - 1) {
            sb.append(random.nextInt(10));
        }

        String numberWithoutCheckDigit = sb.toString();
        int checkDigit = calculateLuhnCheckDigit(numberWithoutCheckDigit);

        String fullNumber = numberWithoutCheckDigit + checkDigit;
        return formatCardNumber(fullNumber);
    }

    private static int calculateLuhnCheckDigit(String number) {
        int sum = 0;
        boolean alternate = false;

        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }

            sum += digit;
            alternate = !alternate;
        }

        return (10 - (sum % 10)) % 10;
    }

    private static String formatCardNumber(String number) {
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            if (i > 0 && i % 4 == 0) {
                formatted.append(" ");
            }
            formatted.append(number.charAt(i));
        }
        return formatted.toString();
    }


    public static String generateCvv() {
        StringBuilder sb = new StringBuilder(DEFAULT_CVV_LENGTH);
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < DEFAULT_CVV_LENGTH; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

}
