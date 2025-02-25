
# API de Transação

Este projeto é uma API REST desenvolvida com Java e Spring Boot para gerenciar transações e calcular estatísticas das transações realizadas nos últimos 60 segundos. 




## Variáveis de Ambiente

Para rodar esta aplicação, é necessário:

Java: JDK 21 ou superior.
Maven: Versão 3.8.1 ou superior.
Git: Para clonar o repositório.
Docker (opcional): Caso queira rodar a aplicação em um container.




##  Como Configurar o Projeto

1. Clone o Repositório

```bash
git clone https://github.com/lucascidade/desafio-java-itau.git

```

2. Baixe as dependências
Caso não tenha o Gradle instalado no seu sistema, use o wrapper (certifique que está dentro da pasta clonada):
```bash
./gradlew build

```
Caso tenha o Gradle no seu sistema

```bash
gradle build
```

3. Execute o Projeto
```bash
./gradlew bootRun
```
4. Como Rodar em um Container (Opcional)

4.1. Crie a Imagem Docker
Certifique-se de que o Docker está instalado e execute:

```bash
docker build -t api-transacoes 
```

4.2. Execute o Container

```bash
docker run -p 8080:8080 api-transacoes
```

## Documentação da API

#### Receber Transações

```http
  POST /transacao
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `valor` | `BigDecimal` | **Obrigatório.** O valor da transação 
| `dataHora` | `OffsetDateTime` | **Obrigatório.**  O horário que a transação ocorreu


#### Calcular Estatísticas

```http
  GET /estatistica
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `intervaloBusca` | `integer` | **Não Obrigatório.**  O padrão default é 60s  |

#### Limpar Transações

```http
  DELETE /transacao
```




