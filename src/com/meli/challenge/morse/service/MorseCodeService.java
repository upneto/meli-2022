package com.meli.challenge.morse.service;

import com.meli.challenge.morse.usecases.morsecode.MorseCodeTranslater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MorseCodeService {

    @Autowired
    private MorseCodeTranslater translater;

    public String decodeTextToMorse(String text) {
        return this.translater.morseCodeFrom(text);
    }

    public String decodeMorseToText(String morse) {
        return this.translater.textFrom(morse);
    }

    public String decodeBitsToMorse(String text) {
        return this.translater.morseFrom(text);
    }

    public String decodeMorseToBits(String morse) {
        return this.translater.bitSequenceFrom(morse);
    }

}
