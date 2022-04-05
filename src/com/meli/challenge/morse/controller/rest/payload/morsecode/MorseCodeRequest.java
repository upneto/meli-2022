package com.meli.challenge.morse.controller.rest.payload.morsecode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MorseCodeRequest {

    @JsonProperty(value= "text")
    private String text;

}
