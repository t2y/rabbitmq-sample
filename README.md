# rabbitmq-sample

RabbitMQ tutorial sample

* https://www.rabbitmq.com/tutorials/tutorial-one-java.html
* https://github.com/rabbitmq/rabbitmq-tutorials/tree/master/java

## Preparation

```bash
$ docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.9-management
```

* https://www.rabbitmq.com/download.html
* https://registry.hub.docker.com/\_/rabbitmq/

access to http://localhost:15672/ , then you can log in by `guest/guest`.

## How to build

```bash
$ mvn compile
```

## How to run

* Consumer

```bash
$ mvn exec:java -Dexec.mainClass="org.example.rabbitmq.sample.Recv"
```

* Producer

```bash
$ mvn exec:java -Dexec.mainClass="org.example.rabbitmq.sample.Send"
```

To confirm messages via `rabbitmqctl`, like this.

```bash
$ docker exec -it 9031af4c30b6 /opt/rabbitmq/sbin/rabbitmqctl list_queues
Timeout: 60.0 seconds ...
Listing queues for vhost / ...
name	messages
hello	3
```
