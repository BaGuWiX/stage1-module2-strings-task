package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> substrings = new ArrayList<>();
        StringBuilder patternBuilder = new StringBuilder();

        for (String delimiter : delimiters) {
            patternBuilder.append(Pattern.quote(delimiter)).append("|");
        }

        if (patternBuilder.length() > 0) {
            patternBuilder.setLength(patternBuilder.length() - 1);
            Pattern pattern = Pattern.compile(patternBuilder.toString());
            Matcher matcher = pattern.matcher(source);
            int start = 0;

            while (matcher.find()) {
                int end = matcher.start();
                if (end > start) {
                    String substring = source.substring(start, end);
                    substrings.add(substring);
                }
                start = matcher.end();
            }

            if (start < source.length()) {
                String lastSubstring = source.substring(start);
                substrings.add(lastSubstring);
            }
        } else {
            substrings.add(source);
        }

        return substrings;
    }
}
