package br.com.cesarschool.domain.global;

import java.security.SecureRandom;
import java.util.LinkedHashSet;
import java.util.Set;

public class RandomStringGenerator {
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateUniqueString(int length) {
        SecureRandom random = new SecureRandom();
        Set<Character> uniqueChars = new LinkedHashSet<>();

        while (uniqueChars.size() < length) {
            int index = random.nextInt(CHAR_POOL.length());
            uniqueChars.add(CHAR_POOL.charAt(index));
        }

        StringBuilder result = new StringBuilder();
        for (char c : uniqueChars) {
            result.append(c);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateUniqueString(8));
    }
}
