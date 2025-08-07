# Projeto Certification NLW - Trilha Java

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

## 📖 Sobre o Projeto

**`certification`** é uma API REST desenvolvida em Java com Spring Boot durante o evento **Next Level Week (NLW) Journey** da Rocketseat.

O objetivo do projeto é construir um sistema de certificações, onde estudantes podem realizar provas sobre tecnologias específicas e obter uma certificação. A aplicação gerencia perguntas, alternativas e os resultados das certificações dos estudantes.

## 🚀 Tecnologias Utilizadas

Este projeto foi construído com as seguintes tecnologias:

- **Java 17**: Versão LTS do Java.
- **Spring Boot 3.2.2**: Framework para criação de aplicações Java de forma rápida e robusta.
- **Spring Boot Starter Web**: Para construir APIs RESTful.
- **Spring Data JPA**: Para persistência de dados e comunicação com o banco.
- **PostgreSQL**: Banco de dados relacional utilizado no projeto.
- **Maven**: Gerenciador de dependências e build do projeto.
- **Lombok**: Para reduzir código boilerplate (getters, setters, construtores, etc).

## ✅ Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:
- JDK 17 ou superior.
- Maven 3.8 ou superior.
- Uma instância do PostgreSQL rodando.
- Um cliente de API como Postman ou Insomnia para testar os endpoints.

## ⚙️ Como Executar

1. **Clone o repositório:**
   ```bash
   git clone <URL_DO_SEU_REPOSITORIO>
   cd certification
   ```

2. **Configure o banco de dados:**
   - Crie um banco de dados no seu PostgreSQL (ex: `db_certification`).
   - No diretório `src/main/resources/`, crie o arquivo `application.properties`.
   - Adicione as seguintes configurações ao arquivo, substituindo pelos seus dados:
     ```properties
     # Configuração do DataSource para PostgreSQL
     spring.datasource.url=jdbc:postgresql://localhost:5432/db_certification
     spring.datasource.username=seu_usuario_postgres
     spring.datasource.password=sua_senha_postgres

     # Configuração do Hibernate (JPA)
     # 'update' atualiza o schema do banco de dados automaticamente ao iniciar a aplicação
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Execute a aplicação:**
   Utilize o Maven para compilar e iniciar o projeto.
   ```bash
   mvn spring-boot:run
   ```

4. **Acesse a aplicação:**
   A API estará disponível em `http://localhost:8080`. Você pode usar um cliente de API para interagir com os endpoints que você desenvolver.
