## API de estoque de produtos

Essa é uma aplicação web em Spring Boot destinada ao curso de persistência web no Android. O objetivo dela é realizar um CRUD de produtos via persistência em banco de dados. O projeto foi desenvolvido com base no Gradle utilizando Kotlin e Java 8.

## Como importar e modificar o código

> Para modificar o código, recomendo que utilize o IntelliJ (pode ser o Community), pois ele já vem com o plugin do Gradle por padrão. Fique à vontade em usar sua ferramenta preferida, certifique-se que ela dê suporte ao Gradle. Evite o uso do Gradle instalado no seu computador, utilize o [Gradle Wrapper](https://medium.com/collabcode/gradle-nativo-ou-wrapper-saiba-qual-utilizar-e029058bf80) para que mantenha a mesma versão do build.

Ao clonar o projeto, você pode importá-lo como um projeto Gradle, siga as orientações da IDE até que o build seja finalizado. Todo código fonte vai estar acessível no diretório **src/main/kotlin**.

## Executando o projeto

O projeto pode ser executado a partir da função `main` do arquivo `src/main/kotlin/br/com/alura/estoqueapi/EstoqueApiApplication.kt`, por padrão, ela ficará disponível na porta 8080 sendo acessível via endereço http://localhost:8080/.

Caso tenha interesse em modificar a porta, vá no arquivo **/src/main/resources/application-producao.yml** e altere o valor da propriedade `server.port`, segue o exemplo:

```yml
server:
  port: ${port:8081}
```

Nessa amostra a execução do servidor vai ser feita na porta 8081.

## Mapeamentos disponíveis

Por padrão, o projeto provê quatro mapeamentos via arquitetura REST:

- **GET** `/produto` -> Devolve a lista com todos os produtos salvos.

- **POST** `/produto` -> Salva o produto com base no modelo recebido via body e devolve o produto com id gerado.

- **PUT** `/produto/{id}` -> Altera o produto com base no id e modelo recebido via body.

- **DELETE** `/produto/{id}` -> Remove o produto com base no id e modelo recebido via body.

Segue o modelo de JSON esperado pelas requisições:

```json
{
	"nome" : "Computador",
	"preco" : "2199.99",
	"quantidade" : 3
}
```

> Para mais detalhes de implementação, consulte o código dos controllers da aplicação.

## Dúvidas sobre o Spring Boot

Caso não conheça o mínimo do Spring Boot com Kotlin e tenha interesse em aprender, sugiro os conteúdos que mantenho para a comunidade que estão acessíveis nestes links:

- [CRUD API parte 1](https://medium.com/collabcode/implementando-uma-crud-api-no-spring-boot-com-kotlin-parte-1-c6e281d0f8f8);

- [CRUD API parte 2](https://medium.com/collabcode/implementando-uma-crud-api-no-spring-boot-com-kotlin-parte-2-3346312dc956);

- [Boas práticas para API com Spring](https://medium.com/collabcode/boas-pr%C3%A1ticas-para-a-implementa%C3%A7%C3%A3o-de-apis-no-spring-boot-com-kotlin-6e77aac110da).
