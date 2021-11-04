# rabbitmq-sample

RabbitMQ tutorial sample

* https://www.rabbitmq.com/tutorials/tutorial-one-java.html
* https://github.com/rabbitmq/rabbitmq-tutorials/tree/master/java

## Preparation

```bash
$ docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.9-management
```

* https://www.rabbitmq.com/download.html
* https://registry.hub.docker.com/_/rabbitmq/

access to http://localhost:15672/ , then you can log in by `guest/guest`.

## How to build

```bash
$ mvn compile
```

## How to run

### Tutorial 1

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
$ docker exec -it ${CONTAINERID} /opt/rabbitmq/sbin/rabbitmqctl list_queues
Timeout: 60.0 seconds ...
Listing queues for vhost / ...
name	messages
hello	3
```

### Tutorial 2

* Consumers

Run processes on multiple terminals.

```bash
$ mvn exec:java -Dexec.mainClass="org.example.rabbitmq.sample.Worker"
```

* Producer

```bash
$ for i in $(seq 1 10); do mvn compile exec:java -Dexec.mainClass="org.example.rabbitmq.sample.NewTask" -Dexec.args="${i} message ..."; done
```

To delete a queue via `rabbitmqctl`, like this.

```bash
$ docker exec -it ${CONTAINERID} /opt/rabbitmq/sbin/rabbitmqctl delete_queue task_queue
Deleting queue 'task_queue' on vhost '/' ...
Queue was successfully deleted with 0 messages
```

To confirm unacknowledged messages in the queue via `rabbitmqctl`, like this.

```bash
$ docker exec -it ${CONTAINERID}  /opt/rabbitmq/sbin/rabbitmqctl list_queues name messages_ready messages_unacknowledged
Timeout: 60.0 seconds ...
Listing queues for vhost / ...
name	messages_ready	messages_unacknowledged
hello	0	0
task_queue	0	3
```

### Tutorial 3

* Consumers

Run processes on multiple terminals.

```bash
$ mvn  exec:java -Dexec.mainClass="org.example.rabbitmq.sample.ReceiveLogs"
```

* Producer

```bash
$ for i in $(seq 1 5); do mvn compile exec:java -Dexec.mainClass="org.example.rabbitmq.sample.EmitLog" -Dexec.args="${i} message ..."; done
```

To confirm exchanges/bindings messages in the queue via `rabbitmqctl`, like this.

```bash
$ docker exec -it ${CONTAINERID} /opt/rabbitmq/sbin/rabbitmqctl list_exchanges
Listing exchanges for vhost / ...
...
logs	fanout
...
```

```bash
$ docker exec -it ${CONTAINERID} /opt/rabbitmq/sbin/rabbitmqctl list_bindings
Listing bindings for vhost /...
source_name	source_kind	destination_name	destination_kind	routing_key	arguments
	exchange	amq.gen-H3M0nn4ECZeI-k4-ZqptiQ	queue	amq.gen-H3M0nn4ECZeI-k4-ZqptiQ	[]
	exchange	hello	queue	hello	[]
	exchange	task_queue	queue	task_queue	[]
	exchange	amq.gen-k-zmbwRfOHV9iuzrPe_DHA	queue	amq.gen-k-zmbwRfOHV9iuzrPe_DHA	[]
	exchange	amq.gen--PTZWKmu0ji8AenOH-m3Gw	queue	amq.gen--PTZWKmu0ji8AenOH-m3Gw	[]
logs	exchange	amq.gen--PTZWKmu0ji8AenOH-m3Gw	queue		[]
logs	exchange	amq.gen-H3M0nn4ECZeI-k4-ZqptiQ	queue		[]
logs	exchange	amq.gen-k-zmbwRfOHV9iuzrPe_DHA	queue		[]
```
