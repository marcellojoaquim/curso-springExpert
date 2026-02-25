# API Rest Desenvolvida no curso Spring Boot Expert

## Descricao
Neste projeto desenvolvemos um backend completo para gerenciamento de vendas e clientes
onde foi possível implementar vários recursos iportantes para aplicações profissionais
como:
*  Persistencia de dados com JPA e consultas complexas
* Documentação Swagger
* Intrnacionalização com mensagem em diferentes idiomas definidas dinâmicamente
* Configuração de segurança com Spring Security
* Uso de JWT
* Enums
* Exceptions customizadas

Neste projeto adicionalmente foi configurado para que o artefato gerado no Build da aplicação não carregue o 
servidor como embeded, que o servidor seja provido, exceto em ambiente de teste onde o servidor é o TomCat por defult
isto permite que não haja choque entre o servidor onde será hospedada a aplicação ao mesmo tempo que permite que o projeto seja executado
localmente.

## Camadas
* REST -> Contém os controllers, DTO's e APIErrors classe responsável por listar erros em casos onde mais de um ocorrer
* DOMAIN -> Contém as entities seus mapeamentos, interfaces repository das entidades e enums
* CONFIG -> Contém as classes de configuração
* EXCEPTION -> Contém os tratamentos de exceções
* SECURITY -> Contém as classes de segurança JWT e Authfilter
* SERVICE -> Contém as interfaces e implementações de serviços camanda intermediaria entre persistencia e controllers
* VALIDATION -> Onde as validações de regra de negócio ocorrem

## Tecnologias utilizadas
* Java 11
* Spring Boot
* Spring Security
* JWT api
* JPA
* MySQL
* H2 Database
* Swagger UI
* Maven
