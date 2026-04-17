# API Mecânica

API responsável por gerenciar o fluxo de trabalho da mecânica de veículos.
Tech Challenge do curso 15SOAT.

## 🗒️ Informações

- [Documentação]()

## 📋 Pré-requisitos

- [JDK 26](https://jdk.java.net/26/)
- [IDE 2026.1](https://www.jetbrains.com/idea/download/)
- [Apache Maven 3.9.11](https://maven.apache.org/download.cgi)

## 🔧 Instalação



## 🌳 Variáveis de ambiente

| Nome                   | Valor |
|------------------------|-------|
| spring.profiles.active | local |

## 📦 Construindo o Pacote

```shell
mvn clean install -DskipTests
```

## 🎬 Executando a Aplicação

```shell
java -jar application/target/api-garage.jar
```

## 👌 Executando os Testes de Unidade

```shell
mvn test
```

## 🍿 Executando Testes de Integração

```shell
mvn test -DintegrationTests
```

## 📄 Swagger

| Ambiente | Url                                                      | 
|----------|----------------------------------------------------------|
| local    | [link](http://localhost:18080/api/swagger-ui/index.html) |
| hml      | [link]()                                                 |

## 📌 Versão

- Usando [SemVer](https://semver.org/lang/pt-BR/) para controle de versão.

## ✒ Autores

- [Rodrigo de Sordi - RM372537](https://github.com/rodsordi)


