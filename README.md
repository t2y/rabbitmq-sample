# rabbitmq-sample

RabbitMQ tutorial sample

* https://www.rabbitmq.com/tutorials/tutorial-one-java.html
* https://github.com/rabbitmq/rabbitmq-tutorials/tree/master/java

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
