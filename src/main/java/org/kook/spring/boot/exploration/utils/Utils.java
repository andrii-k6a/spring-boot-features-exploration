package org.kook.spring.boot.exploration.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.lang.String.join;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Utils {

    public static String joinOrDefault(String[] strings, String defaultValue) {
        return joinOrDefault(strings, ", ", defaultValue);
    }

    public static String joinOrDefault(String[] strings, String delimiter, String defaultValue) {
        if (strings == null || strings.length == 0) {
            return defaultValue;
        }
        return join(delimiter, strings);
    }

}
