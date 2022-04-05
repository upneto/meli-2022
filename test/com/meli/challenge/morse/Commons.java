package com.meli.challenge.morse;

public abstract class Commons {

    public String text = "HEY JUDE";

    public String bitsSequence = "00000000110110100111000001100000011111101001111100" +
            "1111110000000000011101111111101111101111100" +
            "0000101100011111100000111110011101100000100000";

    public String morse = ".... . -.--   .--- ..- -.. .";

    public String[] zeros = bitsSequence.split("1+");
    public String[] ones = bitsSequence.split("0+");
}
