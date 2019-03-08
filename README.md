Arquivo executável da API para o curso de persistência web no
Android.

## Pré-requisitos para execução

Client do Java, de preferência versão 8. Caso não tenha [baixe-o e o instale](https://www.java.com/en/download/).

## Como executar a API

Vá até o diretório onde o arquivo **server.jar** está localizado e execute o comando `java -jar server.jar`. Aguarde a execução até que apresente a mensagem de inicialização do Tomcat na porta 8080.

## Banco de dados

Não há necessidade de instalar banco de dados para a API, pois foi configurada para utilizar o HSQLDB via arquivo.

Essa configuração cria o diretório **database** no mesmo local do arquivo e persiste todos os dados dentro dele. Isso significa que se o diretório **database** for removido ou modificado de local, os dados não serão acessíveis.

## Executando em outras portas

Caso seja necessária a execução em outra porta, utilize o seguinte comando `java -jar -Dserver.port=8081 server.jar`, por exemplo. O valor `8081` pode variar de acordo com as portas disponíveis da máquina que executar o arquivo.

## End Points configurados

- `/produto`:
  - **GET**: devolve lista com todos os produtos.
  - **POST**: salva produto e o retorna com o id gerado.

- `/produto/{id}`: as operações são feitas apenas com ids existentes.
  - **PUT**: altera produto e o retorna com as alterações realizadas
  - **DELETE**: remove produto

> Ids enviados como atributo do objeto via body são ignorados.


O objeto de um produto segue o seguinte modelo:

```
{
    "id": "1",
    "nome": "Bola de futebol i5",
    "preco": "99.99",
    "quantidade": "5"
}
```

## Dicas e sugestões

Execute o servidor apenas via command line ou terminal, pois é possível visualizar o log de inicialização caso apresente um problema. Além disso é mais fácil de encerrá-lo.
