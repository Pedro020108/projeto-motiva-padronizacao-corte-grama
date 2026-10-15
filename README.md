# MOTIVA — API de Monitoramento de Rodovias

## Sobre o projeto

Este projeto foi desenvolvido como parte do Sprint 4 da FIAP, dando continuidade ao sistema MOTIVA criado na etapa anterior.

A proposta foi transformar a aplicação em uma API REST usando Spring Boot, permitindo consultar, cadastrar, atualizar e excluir informações sobre trechos de rodovias, equipes de manutenção e intervenções operacionais.

O sistema também gera relatórios de prioridade com base no nível de vegetação de cada trecho e armazena o histórico dos relatórios no banco de dados Oracle.

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Maven
- Oracle Database
- Postman para testes da API

## Organização do projeto

O código está separado por responsabilidade:

- `model`: classes que representam as entidades do sistema.
- `repository`: interfaces responsáveis pelo acesso aos dados usando Spring Data JPA.
- `service`: regras de negócio e validações.
- `controller`: endpoints que recebem as requisições HTTP.
- `exception`: tratamento de erros da aplicação.

## Como executar o projeto

1. Instale o Java 17 ou uma versão compatível com a configuração do projeto.
2. Configure o acesso ao banco Oracle no arquivo `application.properties`.
3. Abra o projeto na IDE ou pelo Maven.
4. Execute a classe `MotivaApplication`.
5. Com a aplicação em execução, utilize o Postman para acessar os endpoints.

A API utiliza, por padrão, o endereço `http://localhost:8080`.

**Importante:** não publique senhas ou outras credenciais do banco no GitHub.

## Endpoints

### Trechos de rodovia

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/trechos` | Lista todos os trechos |
| GET | `/api/trechos/{id}` | Busca um trecho pelo ID |
| POST | `/api/trechos` | Cadastra um trecho |
| PUT | `/api/trechos/{id}` | Atualiza um trecho |
| DELETE | `/api/trechos/{id}` | Exclui um trecho |
| GET | `/api/trechos/umidade/{trechoUmido}` | Filtra os trechos pela condição de umidade |

### Equipes de manutenção

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/equipes` | Lista todas as equipes |
| GET | `/api/equipes/{id}` | Busca uma equipe pelo ID |
| POST | `/api/equipes` | Cadastra uma equipe |
| PUT | `/api/equipes/{id}` | Atualiza uma equipe |
| DELETE | `/api/equipes/{id}` | Exclui uma equipe |
| GET | `/api/equipes/buscar?nome={nome}` | Busca equipes pelo nome |

### Intervenções operacionais

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/intervencoes` | Lista todas as intervenções |
| GET | `/api/intervencoes/{id}` | Busca uma intervenção pelo ID |
| POST | `/api/intervencoes` | Cadastra uma intervenção |
| PUT | `/api/intervencoes/{id}` | Atualiza uma intervenção |
| DELETE | `/api/intervencoes/{id}` | Exclui uma intervenção |
| GET | `/api/intervencoes/tipo?valor={tipo}` | Busca intervenções pelo tipo |
| GET | `/api/intervencoes/trecho/{trechoId}` | Lista intervenções de um trecho |

### Relatórios de prioridade

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/relatorios` | Gera e salva um relatório |
| GET | `/api/relatorios` | Lista os relatórios salvos |
| GET | `/api/relatorios/{id}` | Busca um relatório pelo ID |
| GET | `/api/relatorios/periodo?inicio={data}&fim={data}` | Consulta relatórios por período |

Para consultar relatórios por período, utilize datas no formato ISO, por exemplo: `2026-10-01T00:00:00`.

## Regras de prioridade

A classificação considera o nível de vegetação registrado em cada trecho:

- **URGENTE:** acima de 45 cm.
- **CRÍTICO:** acima de 30 cm até 45 cm.
- **ATENÇÃO:** acima de 25 cm até 30 cm.
- **NORMAL:** até 25 cm.

Os relatórios contabilizam os trechos de cada categoria e registram a data de geração.

## Exemplos de requisições

Os exemplos abaixo podem ser utilizados no Postman ou no terminal, com a aplicação em execução.

### Cadastrar um trecho

```bash
curl -X POST http://localhost:8080/api/trechos \
  -H "Content-Type: application/json" \
  -d '{
    "kmInicial": 10,
    "kmFinal": 15,
    "nivelVegetacao": 32,
    "trechoUmido": true
  }'
```

### Listar os trechos

```bash
curl http://localhost:8080/api/trechos
```

### Cadastrar uma equipe

```bash
curl -X POST http://localhost:8080/api/equipes \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Equipe de Teste",
    "qtdFuncionarios": 4
  }'
```

### Cadastrar uma intervenção

Utilize o ID de um trecho que já exista no banco.

```bash
curl -X POST http://localhost:8080/api/intervencoes \
  -H "Content-Type: application/json" \
  -d '{
    "tipo": "Rocada Mecanizada",
    "trechoId": 1
  }'
```

### Gerar um relatório

```bash
curl -X POST http://localhost:8080/api/relatorios
```

### Consultar os relatórios

```bash
curl http://localhost:8080/api/relatorios
```

## Tratamento de erros

A aplicação possui tratamento para recursos não encontrados e dados inválidos, retornando respostas HTTP apropriadas para esses casos.

## Testes

Os endpoints foram testados no Postman, incluindo operações de consulta, cadastro, atualização, exclusão e geração de relatórios.

## Aprendizados

O desenvolvimento deste projeto permitiu aprofundar o uso do Spring Boot, a organização de uma API em camadas, o acesso ao banco Oracle com Spring Data JPA e a criação de consultas a partir dos métodos dos repositórios.

Também foi possível trabalhar com validações de negócio, tratamento de exceções e testes de requisições HTTP.

## Autor

Pedro Mirabella Tonarque
Pedro Henrique Marques
Enzo Moretto
João Ivo Campolina
Lucas de Oliveira Scovini
Guilherme Mota Melo