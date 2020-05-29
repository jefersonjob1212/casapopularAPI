# casapopularAPI
##### API de cadastro para famílias que desejam casas populares

Este repositório é uma criação de uma API utilizando arquitetura RESTful para salvar cadastros
de famílias que estão esperando por uma casa popular. A base de dados se encontra em um cluster
na nuvem utilizando MongoDB.

### Dependências do projeto:

- [Spring](https://spring.io/)
- [MongoDB Atlas](https://www.mongodb.com/cloud/atlas)

### Para rodar localmente
- Linha de comando Windos 
1. Acessar o diretório onde foi baixado
2. Executar os seguintes comandos
```
# caso não tenha o Apache Maven configurado
mvnw.cmd clean install
mvnw.cmd spring-boot:run

# caso tenha o Apache Maven configurado no Sistema Operacional
mvn clean install
mvn spring-boot:run
```

- Linha de comando Linux
1. Acessar o diretório onde foi baixado
2. Executar os seguintes comandos:

```
# caso não tenha o Apache Maven configurado
./mvnw clean install
./mvnw spring-boot:run

# caso tenha o Apache Maven configurado no Sistema Operacional
mvn clean install
mvn spring-boot:run
```
