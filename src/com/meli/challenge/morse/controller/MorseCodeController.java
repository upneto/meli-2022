package com.meli.challenge.morse.controller;

import com.meli.challenge.morse.controller.rest.payload.morsecode.MorseCodeRequest;
import com.meli.challenge.morse.controller.rest.payload.morsecode.MorseCodeResponse;
import com.meli.challenge.morse.resource.exception.AppException;
import com.meli.challenge.morse.resource.exception.BusinessException;
import com.meli.challenge.morse.service.MorseCodeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Traduz texto para morse")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna codigo morse"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping(value="/2morse", produces="application/json", consumes="application/json")
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
    @ApiOperation(value = "Traduz morse para texto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna texto"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping(value="/2text", produces="application/json", consumes="application/json")
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
    @ApiOperation(value = "Traduz sequencia de bits em morse")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna morse"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping(value="/bits2morse", produces="application/json", consumes="application/json")
    public MorseCodeResponse bitsToMorse(@RequestBody MorseCodeRequest request) throws BusinessException, AppException {
        String text = this.morseCodeService.decodeBitsToMorse(request.getText());
        return new MorseCodeResponse(200, text);
    }

}
