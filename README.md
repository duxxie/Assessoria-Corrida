# Assessoria de Corrida

### Uma consultoria de corrida desenvolvida em Java, baseada no padrão MVC, com operações completas de CRUD e persistência em JSON. 

## 🚩Informações Gerais

- Cadastro e login de usuários
- Associação de alunos com professores
- Registro e consulta de treinos
- Armazenamento de dados em arquivos JSON
- Senhas protegidas por hashing (jBCrypt)

## Classes e Relacionamentos

#### `Pessoa` (classe abstrata)
- Superclasse de `Aluno`, `Professor` e `Administrador`

#### `Administrador`
- Herda `Pessoa`
- Visualiza todas as entidades do sistema

#### `Professor`
- Herda `Pessoa`
- Manipula os treinos
- Vários alunos associados

#### `Aluno`
- Herda `Pessoa`
- Possui lista de treinos

#### `Treino`
- Associado ao aluno e ao professor

## Relações
- Associações entre `Aluno` e `Professor`

- Herança entre `Pessoa` e classes filhas

- Composição com classes auxiliares

## Como executar o projeto
### Requisitos:
- Java 21 ou superior
- Maven Instalado

### Passos para rodar
- Clone o repositório git clone https://github.com/duxxie/Assessoria-Corrida.git em sua IDE
- Compile em uma JVM

### Referências e Recursos
- Jackson - FasterXML GitHub

- jBCrypt - Jeremy H GitHub
