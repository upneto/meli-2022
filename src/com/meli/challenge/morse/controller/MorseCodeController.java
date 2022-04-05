package com.meli.challenge.morse.controller;

import com.meli.challenge.morse.controller.rest.payload.morsecode.MorseCodeRequest;
import com.meli.challenge.morse.controller.rest.payload.morsecode.MorseCodeResponse;
import com.meli.challenge.morse.resource.exception.AppException;
import com.meli.challenge.morse.resource.exception.BusinessException;
import com.meli.challenge.morse.service.MorseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MorseCodeController {

    @Autowired
    private MorseCodeService morseCodeService;

    /**
     * Traduz texto para morse
     * @param request
     * @return
     * @throws BusinessException
     * @throws AppException
     */
    @PostMapping("/2morse")
    public MorseCodeResponse toMorse(@RequestBody MorseCodeRequest request) throws BusinessException, AppException {
        String morseCode = this.morseCodeService.decodeTextToMorse(request.getText());
        return new MorseCodeResponse(200, morseCode);
    }

    /**
     * Traduz codigo morse para texto
     * @param request
     * @return
     * @throws BusinessException
     * @throws AppException
     */
    @PostMapping("/2text")
    public MorseCodeResponse toText(@RequestBody MorseCodeRequest request) throws BusinessException, AppException {
        String text = this.morseCodeService.decodeMorseToText(request.getText());
        return new MorseCodeResponse(200, text);
    }

    /**
     * Traduz sequencia de bits em codigo morse
     * @param request
     * @return
     * @throws BusinessException
     * @throws AppException
     */
    @PostMapping("/bits2morse")
    public MorseCodeResponse bitsToMorse(@RequestBody MorseCodeRequest request) throws BusinessException, AppException {
        String text = this.morseCodeService.decodeBitsToMorse(request.getText());
        return new MorseCodeResponse(200, text);
    }

    /**
     * Traduz texto em bits
     * @param request
     * @return
     * @throws BusinessException
     * @throws AppException
     */
    @PostMapping("/text2bits")
    public MorseCodeResponse text2bits(@RequestBody MorseCodeRequest request) throws BusinessException, AppException {
        String text = this.morseCodeService.decodeTextToBits(request.getText());
        return new MorseCodeResponse(200, text);
    }
}
