Example application using Spring Data Rest with HATEOAS endpoints and RabbitMQ as a message broker. The repository includes two backend applications that communicate asyncronously between each other through two different queues. A third Dead-Letter queue is used to deliver rejected messages and retry them again after an specified delay. The delay increases after every retry being up to the MAX retries established.

In the web application is possible to do CRUD operations to the books that are persisted using H2 database.

1. Install RabbitMQ server.
2. Install the official RabbitMQ Delayed Message Plugin.
3. Run both backend applications.
4. Deploy frontend webapp to an HTTP server, like http-server from Node.js NPM package manager.