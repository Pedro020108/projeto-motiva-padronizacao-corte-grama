# MOTIVA — API de Monitoramento de Rodovias

## Sobre o projeto

O MOTIVA é um sistema de monitoramento de rodovias desenvolvido para auxiliar no gerenciamento de trechos rodoviários, equipes de manutenção e intervenções operacionais.

Nesta Sprint 4, o projeto foi migrado de uma aplicação baseada em console e JDBC para uma **API REST desenvolvida com Spring Boot e Spring Data JPA**, mantendo a integração com o banco de dados Oracle utilizado nas etapas anteriores.

A aplicação permite cadastrar, consultar, atualizar e excluir registros, além de gerar relatórios de prioridade com base no nível de vegetação dos trechos monitorados.

## Objetivos

- Desenvolver uma API REST utilizando Spring Boot.
- Aplicar o padrão de arquitetura em camadas.
- Utilizar Spring Data JPA para persistência de dados.
- Integrar a aplicação ao banco de dados Oracle.
- Implementar operações CRUD para trechos, equipes e intervenções.
- Gerar e armazenar relatórios de prioridade.
- Validar dados e tratar exceções da aplicação.

## Tecnologias utilizadas

- **Java 17**
- **Spring Boot 4.1.1**
- **Spring Web**
- **Spring Data JPA**
- **Maven**
- **Oracle Database**
- **Oracle JDBC Driver**
- **Postman** para testes dos endpoints
- **Git e GitHub** para versionamento do projeto

## Arquitetura do projeto

O projeto está organizado em camadas, separando as responsabilidades para facilitar a manutenção e a evolução do sistema.

```text
src/main/java/br/motiva/
├── MotivaApplication.java
├── model/
├── repository/
├── service/
├── controller/
└── exception/
```

### Responsabilidades das camadas

- **Model:** representa as entidades persistidas no banco de dados.
- **Repository:** utiliza Spring Data JPA para executar operações de persistência e consultas.
- **Service:** concentra as regras de negócio e as validações.
- **Controller:** disponibiliza os endpoints REST e recebe as requisições HTTP.
- **Exception:** contém o tratamento centralizado de exceções da API.

## Banco de dados

A aplicação utiliza o banco de dados Oracle e as tabelas criadas nas sprints anteriores.

As principais tabelas utilizadas são:

| Tabela | Responsabilidade |
|---|---|
| `TRECHO_RODOVIA` | Armazena os trechos monitorados e seus níveis de vegetação. |
| `EQUIPE_MANUTENCAO` | Armazena as equipes responsáveis pela manutenção. |
| `INTERVENCAO_OPERACIONAL` | Registra as intervenções associadas aos trechos. |
| `RELATORIO_PRIORIDADE` | Armazena o histórico dos relatórios gerados. |

A persistência é realizada por meio do Spring Data JPA, com entidades mapeadas para as tabelas existentes e sequências Oracle para a geração dos identificadores.

### Configuração do banco

A configuração da conexão deve ser realizada no arquivo `src/main/resources/application.properties`.

Exemplo de configuração:

```properties
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

**Importante:** substitua os valores de usuário e senha pelas suas credenciais. Não publique informações de acesso ao banco de dados no GitHub.

O projeto utiliza `spring.jpa.hibernate.ddl-auto=none` para evitar que o Hibernate tente criar ou alterar automaticamente as tabelas existentes.

## Como executar o projeto

### Pré-requisitos

- Java 17 instalado.
- Maven instalado ou utilização do Maven Wrapper do projeto.
- Acesso ao banco de dados Oracle utilizado pela aplicação.
- IDE de sua preferência, como IntelliJ IDEA, Eclipse ou Visual Studio Code.

### Passo 1 — Clonar o repositório

```bash
git clone URL_DO_SEU_REPOSITORIO
cd motiva
```

Substitua `URL_DO_SEU_REPOSITORIO` pelo endereço real do repositório no GitHub.

### Passo 2 — Configurar o banco de dados

Configure o arquivo `src/main/resources/application.properties` com as credenciais do Oracle.

### Passo 3 — Executar a aplicação

Pelo Maven:

```bash
mvn spring-boot:run
```

Caso o projeto tenha o Maven Wrapper no Windows:

```bash
mvnw.cmd spring-boot:run
```

### Passo 4 — Testar a API

Após iniciar a aplicação, os endpoints estarão disponíveis, por padrão, em:

`http://localhost:8080`

As requisições podem ser realizadas pelo Postman ou por ferramentas de linha de comando, como o cURL.

## 📡 Endpoints da API

### 1. Trechos rodoviários

| Método HTTP | Endpoint | Descrição |
|---|---|---|
| GET | `/api/trechos` | Lista todos os trechos. |
| GET | `/api/trechos/{id}` | Consulta um trecho pelo identificador. |
| POST | `/api/trechos` | Cadastra um novo trecho. |
| PUT | `/api/trechos/{id}` | Atualiza um trecho existente. |
| DELETE | `/api/trechos/{id}` | Exclui um trecho. |
| GET | `/api/trechos/umidade/{trechoUmido}` | Consulta trechos pelo indicador de umidade. |

Exemplo de corpo para cadastro:

```json
{
  "kmInicial": 10.0,
  "kmFinal": 15.0,
  "nivelVegetacao": 32.0,
  "trechoUmido": false
}
```

### 2. Equipes de manutenção

| Método HTTP | Endpoint | Descrição |
|---|---|---|
| GET | `/api/equipes` | Lista todas as equipes. |
| GET | `/api/equipes/{id}` | Consulta uma equipe pelo identificador. |
| POST | `/api/equipes` | Cadastra uma equipe. |
| PUT | `/api/equipes/{id}` | Atualiza uma equipe existente. |
| DELETE | `/api/equipes/{id}` | Exclui uma equipe. |
| GET | `/api/equipes/buscar?nome={nome}` | Pesquisa equipes pelo nome. |

Exemplo de corpo para cadastro:

```json
{
  "nome": "Equipe Sul",
  "qtdFuncionarios": 3
}
```

### 3. Intervenções operacionais

| Método HTTP | Endpoint | Descrição |
|---|---|---|
| GET | `/api/intervencoes` | Lista todas as intervenções. |
| GET | `/api/intervencoes/{id}` | Consulta uma intervenção pelo identificador. |
| POST | `/api/intervencoes` | Cadastra uma intervenção. |
| PUT | `/api/intervencoes/{id}` | Atualiza uma intervenção existente. |
| DELETE | `/api/intervencoes/{id}` | Exclui uma intervenção. |
| GET | `/api/intervencoes/tipo?valor={tipo}` | Pesquisa intervenções pelo tipo. |
| GET | `/api/intervencoes/trecho/{trechoId}` | Lista intervenções associadas a um trecho. |

Exemplo de corpo para cadastro:

```json
{
  "tipo": "Poda",
  "trechoId": 1
}
```

O valor de `trechoId` deve corresponder a um trecho existente no banco de dados.

### 4. Relatórios de prioridade

| Método HTTP | Endpoint | Descrição |
|---|---|---|
| POST | `/api/relatorios` | Gera um relatório e registra seu histórico. |
| GET | `/api/relatorios` | Lista os relatórios armazenados. |
| GET | `/api/relatorios/{id}` | Consulta um relatório pelo identificador. |
| GET | `/api/relatorios/periodo?inicio={inicio}&fim={fim}` | Consulta relatórios gerados em um período. |

O relatório contabiliza os trechos de acordo com o nível de vegetação e armazena os resultados no banco de dados.

Para consultar por período, utilize datas e horários no formato ISO, por exemplo:

`/api/relatorios/periodo?inicio=2026-10-01T00:00:00&fim=2026-10-31T23:59:59`

## Regras de prioridade

A classificação dos trechos é definida pelo nível de vegetação:

| Nível de vegetação | Prioridade |
|---|---|
| Maior que 45 | URGENTE |
| Maior que 30 e até 45 | CRÍTICO |
| Maior que 25 e até 30 | ATENÇÃO |
| Menor ou igual a 25 | NORMAL |

A aplicação utiliza essas faixas para contar os trechos em cada categoria durante a geração dos relatórios.

## Exemplos de requisições cURL

Os exemplos abaixo consideram a aplicação executando localmente na porta 8080.

### 1. Listar trechos

```bash
curl http://localhost:8080/api/trechos
```

### 2. Consultar um trecho pelo ID

```bash
curl http://localhost:8080/api/trechos/1
```

### 3. Cadastrar um trecho

```bash
curl -X POST http://localhost:8080/api/trechos \
  -H "Content-Type: application/json" \
  -d '{
    "kmInicial": 10.0,
    "kmFinal": 15.0,
    "nivelVegetacao": 32.0,
    "trechoUmido": false
  }'
```

### 4. Atualizar um trecho

```bash
curl -X PUT http://localhost:8080/api/trechos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "kmInicial": 10.0,
    "kmFinal": 15.0,
    "nivelVegetacao": 40.0,
    "trechoUmido": true
  }'
```

### 5. Excluir um trecho

```bash
curl -X DELETE http://localhost:8080/api/trechos/1
```

**Atenção:** os exemplos de atualização e exclusão utilizam o ID `1` apenas como referência. Para testar, utilize um registro apropriado, pois essas operações alteram os dados persistidos.

### 6. Cadastrar uma equipe

```bash
curl -X POST http://localhost:8080/api/equipes \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Equipe Sul",
    "qtdFuncionarios": 3
  }'
```

### 7. Cadastrar uma intervenção

```bash
curl -X POST http://localhost:8080/api/intervencoes \
  -H "Content-Type: application/json" \
  -d '{
    "tipo": "Poda",
    "trechoId": 1
  }'
```

### 8. Gerar um relatório

```bash
curl -X POST http://localhost:8080/api/relatorios
```

### 9. Listar os relatórios

```bash
curl http://localhost:8080/api/relatorios
```

### 10. Consultar um relatório pelo ID

```bash
curl http://localhost:8080/api/relatorios/1
```

### 11. Consultar relatórios por período

```bash
curl "http://localhost:8080/api/relatorios/periodo?inicio=2026-10-01T00:00:00&fim=2026-10-31T23:59:59"
```

Os IDs utilizados nos exemplos são ilustrativos e devem ser ajustados aos registros existentes no banco de dados.

## Validações e tratamento de erros

A aplicação realiza validações para impedir operações com dados inválidos, como:

- Valores de quilometragem ausentes ou inválidos.
- Quilometragem final menor ou igual à inicial.
- Nível de vegetação negativo.
- Campos obrigatórios não informados.
- Nome de equipe ausente ou quantidade de funcionários inválida.
- Tipo de intervenção ausente ou acima do limite permitido.
- Associação de intervenção a um trecho inexistente.
- Consulta ou alteração de registros que não existem.

O tratamento centralizado de exceções utiliza `@RestControllerAdvice`, retornando respostas HTTP adequadas para os erros tratados.

Entre os códigos HTTP utilizados estão:

| Código | Significado |
|---|---|
| 200 | Requisição executada com sucesso. |
| 201 | Recurso criado com sucesso. |
| 204 | Operação concluída sem conteúdo de resposta, como uma exclusão bem-sucedida. |
| 400 | Requisição inválida ou erro de validação tratado. |
| 404 | Recurso não encontrado. |

## Evidências de testes

Os testes da API podem ser demonstrados por meio de capturas de tela do Postman, mostrando a requisição, a URL, o status HTTP e a resposta recebida.

Recomenda-se registrar pelo menos seis evidências, contemplando:

1. Consulta de trechos com resposta HTTP 200.
2. Cadastro de equipe com resposta HTTP 201.
3. Atualização de equipe com resposta HTTP 200.
4. Exclusão de equipe de teste com resposta HTTP 204.
5. Geração de relatório com resposta HTTP 201.
6. Consulta do histórico de relatórios com resposta HTTP 200.

As capturas devem corresponder a testes realmente executados. Se as evidências forem armazenadas no repositório, podem ser organizadas em uma pasta chamada `evidencias-postman/`.

## Reflexões sobre o desenvolvimento

### 1. Por que utilizar uma interface Repository em vez de implementar todas as operações manualmente?

A interface Repository permite utilizar os recursos do Spring Data JPA para realizar operações de persistência sem precisar implementar manualmente métodos comuns de consulta, inclusão, atualização e exclusão. Ao estender `JpaRepository`, a aplicação recebe essas operações prontas e pode definir consultas adicionais por meio de métodos derivados, reduzindo código repetitivo e facilitando a manutenção.

### 2. O padrão DAO está morto ou apenas mudou de forma?

O padrão DAO não está morto. Seu conceito continua presente na separação entre a lógica de negócio e o acesso aos dados. No projeto, a camada Repository desempenha uma função semelhante, enquanto o Spring Data JPA automatiza grande parte das operações que antes precisariam ser implementadas manualmente.

### 3. Por que a validação `nivelVegetacao >= 0` deve ficar no Service e não no Controller?

Essa validação representa uma regra de negócio e, por isso, deve ficar na camada Service. Dessa forma, a regra é aplicada independentemente do endpoint ou da forma pela qual a operação é chamada. O Controller permanece responsável por receber as requisições e encaminhar as operações, mantendo as responsabilidades separadas.

### 4. Em qual arquivo está o SQL gerado pelo método `findByTipoIgnoreCase()`?

O SQL não precisa ser escrito manualmente em um arquivo específico. O Spring Data JPA interpreta o nome do método `findByTipoIgnoreCase()` declarado na interface Repository e gera a consulta necessária para buscar intervenções pelo tipo, ignorando diferenças entre letras maiúsculas e minúsculas. O Hibernate atua na geração e execução do SQL correspondente.

## Aprendizados

Durante o desenvolvimento, foram trabalhados conceitos de construção de APIs REST, arquitetura em camadas, persistência com Spring Data JPA, integração com Oracle, consultas derivadas, validação de dados, tratamento de exceções e testes de endpoints com o Postman.

A migração também permitiu compreender melhor a diferença entre acessar o banco de dados manualmente e utilizar um framework de persistência para reduzir código repetitivo e organizar as responsabilidades da aplicação.

## Integrantes

- Pedro Mirabella Tonarque
- Pedro Henrique Marques
- Enzo Moretto
- João Ivo Campolina
- Lucas de Oliveira Scovini
- Guilherme Mota Melo
