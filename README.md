# MELI - morse
Desafio MELI 2022 - Conversor de códigos MORSE

### Documentação de referência

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/maven-plugin/)
* [Wikipedia - Morse Code](https://en.wikipedia.org/wiki/Morse_code)
* [Doc - International Timing](https://morsecode.world/international/timing.html)
* [Desafio Codewars Conversão Bits em Morse](https://www.codewars.com/kata/decode-the-morse-code-for-real/)

### Pré requisitos e configurações

Para que a API do MELI possa ser iniciada será necessária a instalação de algumas dependências:

- a máquina virtual do [Java 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html), o [Maven](https://maven.apache.org/download.cgi) e o [Docker](https://www.docker.com/get-started/)

Obs.: O Maven não possui instalador e deverá ser configurado nas [variáveis de ambiente](https://www.baeldung.com/install-maven-on-windows-linux-mac) do SO que estiver utilizando.

Após os pré-requisitos instalados, navegar até o diretório do projeto e executar o comando abaixo:

	mvn clean install

O comando acima irá criar uma imagem Docker. Para iniciar a aplicação execute o comando abaixo:

	docker run --name meli-morse -p 8080:8080 meli-morse:0.0.1


### End Points da API


[https://morse-1648997229537.azurewebsites.net/2text](https://morse-1648997229537.azurewebsites.net/2text)
Faz a tradução de morse para texto:

	POST: { "text": ".... --- .-.. .-   -- . .-.. .." } 

[https://morse-1648997229537.azurewebsites.net/2morse](https://morse-1648997229537.azurewebsites.net/2morse)
Faz a tradução de uma sequencia de bits para morse:
    
    POST: { "text": "0000000011011010011100000110000001111110100111110011111100000000000111011111111011111011111000000101100011111100000111110011101100000100000" }

[https://morse-1648997229537.azurewebsites.net/2text](https://morse-1648997229537.azurewebsites.net/2text)
Faz a tradução de morse para texto:

    POST: { "text": ".... . -.--   .--- ..- -.. ." }
