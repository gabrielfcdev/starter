# Starter Social Network

**Descrição:**  
O **Starter Social Network** é uma plataforma de rede social com o objetivo de, no futuro, se tornar uma rede de relacionamentos robusta. O projeto permite que os usuários se conectem, compartilhem informações e interajam em um ambiente seguro e amigável. Atualmente, a aplicação oferece funcionalidades como criação de posts, comentários, curtidas, envio de mensagens e adição de amigos.


## Índice

1. [Tecnologias Utilizadas](#tecnologias-utilizadas)  
2. [Funcionalidades](#funcionalidades)  
3. [Instalação](#instalação)  
   - [Pré-requisitos](#pré-requisitos)  
   - [Passos](#passos)  
4. [Contribuição](#contribuição)  
5. [Licença](#licença)  
6. [Contato](#contato)  

---

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.  
- **Spring Boot**: Framework para construção de aplicações Java.  
- **Spring Data JPA**: Para interagir com o banco de dados.  
- **MySQL**: Sistema de gerenciamento de banco de dados relacional.  
- **Maven**: Gerenciador de dependências.  

---

## Funcionalidades

- [x] Cadastro de usuários  
- [x] Criação de posts  
- [x] Curtidas e comentários em posts  
- [x] Envio de mensagens diretas  
- [x] Adição de amigos  
- [ ] Notificações em tempo real (planejado)  
- [ ] Personalização de perfis (planejado)  

---

## Instalação

### Pré-requisitos

- **JDK 11** ou superior  
- **Maven**  
- **MySQL**  

### Passos

1. Clone o repositório:  
   ```bash
   git clone https://github.com/seu-usuario/starter.git
   cd starter-social-network
Configure o banco de dados MySQL:

Crie um novo banco de dados:
sql
Copiar código
CREATE DATABASE starter;
Atualize as credenciais do banco de dados no arquivo application.properties:
properties
Copiar código
spring.datasource.url=jdbc:mysql://localhost:3306/starter
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
Compile o projeto:

bash
Copiar código
mvn clean install
Execute a aplicação:

bash
Copiar código
mvn spring-boot:run
Acesse a aplicação no navegador:

arduino
Copiar código
http://localhost:8080
Contribuição
Contribuições são bem-vindas! Para contribuir:

Faça um fork do repositório.
Crie uma branch para sua feature:
bash
Copiar código
git checkout -b minha-feature
Commit suas mudanças:
bash
Copiar código
git commit -m "Adicionei nova funcionalidade"
Envie a branch:
bash
Copiar código
git push origin minha-feature
Abra um Pull Request.
Licença
Este projeto está licenciado sob a MIT License. Veja o arquivo LICENSE para mais detalhes.

Contato
Email: contato@gfctech.com.br
LinkedIn: Gabriel Calixto
Desenvolvido com por Gabriel Calixto.

markdown
Copiar código

---

### **Alterações e Melhorias Realizadas:**
1. **Estrutura organizada:** Adicionado um índice para facilitar a navegação.  
2. **Correção de formatação:** Ajustei códigos, listas e seções para manter o padrão Markdown.  
3. **Clareza nas instruções:** Detalhei os passos de instalação, como configurar o banco de dados e rodar a aplicação.  
4. **Funcionalidades:** Especifiquei as funcionalidades implementadas e planejadas.  
5. **Contato e Licença:** Incluí informações claras e acessíveis.  







