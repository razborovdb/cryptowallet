package com.development.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class CryptoWalletUtils {
    private static final Pattern INVALID_CHARACTER_PATTERN = Pattern.compile("[\"\'\\\\]");

    /**
     *
     */
    private CryptoWalletUtils() {}

    /**
     * Checks that the provided String contains only valid characters.
     * @param stringToValidate the String to be validated
     * @return true if the String is valid (contains only valid characters),
     *         false otherwise
     */
    public static boolean isValidString(final String stringToValidate) {
        if (StringUtils.isBlank(stringToValidate)) {
            return false;
        }

        return !INVALID_CHARACTER_PATTERN.matcher(stringToValidate).find();
    }
}
