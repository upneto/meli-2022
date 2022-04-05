package com.meli.challenge.morse.unitary.service;

import com.meli.challenge.morse.Commons;
import com.meli.challenge.morse.controller.rest.payload.morsecode.MorseCodeRequest;
import com.meli.challenge.morse.controller.rest.payload.morsecode.MorseCodeResponse;
import com.meli.challenge.morse.service.MorseCodeService;
import com.meli.challenge.morse.usecases.morsecode.MorseCodeTranslater;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class MorseCodeServiceTest  extends Commons {

    @InjectMocks
    private MorseCodeService service;

    @Mock
    private MorseCodeTranslater mockTranslater;

    @BeforeEach
    void initService() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void decodeTextToMorseTest() {
        Mockito.when(mockTranslater.decodeTextToMorse(Mockito.anyString())).thenReturn(text);
        String response = service.decodeTextToMorse(Mockito.anyString());

        Assert.assertNotNull(response);
        Assert.assertEquals(text, response);
    }

    @Test
    public void  decodeMorseToTextTest() {
        Mockito.when(mockTranslater.decodeMorseToText(Mockito.anyString())).thenReturn(text);
        String response = service.decodeMorseToText(Mockito.anyString());

        Assert.assertNotNull(response);
        Assert.assertEquals(text, response);
    }

    @Test
    public void  decodeBitsToMorseTest() {
        Mockito.when(mockTranslater.decodeBitsToMorse(Mockito.anyString())).thenReturn(text);
        String response = service.decodeBitsToMorse(Mockito.anyString());

        Assert.assertNotNull(response);
        Assert.assertEquals(text, response);
    }

    @Test
    public void  decodeTextToBitsTest(){
        Mockito.when(mockTranslater.decodeTextToBits(Mockito.anyString())).thenReturn(text);
        String response = service.decodeTextToBits(Mockito.anyString());

        Assert.assertNotNull(response);
        Assert.assertEquals(text, response);
    }

}
