package com.meli.challenge.morse.usecases.morsecode;

import com.meli.challenge.morse.model.constants.MorseCode;
import com.meli.challenge.morse.usecases.morsecode.kmeans.KMeans;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

@Service
public class MorseCodeTranslater {

    private float stripMim;
    private float stripMax;

    private String[] ones;
    private String[] zeros;

    /**
     * Traduz sequencia de digitos para texto
     * @param bitsSequence
     * @return String (codigo morse)
     */
    public String decodeBitsToMorse(String bitsSequence) {
        bitsSequence = this.clean(bitsSequence);
        this.splitBitSequence(bitsSequence);
        this.setStripsOfTime(bitsSequence);
        return this.decode(bitsSequence);
    }

    /**
     * Retorna texto
     *
     * @param morseCode
     * @return String (texto)
     */
    public String decodeMorseToText(String morseCode) {
        StringBuilder results = new StringBuilder();
        morseCode = morseCode.trim().replaceAll(" {3}", MorseCode.Signal.WORD_SEPARATOR_TOKEN);
        Scanner sc = new Scanner(morseCode);
        while (sc.hasNext()) {
            String nxt = sc.next();
            if (nxt.equals(MorseCode.Signal.WORD_SEPARATOR_TOKEN.trim())) {
                results.append(MorseCode.Signal.CHAR_SEPARATOR);
            }
            else {
                results.append(MorseCode.DICTIONARY.get(nxt));
            }
        }
        return results.toString();
    }

    /**
     * Retorna texto a partir de morse
     * @param text
     * @return
     */
    public String decodeTextToMorse(String text) {
        StringBuilder morse = new StringBuilder();
        for (Character character : text.toCharArray()) {
            Stream<String> key = MorseCode.DICTIONARY.entrySet().stream()
                    .filter(entry -> character.toString().equalsIgnoreCase(entry.getValue()))
                    .map(Map.Entry::getKey);
            if(!morse.toString().isEmpty()) {
                morse.append(MorseCode.Signal.CHAR_SEPARATOR);
            }
            morse.append(key.findFirst().get());
        }
        return morse.toString();
    }

    /**
     * Retorna bitSequence a partir do texto
     * @param text
     * @return
     */
    public String decodeTextToBits(String text) {
        StringBuilder bitSequence = new StringBuilder();
        for (Character character : text.toCharArray()) {
            bitSequence.append(String.format("%8s", Integer.toBinaryString(character)));
        }
        return bitSequence.toString().replaceAll(MorseCode.Signal.CHAR_SEPARATOR, MorseCode.Signal.CHAR_EMPTY);
    }

    /**
     * Limpa sequencia de bits removendo zeros sobresalentes do início e final da String
     * @param bitsSequence
     * @return String corrigida
     */
    private String clean(String bitsSequence) {
        return bitsSequence.replaceAll("^[0]+", "").replaceAll("[0]+$", "");
    }

    /**
     * Separa sequencia em duas listas: zeros e ones
     * @param bitsSequence
     */
    private void splitBitSequence(String bitsSequence){
        ones = bitsSequence.split("0+");
        zeros = bitsSequence.split("1+");
    }

    /**
     * Obtem faixas de tempo para conversão dos bits (sinais)
     * @param bitsSequence
     */
    private void setStripsOfTime(String bitsSequence) {
        KMeans km = new KMeans(zeros, ones);
        km.makeGrouping();

        stripMim = (km.getTimeUnit(0) + km.getTimeUnit(1)) / 2;
        stripMax = ((km.getTimeUnit(1) + km.getTimeUnit(2)) / 2) * 1.2f; //Ajuste de tempo de digitação padrão
    }

    /**
     * Decodifica a sequencia de bits em texto
     *
     * @param bitsSequence
     * @return
     */
    private String decode(String bitsSequence) {
        String morse = "";
        for (int i = 0; i < zeros.length - 1; i++) {
            morse += this.getMorseCode(ones[i], zeros[i + 1]);
        }
        if (ones[0].length() > 0) {
            morse += this.getMorseCode(ones[ones.length - 1]);
        }
        return morse;
    }

    /**
     * Retorna caracter morse de acordo com a faixa de tempo.
     *
     * @param one
     * @param zero
     * @return String Morse Code
     */
    private String getMorseCode(String one, String zero) {
        // Se sequencia ONES menor ou igual ao intervalo minimo é ponto caso contrário traço
        String code = one.length() <= stripMim ? MorseCode.Signal.DIT : MorseCode.Signal.DAH;

        // Se sequencia ZEROS dentro do intervalo de tempo concatena espaço de separação de caracter
        if ((zero.length() >= stripMim) && (zero.length() < stripMax)) {
            code += MorseCode.Signal.CHAR_SEPARATOR;
        }
        // Se sequencia ZEROS maior que intervalo maximo concatena separador de palavras
        else if (zero.length() >= stripMax) {
            code += MorseCode.Signal.WORD_SEPARATOR;
        }
        return code;
    }

    /**
     * Retorna caracter morse de acordo com a faixa de tempo.
     *
     * @param one
     * @@return String Morse Code
     */
    private String getMorseCode(String one) {
        return one.length() <= stripMim ? MorseCode.Signal.DIT : MorseCode.Signal.DAH;
    }


}
