# Demo 

Esse projeto foi gerado pelo Srping Initializer.
(https://start.spring.io/) versão 2.1.4.RELEASE.

## Atenção

Se você chegou até aqui é porque já seguiu o README.md do repositório Desafio-Lanches-Frontend. Caso
não saiba do que se trata, neste mesmo github existe um repositório entitulado Desafio-Lanches-Frontend. Entre lá
e leia as intruções.

## Premissa do Problema

Somos uma startup do ramo de alimentos e precisamos de uma aplicação web para gerir nosso negócio. 
Nossa especialidade é a venda de lanches, de modo que alguns lanches são opções de cardápio e outros podem conter 
ingredientes personalizados.

## Concepção do Sistema:

* Criação do repositório GIT;
* Criação do micro serviço utilizando Spring Boot 2.1.4.RELEASE;
* Criação das Entidades;
* Criação dos Endpoints;
* Criação dos Repositorios;
* Implementação da lógica nso métodos das classes de serviços;
* Criação de testes;
* JavaDOCS criando nos métodos e classes necessárias(os);
* Criação e configuração de um container Docker para o servidor;
* Criação e configuração de um Docker-compose, que é o orquestrador dos containers;
* Criação e configuração do script de inicialização;
* Criação e configuração do script de término;

## Necessário

* Java 8+;
* Docker;
* Docker-compose;

## Execução

* No mesmo diretório criado onde foi clonada a parte do frontend clonar o repositório: https://github.com/SamuelBiazotto/Desafio-Lanches-Backend.git;
* Abra um terminal e entre neste diretório clonado;

(Talvez seja necessário dar permissão para executar o comando a seguir)

* Execute: ./build_server_and_start_docker_compose.sh;
* Acessar: 127.0.0.1;

Para Finalizar o serviço execute: (Talvez seja necessário dar permissão para executar o comando a seguir)
 
* ./stop_docker_compose.sh


## Documentação

* JAVADOC
