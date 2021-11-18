# BuildersCRM-api

** Tecnologias utilizadas: **

Backend: Java 11, SpringBoot, Maven, Swagger.

Banco de dados: H2 Database.

Demais tecnologias: Docker, Postman.

Requisitos para executar o projeto:

1. Maven
2. Docker

** Configuração: **

1. Faça o clone do projeto no servidor/maquina.
2. Abra um terminal/CMD e navegue até o diretório do projeto.
3. Execute o comando: mvn clean package
4. Ao término do comando acima, será gerada uma versão da aplicação que ficará localizada no diretório /target
5. Execute o comando: docker-compose up --build --force-recreate
6. O comando acima se encarregará de baixar e carregar o que for necessário para a execução da aplicação.
7. Para realizar os testes, basta importar o arquivo Builders.postman_collection.json no Postman que o mesmo contém todas as URLs do projeto.

Caso queria ler a documentação dos endpoints, acesse o endereço http://localhost:8080/swagger-ui.html#/cliente-controller

Para console do banco de dados, acesse o endereço http://localhost:8082/ com os dados abaixo:
JDBC URL: jdbc:h2:crmApi_db
User Name: sa
Password: 1234

Este projeto também está hospedado em um provedor cloud que é possível executa-lo através do endereço http://builders-crm.herokuapp.com/clientes 

** Sobre o projeto: **

O projeto foi desenvolvido seguindo o padrão MVC, no qual dentro do pacote controller contém a classe responsável por "orquestrar" o fluxo da aplicação. Nesta classe recebemos as requisições e realizamos os devidos tratamentos.
É possível notar que em alguns métodos há a anotação @Cacheable que é responsável por manter os dados que foram tratados/buscados do banco em cache, assim caso tenha uma nova requisição buscando pelos mesmos dados, não será necessário ir no banco novamente buscá-los.
Há também em alguns métodos a anotação @CacheEvict que é responsável por limpar os dados em cache, garantindo assim que os dados sempre estarão atualizados quando estiverem em cache.
O pacote controller.form contém as classes responsáveis por validar se os dados enviados na requisição estão de acordo com o esperado. Caso os dados não estejam de acordo com o esperado, há um handler chamado "ErroDeValidacaoHandler" que é responsável por tratar a mensagem de erro e retornar para quem fez a requisição.
E no pacote controller.dto contém a classe responsável por "transformar" os dados e retornar para quem fez a requisição.
No pacote repository contém a interface responsável por receber os dados, realizar a devida transação no banco e retornar os dados.
No pacote model contém a classe Cliente, nessa classe utilizei a anotação @Data do pacote (lombok.Data) para que o mesmo fique encarregado de gerar os getters e setters, ficando assim mais limpo o código.
No pacote config.swagger contém a classe de configuração do Swagger para que o mesmo possa realizar a "varredura" do código e gerar a documentação dos endpoints.

Dentro de main/src/resources possui 3 application.properties, sendo 1 default, 1 para os testes e 1 para produção.

Por fim, dentro de src/test/java possuem as classes de teste do JUnit contendo alguns testes para validar se a aplicação está se comportando do modo esperado.