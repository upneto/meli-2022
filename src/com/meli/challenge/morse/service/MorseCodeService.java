package com.meli.challenge.morse.service;

import com.meli.challenge.morse.usecases.morsecode.MorseCodeTranslater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MorseCodeService {

    @Autowired
    private MorseCodeTranslater translater;

    public String decodeBitsToMorse(String bits) {
        return this.translater.decodeBitsToMorse(bits);
    }

    public String decodeMorseToText(String morse) {
        return this.translater.decodeMorseToText(morse);
    }

    public String decodeTextToMorse(String text) {
        return this.translater.decodeTextToMorse(text);
    }

    public String decodeTextToBits(String morse) {
        return this.translater.decodeTextToBits(morse);
    }

}
