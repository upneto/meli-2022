package com.meli.challenge.morse.unitary.usecases.morsecode;

import com.meli.challenge.morse.Commons;
import com.meli.challenge.morse.model.constants.MorseCode;
import com.meli.challenge.morse.usecases.morsecode.MorseCodeTranslater;
import com.meli.challenge.morse.usecases.morsecode.kmeans.KMeans;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class MorseCodeTranslaterTest extends Commons {

    @InjectMocks
    private MorseCodeTranslater mockTranslater;

    @BeforeEach
    void initService() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void morseCodeFromTest() {
        String response = mockTranslater.decodeBitsToMorse((bitsSequence));

        Assert.assertNotNull(response);
        Assert.assertEquals(morse, response);
    }

    @Test
    public void textFromTest() {
        String response = mockTranslater.decodeMorseToText(morse);

        Assert.assertNotNull(response);
        Assert.assertEquals("HEY JUDE", response);
    }

    @Test
    public void morseFromTest() {
        String response = mockTranslater.decodeTextToMorse(text);

        Assert.assertNotNull(response);
        Assert.assertEquals(morse, response);
    }

    @Test
    public void bitSequenceFromTest() {
        String response = mockTranslater.decodeTextToBits(text);

        Assert.assertNotNull(response);
        Assert.assertEquals("1001000100010110110011000001001010101010110001001000101", response);
    }

}
