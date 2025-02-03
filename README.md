# Aplicação de Avaliação de Crédito com Microserviços 💳

Este projeto é uma aplicação de **avaliação de crédito** desenvolvida utilizando **microserviços** com **Java**. A aplicação é composta por três microserviços principais: **Cartão**, **Avaliador de Crédito** e **Cliente**. Abaixo você encontrará uma visão geral do funcionamento da aplicação, das tecnologias utilizadas e dos requisitos para rodá-la localmente.

## Funcionalidade 🔄

O fluxo da aplicação se resume da seguinte forma:

1. **Cliente** 👤: O cliente, cujos dados estão armazenados no banco de dados, solicita a avaliação do seu crédito através de uma API REST.
2. **Avaliação de Crédito** 💳: O microserviço de avaliação de crédito realiza a análise do crédito do cliente. Se aprovado, uma ordem de emissão do cartão é enviada para uma fila.
3. **Emissão de Cartão** 🎉: O microserviço de cartões aguarda essa ordem na fila e, quando recebida, emite o cartão e o armazena no banco de dados.

### Autenticação 🔐
Para garantir a segurança da aplicação, o cliente precisa ser autenticado antes de realizar qualquer solicitação. A autenticação é realizada através do **Keycloak**.

### Gateway API 🌐
A comunicação entre os microserviços é feita por meio de um **API Gateway** baseado no **Spring Cloud**, que realiza o balanceamento de carga e o roteamento para os microserviços.

### Mensageria 📬
A comunicação entre os microserviços ocorre por meio de um sistema de **mensageria** utilizando o **RabbitMQ**.

## Tecnologias Utilizadas 🛠️

- **Spring Cloud**: Para o gerenciamento dos microserviços e do API Gateway.
- **RabbitMQ**: Para o sistema de mensageria entre os microserviços.
- **Keycloak**: Para autenticação e controle de acesso.
- **Docker**: Para criação das imagens dos microserviços e do Gateway API.
- **Java**: Linguagem de programação utilizada para implementar os microserviços.

## Arquitetura 🏗️

![microservice-achictecture](https://github.com/user-attachments/assets/9f047574-9471-4814-8d38-9b1edbb00104)


A aplicação segue uma arquitetura baseada em **microserviços**, onde cada microserviço é responsável por uma parte específica do processo de avaliação de crédito:

- **Microserviço de Cliente** 🧑‍💼: Responsável por armazenar e recuperar as informações dos clientes.
- **Microserviço de Avaliação de Crédito** 💳: Realiza a análise do crédito dos clientes.
- **Microserviço de Cartão** 🎟️: Emite os cartões para clientes aprovados.

Todos os microserviços se comunicam, tanto através de serviço de mensageria como por meio de API's REST.
