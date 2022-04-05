package com.meli.challenge.morse.model.constants;

import java.util.HashMap;
import java.util.Map;

public abstract class MorseCode {

    public static class Signal {
        public static final String DIT = ".";
        public static final String DAH = "-";
        public static final String CHAR_EMPTY = "";
        public static final String CHAR_SEPARATOR = " ";
        public static final String WORD_SEPARATOR = "   ";
        public static final String WORD_SEPARATOR_TOKEN = " %¬% ";
        public static final String STOP = ".-.-.-";
    }

    public static final Map<String, String> DICTIONARY = new HashMap<>() {{
        put(".-", "A");
        put("-...", "B");
        put("-.-.", "C");
        put("-..", "D");
        put(".", "E");
        put("..-.", "F");
        put("--.", "G");
        put("....", "H");
        put("..", "I");
        put(".---", "J");
        put("-.-", "K");
        put(".-..", "L");
        put("--", "M");
        put("-.", "N");
        put("---", "O");
        put(".--.", "P");
        put("--.-", "Q");
        put(".-.", "R");
        put("...", "S");
        put("-", "T");
        put("..-", "U");
        put("...-", "V");
        put(".--", "W");
        put("-..-", "X");
        put("-.--", "Y");
        put("--..", "Z");
        put("-----", "0");
        put(".----", "1");
        put("..---", "2");
        put("...--", "3");
        put("....-", "4");
        put(".....", "5");
        put("-....", "6");
        put("--...", "7");
        put("---..", "8");
        put("----.", "9");
        put(".-.-.-", ".");
        put("--..--", ",");
        put("..--..", "?");
        put(".----.", "'");
        put("-.-.--", "!");
        put("-..-.", "/");
        put("-.--.", "(");
        put("-.--.-", ")");
        put(".-...", "&");
        put("---...", ",");
        put("-.-.-.", ";");
        put("-...-", "=");
        put(".-.-.", "+");
        put("-....-", "-");
        put("..--.-", "_");
        put(".-..-.", "\"");
        put("...-..-", "$");
        put(".--.-.", "@");
        put("...---...", "SOS");
        put(" ", " ");
    }};

}


