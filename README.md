# Assessoria de Corrida

Sistema de assessoria de corrida desenvolvido em Java, com arquitetura baseada em MVC, persistência de dados em arquivos JSON e foco em operações de cadastro, autenticação, gerenciamento de usuários e treinos.

O projeto foi criado como prática de modelagem orientada a objetos, organização em camadas e manipulação de dados locais sem banco relacional.

- Cadastro e login de usuários
- Associação de alunos com professores
- Registro e consulta de treinos
- Armazenamento de dados em arquivos JSON
- Senhas protegidas por hashing (jBCrypt)

## Funcionalidades
- Cadastro de alunos, professores e administradores 
- Login de usuários
- Geração de hash de senha com jBCrypt
- Persistência de dados em arquivos JSON
- Cadastro e consulta de treinos
- Associação entre alunos, professores e treinos
- Visualização de dashboards no console
- Geração e validação de códigos para cadastro de administradores
- Controle de administrador raiz


## Regras de negócio
- Toda entidade principal herda da classe abstrata `Pessoa`
- Um `Treino`pertence a um único `Aluno` e a um único `Professor`
- Um `Aluno` e um `Professor` podem estar relacionados a vários treinos
- O cadastro de administrador depende de um código de administrador válido
- Existe a figura de um administrador raiz, responsável por gerar novos códigos administrativos
- Os dados são persistidos localmente em arquivos JSON
- Senhas não são armazenadas em texto puro, apenas em hash

## Entidades principais
### `Pessoa`
Classe abstrata base do sistema.

Atributos comum:

- `id`
- `nome`
- `email`
- `cpf`
- `idade`
- `telefone`
- `hashProvider`
- `contatoEmergencia`
- `infoMedica`

### `Aluno`
Representa o aluno da assessoria.

Responsabilidades:

- visualizar e alterar seus próprios dados
- visualizar treinos associados

### `Professor`
Representa o professor responsável pelos treinos.

Responsabilidades:

- manipular treinos
- acompanhar alunos vinculados aos treinos

### `Administrador`
Responsável por operações administrativas do sistema.

Responsabilidades:

- visualizar entidades cadastradas
- gerar códigos de administrador (somente o administrador raiz)
- controlar operações administrativas

Atributos adicionais:

- `idCodigoAdministrador`
- `adminRaiz`

### `Treino`
Representa um treino prescrito.

Atributos principais:
- `id`
- `idAluno`
- `idProfessor`
- `planoSemanal`

Relacionamentos:
- pertence a um aluno
- pertence a um professor

### `CodigoAdministrador`
Entidade auxiliar responsável por controlar códigos usados no cadastro de administradores.

Atributos principais:
- `id`
- `usado`
- `ativo`

## Estrutura do projeto
O projeto está organizado com base no padrão MVC:

### `model`
Contém as entidades principais e classes auxiliares do domínio.

Exemplo:
- `model.entidades`
- `model.dto`
- `model.dao`

### `view`
Responsável pela interação com o usuário via console.

Exemplos:

- menus
- leitura de dados
- exibição de dashboards

### `controller`
Intermedia a comunicação entre view e service, e também, trata as exceptions lançadas do service.

### `Service`
Contém as regras de negócio do sistema.

Exemplos:

- validações
- logins
- cadastro
- geração de códigos
- montagem de objetos detalhados para exibição

### `helpers`
Classes utilitárias de apoio.

Exemplos:

- leitura de entrada
- geração de hash
- formatação
- montagem de dados de cadastro
- geração de id específico para cada entidade

## Persistência em JSON
Os dados são armazenados localmente em arquivos JSON.

Exemplos de arquivos:
- `administrador.json`
- `alunos.json`
- `professores.json`
- `treinos.json`

## Estratégia de persistência
- As entidades principais são persistidas em JSON
- Relações entre entidades são armazenadas preferencialmente por ID
- Para exibição em console, o sistema monta objetos mais ricos a partir desses IDs

## Tecnologias e bibliotecas
- `Java 21`
- `Maven`
- `Jackson para serialização e desserialização JSON`
- `jBCrypt para hashing de senhas`

## Como executar

### Pré-requisitos
- Java 21 ou superior
- Maven instalado

### Passos
1. Clone o repositório:
```bash
https://github.com/duxxie/Assessoria-Corrida.git
```
2. Acesse a pasta do projeto

3. Compile o projeto:
```bash
mvn clean compile
```

4. Execute a aplicação pela sua IDE ou pela classe principal do projeto

## Objetivos do projeto
Este projeto foi desenvolvido com foco em:

- prática de orientação a objetos em Java
- aplicação do padrão MVC
- persistência local com JSON
- organização de regras de negócio em services
- evolução gradual da modelagem com refatoração contínua

## Melhorias futuras
- refinamento das DTOs de entrada e saída
- expansão das exceptions personalizadas
- melhoria da modelagem de relacionamentos
- separação mais forte entre entidades persistidas e objetos de exibição
- novos dashboards no console
- testes automatizados

## Autor
Desenvolvido por Gustavo Falcão.

