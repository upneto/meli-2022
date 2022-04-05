package com.meli.challenge.morse.unitary.controller;

import com.meli.challenge.morse.Commons;
import com.meli.challenge.morse.controller.MorseCodeController;
import com.meli.challenge.morse.controller.rest.payload.morsecode.MorseCodeRequest;
import com.meli.challenge.morse.controller.rest.payload.morsecode.MorseCodeResponse;
import com.meli.challenge.morse.resource.exception.AppException;
import com.meli.challenge.morse.service.MorseCodeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MorseCodeControllerTest extends Commons {

    @Mock
    private MorseCodeService mockService;

    @InjectMocks
    private MorseCodeController controller;

    @BeforeEach
    void initService() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void toMorseTest() throws AppException {
        Mockito.when(mockService.decodeTextToMorse(Mockito.anyString())).thenReturn(Commons.mockReturn);
        MorseCodeResponse response = controller.toMorse(new MorseCodeRequest(Mockito.anyString()));

        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getCode());
        Assert.assertEquals(Commons.mockReturn, response.getResponse());
    }

    @Test
    public void toTextTest() throws AppException {
        Mockito.when(mockService.decodeMorseToText(Mockito.anyString())).thenReturn(Commons.mockReturn);
        MorseCodeResponse response = controller.toText(new MorseCodeRequest(Mockito.anyString()));

        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getCode());
        Assert.assertEquals(Commons.mockReturn, response.getResponse());
    }

    @Test
    public void bitsToMorseTest() throws AppException {
        Mockito.when(mockService.decodeBitsToMorse(Mockito.anyString())).thenReturn(Commons.mockReturn);
        MorseCodeResponse response = controller.bitsToMorse(new MorseCodeRequest(Mockito.anyString()));

        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getCode());
        Assert.assertEquals(Commons.mockReturn, response.getResponse());
    }

    @Test
    public void morseToBitsTest() throws AppException {
        Mockito.when(mockService.decodeMorseToBits(Mockito.anyString())).thenReturn(Commons.mockReturn);
        MorseCodeResponse response = controller.morseToBits(new MorseCodeRequest(Mockito.anyString()));

        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getCode());
        Assert.assertEquals(Commons.mockReturn, response.getResponse());
    }
}
