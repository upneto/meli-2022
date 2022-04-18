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

A documentação básica foi disponibilizada no endereço abaixo (Swagger):

[https://morse-1648997229537.azurewebsites.net/swagger-ui/index.html](https://morse-1648997229537.azurewebsites.net/swagger-ui/index.html)

### Solução conversão Bits em Morse 

Sequência de Bits original

    0000000011011010011100000110000001111110100111110011111100000000000111011111111011111011111000000101100011111100000111110011101100000100000

1 - Removidos zeros do inicio e final do bit morse		
    
    110110100111000001100000011111101001111100111111000000000001110111111110111110111110000001011000111111000001111100111011000001

2 - Separados valores em sequencias de repetição

    11  11   1    111      11  111111   1  11111       111111  111  11111111  11111   11111    1   11  111111  11111  111     11   1
    0   0  00  00000  000000       0  00     00  00000000000    0         0      0  000000    0  000   00000     00    0  00000

3 - Monta lista com as sequencias de valores		

    [ "11", "0", "11", "0", "1", "00", "111", "00000", "11", "000000", "111111", "0", "1", "00", "11111", "00", "111111",
    "00000000000", "111", "0", "11111111", "0", "11111", "0", "11111", "000000", "1", "0", "11", "000", "111111", "00000",
    "11111", "00", "111", "0", "11", "00000", "1" ]

4 - Agrupa por largura de cada seguencia da lista

    1  - 12x
    2  - 9x
    3  - 4x
    5  - 7x
    6  - 5x
    8  - 1x
    11 - 1x

5 - Cria 3 clusters kmeans nas posições de inicio, meio + 1 e final do agrupamento (1, 7 e 11)
    
Agrupa os valores a partir do grupo de largura definida acima

Cluster_1 = 
    
    [ 1, 1, 1, 1, 1, 1, 1, 1 ,1, 1, 1, 1 ] -> 12 x 1  = 12
    [ 2, 2, 2, 2, 2, 2, 2 , 2, 2 ]         -> 9  x 2  = 18
    [ 3, 3, 3, 3 ]                         -> 4  x 3  = 12
                                      TOTAL =           42
                                      MEDIA = 42 / 25 = 1.68

Cluster_7 = 
    
    [ 5, 5, 5, 5, 5, 5, 5 ] -> 7 x 5 = 35
	[ 6, 6, 6, 6, 6 ]       -> 5 x 6 = 30
	[ 8 ] 					-> 1 x 8 = 8
				           TOTAL =         73
					   MEDIA = 73/13 = 5.6153846    
	
Cluster_11 = 

    [ 11 ] -> 1 x 11 = 11
	           TOTAL = 11
               MEDIA = 11


6 - Existem 2 sinais possiveis no código morse "." e "-" correspontentes à quantidade de 1 na sequencia.
O 0 na sequencia é equivalente a pausa e pode ser responsável pela separação de palavras.

O sinal "." (mais rapido) deve existir entre as médias do Cluster_1 e Cluster_2.
O sinal "-" deve existir entre as médias do Cluster_2 e Cluster_3.
	
- Numero minimo da sequencia no intervalo (média Cluster_1 e Cluster_2) =  3.41
- Numero maximo da sequencia no intervalo (média Cluster_2 e Cluster_3  =  8.30
	

    valor sinal "." 	  = largura sequencia 1 <= 3.41
    Valor sinal "-" 	  = largura sequencia 1  > 3.41	
    Valor separador sinal     = largura sequencia 0  > 3.41 < 8.30 
    Valor separador " "       = largura sequencia 0  > 8.30 

Resultado: ".... . -.--  .--- ..- -.. ."

    [  
        "11",                  = "."
        "0",                   =
        "11",                  = "."
        "0",                   =
        "1",                   = "."
        "00",                  =
        "111",                 = "."
        "00000",               = " "
        "11",                  = "."
        "000000",              = " "
        "111111",              = "-"
        "0",                   =
        "1",                   = "."
        "00",                  =
        "11111",               = "-"
        "00",                  =
        "111111",              = "-"
        "00000000000",         = "  "
        "111",                 = "."
        "0",                   =
        "11111111",            = "-"
        "0",                   =
        "11111",               = "-"
        "0",                   =
        "11111",               = "-"
        "000000",              = " "
        "1",                   = "."
        "0",                   =
        "11",                  = "."
        "000",                 =
        "111111",              = "-"
        "00000",               = " "
        "11111",               = "-"
        "00",                  =
        "111",                 = "."
        "0",                   =
        "11",                  = "."
        "00000",               = " "
        "1"                    = "."
    ]
