package org.mjhost.anagrafica.utils;

import java.util.Arrays;

public class TrimUtils {

    public static String smartTrim(String input) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream((input).split("\\s{1,}")).forEach(t -> sb.append(" ".concat(t)));
        return sb.substring(1);
    }
}
