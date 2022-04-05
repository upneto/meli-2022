package com.meli.challenge.morse.controller.rest.payload.morsecode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MorseCodeResponse {

    @JsonProperty(value= "code")
    private int code;

    @JsonProperty(value= "response")
    private String response;

}
