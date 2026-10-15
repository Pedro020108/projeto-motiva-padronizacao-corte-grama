# MOTIVA — API de Monitoramento de Rodovias

## Sobre o projeto

Este projeto foi desenvolvido como parte do Sprint 4 da FIAP, dando continuidade ao sistema MOTIVA criado nas etapas anteriores.

A aplicação foi transformada em uma API REST utilizando Spring Boot, permitindo cadastrar, consultar, atualizar e excluir informações sobre trechos de rodovias, equipes de manutenção e intervenções operacionais.

O sistema também gera relatórios de prioridade com base no nível de vegetação dos trechos e armazena o histórico dos relatórios no banco de dados Oracle.

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Maven
- Oracle Database
- Postman para testes da API

## Organização do projeto

O código está organizado em camadas, separando as responsabilidades:

- `model`: entidades que representam os dados do sistema.
- `repository`: interfaces de acesso aos dados utilizando Spring Data JPA.
- `service`: regras de negócio e validações.
- `controller`: endpoints responsáveis pelas requisições HTTP.
- `exception`: tratamento centralizado de exceções.

## Como executar o projeto

### Pré-requisitos

- Java 17 ou versão compatível com a configuração do projeto.
- Maven instalado ou utilização do Maven Wrapper incluído no repositório.
- Banco de dados Oracle acessível e configurado.

### Configuração do banco de dados

Configure a conexão com o Oracle no arquivo `src/main/resources/application.properties`, utilizando as credenciais do seu ambiente local.

O arquivo `application-example.properties` pode ser utilizado como referência para as propriedades necessárias.

**Importante:** não publique senhas, tokens ou outras credenciais reais no GitHub.

### Executar com Maven

Na pasta raiz do projeto, onde está localizado o `pom.xml`, execute:

```bash
mvn spring-boot:run
```

No Windows, se preferir utilizar o Maven Wrapper incluído no projeto:

```powershell
.\mvnw.cmd spring-boot:run
```

Também é possível executar a classe principal `MotivaApplication` pela IDE.

Após a inicialização, a API estará disponível em:

`http://localhost:8080`

A porta poderá variar conforme a configuração da aplicação.

## Endpoints da API

### Trechos de rodovia

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/trechos` | Lista todos os trechos |
| GET | `/api/trechos/{id}` | Consulta um trecho pelo ID |
| POST | `/api/trechos` | Cadastra um trecho |
| PUT | `/api/trechos/{id}` | Atualiza um trecho |
| DELETE | `/api/trechos/{id}` | Exclui um trecho |
| GET | `/api/trechos/umidade/{trechoUmido}` | Filtra trechos pela condição de umidade |

### Equipes de manutenção

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/equipes` | Lista todas as equipes |
| GET | `/api/equipes/{id}` | Consulta uma equipe pelo ID |
| POST | `/api/equipes` | Cadastra uma equipe |
| PUT | `/api/equipes/{id}` | Atualiza uma equipe |
| DELETE | `/api/equipes/{id}` | Exclui uma equipe |
| GET | `/api/equipes/buscar?nome={nome}` | Busca equipes pelo nome |

### Intervenções operacionais

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/intervencoes` | Lista todas as intervenções |
| GET | `/api/intervencoes/{id}` | Consulta uma intervenção pelo ID |
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
| GET | `/api/relatorios/{id}` | Consulta um relatório pelo ID |
| GET | `/api/relatorios/periodo?inicio={data}&fim={data}` | Consulta relatórios por período |

Para consultar relatórios por período, utilize datas no formato ISO, por exemplo: `2026-10-01T00:00:00`.

Os endpoints acima documentam as rotas previstas na aplicação. Consulte os controllers correspondentes caso alguma rota tenha sido alterada durante o desenvolvimento.

## Regras de prioridade

A classificação considera o nível de vegetação registrado em cada trecho:

- **URGENTE:** acima de 45 cm.
- **CRÍTICO:** acima de 30 cm até 45 cm.
- **ATENÇÃO:** acima de 25 cm até 30 cm.
- **NORMAL:** até 25 cm.

Os relatórios contabilizam os trechos de cada categoria e registram a data de geração.

## Exemplos de requisições cURL

Os exemplos podem ser executados no terminal com a aplicação em funcionamento e o banco de dados configurado. Os IDs utilizados devem corresponder aos registros existentes no ambiente de teste.

### 1. Cadastrar um trecho

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

### 2. Listar os trechos

```bash
curl http://localhost:8080/api/trechos
```

### 3. Consultar um trecho pelo ID

```bash
curl http://localhost:8080/api/trechos/1
```

### 4. Atualizar um trecho

```bash
curl -X PUT http://localhost:8080/api/trechos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "kmInicial": 10,
    "kmFinal": 15,
    "nivelVegetacao": 20,
    "trechoUmido": false
  }'
```

Confira os campos exigidos pela entidade e pelo controller antes de executar o exemplo.

### 5. Excluir um trecho

```bash
curl -X DELETE http://localhost:8080/api/trechos/1
```

### 6. Cadastrar uma equipe

```bash
curl -X POST http://localhost:8080/api/equipes \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Equipe de Teste",
    "qtdFuncionarios": 4
  }'
```

### 7. Cadastrar uma intervenção

Utilize um trecho que já exista no banco de dados.

```bash
curl -X POST http://localhost:8080/api/intervencoes \
  -H "Content-Type: application/json" \
  -d '{
    "tipo": "Rocada Mecanizada",
    "trechoId": 1
  }'
```

### 8. Gerar um relatório de prioridade

```bash
curl -X POST http://localhost:8080/api/relatorios
```

### 9. Consultar o histórico de relatórios

```bash
curl http://localhost:8080/api/relatorios
```

### 10. Consultar um relatório pelo ID

```bash
curl http://localhost:8080/api/relatorios/1
```

### 11. Consultar relatórios por período

```bash
curl -G http://localhost:8080/api/relatorios/periodo \
  --data-urlencode "inicio=2026-10-01T00:00:00" \
  --data-urlencode "fim=2026-10-31T23:59:59"
```

Os exemplos demonstram como montar as requisições. Os códigos HTTP e os resultados efetivos devem ser confirmados executando os testes contra a aplicação.

## Tratamento de erros

A aplicação possui tratamento de exceções para recursos não encontrados e dados inválidos, com respostas HTTP apropriadas conforme a implementação dos controllers e do tratamento global de erros.

## Testes e evidências

Os endpoints devem ser testados com o Postman ou com requisições cURL, verificando os códigos HTTP e os resultados retornados.

Para a entrega acadêmica, mantenha registros de pelo menos seis requisições funcionando, contemplando operações CRUD e geração ou consulta de relatórios. As capturas de tela ou registros devem demonstrar as requisições realizadas e suas respectivas respostas.

## Aprendizados

O desenvolvimento deste projeto permitiu aprofundar o uso do Spring Boot, a organização de uma API REST em camadas, o acesso ao Oracle com Spring Data JPA, a criação de consultas derivadas nos repositórios, as validações de negócio e o tratamento de exceções.

## Autores

- Pedro Mirabella Tonarque
- Pedro Henrique Marques
- Enzo Moretto
- João Ivo Campolina
- Lucas de Oliveira Scovini
- Guilherme Mota Melo
