## Índice
* [Introdução](https://github.com/RuanK07/Utup#API-RedeSocial
)
* [1. Configuração](https://github.com/RuanK07/Utup#1-Configuração)
* [2. Estrutura do Projeto](https://github.com/RuanK07/Utup#2-Estrutura-do-Projeto)
* [3. Endpoints](https://github.com/RuanK07/Utup#3-Endpoints)
* [4. Schema do Banco de Dados](https://github.com/RuanK07/Utup#4-Schema-do-Banco-de-Dados)
* [5. Informacoes adicionais](https://github.com/RuanK07/Utup#5-Informacoes-adicionais)

-------------------------------

# API-RedeSocial

&nbsp;&nbsp;&nbsp;&nbsp;Bem-vindo à documentação do projeto de desenvolvimento de uma rede social
utilizando o framework Spring. Este projeto tem como objetivo criar uma plataforma de rede social
interativa, permitindo aos usuários interagirem, compartilharem informações e estabelecerem 
conexões online de maneira segura e eficiente. A rede social será construída com base nas
tecnologias modernas do ecossistema Spring, aproveitando suas diversas partes para garantir
um desenvolvimento ágil e de alta qualidade.

## Tecnologias Utilizadas:

O projeto utiliza várias partes do framework Spring para oferecer uma experiência completa e confiável aos usuários. Algumas das principais tecnologias e partes do Spring incluem:

 - **Spring Boot**: Utilizado como base para a criação do projeto, o Spring Boot simplifica a configuração e a inicialização, fornecendo um ambiente pronto para o desenvolvimento da aplicação.

- **Spring MVC**: O módulo Spring MVC é utilizado para desenvolver a estrutura da aplicação web, permitindo a definição de controladores, modelos e visualizações para gerenciar as interações dos usuários.

- **Spring Data JPA**: Para lidar com a persistência dos dados, o Spring Data JPA é utilizado, proporcionando uma camada de acesso a dados por meio de abstrações e anotações.

- **Spring Security**: A segurança é uma preocupação fundamental, e o Spring Security é integrado para oferecer autenticação e autorização robustas, garantindo que as interações na rede social sejam seguras e protegidas.

- **PostgreSQL**: O banco de dados PostgreSQL foi escolhido como solução de armazenamento, aproveitando seu suporte a SQL e recursos avançados para garantir a integridade e eficiência dos dados dos usuários.

- **JUnit 5**: O ambiente de teste é fortalecido por meio do framework JUnit 5, permitindo a criação de testes automatizados para validar o funcionamento das funcionalidades e a estabilidade do sistema.

- **Swagger**: O Swagger é integrado ao projeto para fornecer uma documentação interativa da API, permitindo aos desenvolvedores explorar, testar e compreender as funcionalidades da rede social.:

-------------------------------

<br></br>

# 1. Configuração

Para garantir um ambiente de desenvolvimento adequado para o projeto de rede social baseado em Spring, siga estas etapas para configurar todas as ferramentas necessárias, versões do Java, IDE e as dependências relevantes.

## Ambiente de Desenvolvimento:

- Certifique-se de ter o JDK 17 instalado em seu sistema.

- Para a IDE, recomenda-se o Spring Tool Suite (STS) para facilitar o desenvolvimento. Baixe e instale o STS.

## Configuração do Banco de Dados:

- O projeto utiliza o PostgreSQL como banco de dados. Certifique-se de ter o PostgreSQL instalado e configurado na porta padrão (5432).

- Crie um banco de dados chamado "SocialNetworkAPI" no PostgreSQL.

## Configuração do Spring:

- O projeto faz uso de arquivos de configuração .properties. Você pode re-definir as configurações no arquivo application.properties dentro do diretório src/main/resources.


<br></br>



# 2. Estrutura do Projeto

A estrutura do projeto segue uma organização lógica que separa as diferentes responsabilidades e componentes do sistema. Isso facilita a manutenção, escalabilidade e compreensão do código. Aqui está uma visão geral dos principais diretórios, pacotes e módulos presentes no seu projeto:

## socialnetwork:

- **O pacote raiz do projeto contém as classes principais e a configuração geral.**

## socialnetwork.config:

- **Neste pacote, estão as classes relacionadas à configuração geral do projeto.**
- **SecurityConfig: Configuração de segurança com Spring Security.**
- **exceptionConfig: Configuração de tratamento de exceções.**

## socialnetwork.controller:

- **Este pacote contém os controladores da aplicação, que são responsáveis por receber as requisições HTTP e delegar as ações apropriadas.**
- **Existem controladores para várias partes da aplicação, como autenticação, autorização, comentários, amigos, postagens, perfis, área do usuário, entre outros.**

## socialnetwork.dto:

- **Neste pacote, estão as classes DTO (Data Transfer Object) que são usadas para transferir dados entre as camadas da aplicação.**

## socialnetwork.enumerated:

- **Contém enumerações utilizadas na aplicação para representar valores fixos ou categorias.**

## socialnetwork.filter:

- **Aqui estão filtros que podem ser aplicados nas requisições, permitindo a customização do comportamento das solicitações HTTP.**

## socialnetwork.model:

- **Esse pacote contém as classes de modelos que representam as entidades no banco de dados.**

## socialnetwork.repository:

- **Os repositórios JPA que lidam com a persistência das entidades no banco de dados.**

## socialnetwork.service:

- **Este é um pacote crucial, onde a maioria da lógica de negócios reside.**
- **As subpastas e pacotes dentro deste diretório refletem diferentes regras de negócios e entidades que o sistema gerencia.**
- **Por exemplo, commentService, friendService, postService, profileService, userAreaService e userService representam diferentes serviços de negócios.**

## socialnetwork.util:

- **Este pacote contém classes utilitárias, como classes para manipulação de datas, formatação e outros utilitários gerais.**

## src/main/resources:

- **Aqui estão os recursos da aplicação, incluindo arquivos de configuração, mensagens localizadas e diretórios estáticos para recursos web.**

## src/test/java:

- **Contém os testes unitários e de integração da aplicação. Cada pacote de serviços e controladores possui seus próprios testes.**

## mvnw, mvnw.cmd, pom.xml:

- **Esses arquivos fazem parte da estrutura do Maven e são usados para gerenciar dependências, compilar e construir o projeto.**

<br></br>


# 3. Endpoints

<table>
  <tr><th> Requisições </th></tr>
  <tr><td>

  |Endpoint | Tipo|  Descrição|
  |--|--|--|
  | /auth | POST| Authenticate in the system |
  | /users/register | POST | Sign up in the System |
  | /users | GET | Find all The Users from the System |
  | /users/{id} | GET | Find an User from the System by ID |
  | /friends/create/friend | POST| Create Friend |
  | /friends/allfriends | GET| Get a Friend list by User Nickname |
  | /profile/photo | PUT | Update Profile Photo |
  | /profile/biography | PUT | Update Profile Biography |
  | /profile/userprofile | GET | Get a Profile by User's Nickname |
  | /posts/subtitle | PUT | Update Post Subtitle |
  | /posts/create/post | POST | Create Post |
  | /posts | GET | Get a Post list by Subtitle |
  | /posts/profile | GET | Get a Post list by User's Nickname |
  | /comments/edit | PUT | Update Comment by its ID |
  | /comments/create/comment | POST | Create Comment |
  | /comments/post | GET | Get a Comment list by Post ID |
  | /userarea/changepassword | PUT | Change the Acccount Password |
  | /userarea/myprofile | GET | Access the Logged User Informations |

</td></tr> </table>

<br></br>


# 4. Schema do Banco de Dados

<img>
  <p align="center"> 
    <b>Diagrama de Entidade Relacional</b> 
  </p> 
</img>

<img src="back-end-socialnetwork/social-network/social-network/src/main/resources/static/images/der.png">

<br></br>

# 5. Informacoes adicionais:

Sub-pastas do projeto = 122 pastas

Classes do projeto = 117 classes

Linhas de codigo do projeto = 5271 linhas

Link index do Swagger: /utup-api/swagger-ui/index.html



# Autor

Ruan Kennedy Freire Mendes

www.linkedin.com/in/ruan-kennedy-b1b084242
