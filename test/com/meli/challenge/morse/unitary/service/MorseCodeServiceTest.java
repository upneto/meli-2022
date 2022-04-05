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
        Mockito.when(mockTranslater.morseCodeFrom(Mockito.anyString())).thenReturn(Commons.mockReturn);
        String response = service.decodeTextToMorse(Mockito.anyString());

        Assert.assertNotNull(response);
        Assert.assertEquals(Commons.mockReturn, response);
    }

    @Test
    public void  decodeMorseToTextTest() {
        Mockito.when(mockTranslater.textFrom(Mockito.anyString())).thenReturn(Commons.mockReturn);
        String response = service.decodeMorseToText(Mockito.anyString());

        Assert.assertNotNull(response);
        Assert.assertEquals(Commons.mockReturn, response);
    }

    @Test
    public void  decodeBitsToMorseTest() {
        Mockito.when(mockTranslater.morseFrom(Mockito.anyString())).thenReturn(Commons.mockReturn);
        String response = service.decodeBitsToMorse(Mockito.anyString());

        Assert.assertNotNull(response);
        Assert.assertEquals(Commons.mockReturn, response);
    }

    @Test
    public void  decodeMorseToBitsTest(){
        Mockito.when(mockTranslater.bitSequenceFrom(Mockito.anyString())).thenReturn(Commons.mockReturn);
        String response = service.decodeMorseToBits(Mockito.anyString());

        Assert.assertNotNull(response);
        Assert.assertEquals(Commons.mockReturn, response);
    }

}
