# Kafka producer

## Key points

**What happens after producerRecord is sent?

    Step #1: Once we send the ProducerRecord, the first thing the producer will do is serialize the key and value objects to ByteArrays so they can be sent over the network
    Step #2: Data is sent to partitioner, If we specified a partition in the ProducerRecord, the partitioner doesn’t do anything and simply returns the partition we specified.
             If we didn’t, the partitioner will choose a partition for us, usually based on the ProducerRecord key.
    Step #3: Adds the record to a batch of records that will also be sent to the same topic and partition
    Step #4: A separate thread is responsible for sending those batches of records to the appropriate Kafka brokers.
    Step #5: Broker receives the messages, it sends back a response.
             Successful : it will return a RecordMetadata object with the topic, partition, and the offset of the record within the partition.
             Failed: it will return a error code, producer may retry sending few more times before giving up and returning an error.


**When will a produced message will be ready to consume?**

    Messages written to the partition leader are not immediately readable by consumers regardless of the producer's acknowledgement settings.
    When all in-sync replicas have acknowledged the write, then the message is considered committed, which makes it available for reading.
    This ensures that messages cannot be lost by a broker failure after they have already been read.

**What are the producer key configuration?**

    key configurations are explined here : https://docs.confluent.io/current/clients/producer.html

**key.serializer**

    producer interface allows user to provide the key.serializer.
    Avilable serializers: ByteArraySerializer, StringSerializer and IntegerSerializer.
    setting key.serializer is required even if you intend to send only values.

**value.serializer**

    producer interface allows user to provide the key.serializer. like the same way as key.serializer.

**retries**

    Setting a value greater than zero will cause the client to resend any record whose send fails with a potentially transient error. Note that this retry is no different than if the client resent the record upon receiving the error. Allowing retries without setting max.in.flight.requests.per.connection to 1 will potentially change the ordering of records because if two batches are sent to a single partition, and the first fails and is retried but the second succeeds, then the records in the second batch may appear first. Note additionall that produce requests will be failed before the number of retries has been exhausted if the timeout configured by delivery.timeout.ms expires first before successful acknowledgement. Users should generally prefer to leave this config unset and instead use delivery.timeout.ms to control retry behavior.
    default: 2147483647
    By default, the producer will wait 100ms between retries, but you can control this using the retry.backoff.ms parameter.

**acks**

    If acks=0, the producer will not wait for a reply from the broker before assuming the message was sent successfully.
    If acks=1, the producer will receive a success response from the broker the moment the leader replica received the message
                If the client uses callbacks, latency will be hidden, but throughput will be limited by the number of in-flight messages (i.e., how many messages the producer will send before receiving replies from the server).
    If acks=all, the producer will receive a success response from the broker once all in-sync replicas received the message.
                if acks is set to all, the request will be stored in a buffer called purgatory until the leader observes that the follower replicas replicated the message, at which point a response is sent to the client

**buffer.memory**

    This sets the amount of memory the producer will use to buffer messages waiting to be sent to brokers.
    If messages are sent by the application faster than they can be delivered to the server, the producer may run out of space and additional send() calls will either block or throw an exception, based on the block.on.buffer.full parameter (replaced with max.block.ms in release 0.9.0.0, which allows blocking for a certain time and then throwing an exception).

**batch.size**

    When multiple records are sent to the same partition, the producer will batch them together.
    This parameter controls the amount of memory in bytes (not messages!) that will be used for each batch.
    When the batch is full, all the messages in the batch will be sent. However, this does not mean that the producer will wait for the batch to become full.
    The producer will send half-full batches and even batches with just a single message in them.
    Therefore, setting the batch size too large will not cause delays in sending messages; it will just use more memory for the batches.
    Setting the batch size too small will add some overhead because the producer will need to send messages more frequently.

**linger.ms**

    linger.ms controls the amount of time to wait for additional messages before sending the current batch.
    KafkaProducer sends a batch of messages either when the current batch is full or when the linger.ms limit is reached.
    By default, the producer will send messages as soon as there is a sender thread available to send them, even if there’s just one message in the batch.
    By setting linger.ms higher than 0, we instruct the producer to wait a few milliseconds to add additional messages to the batch  before sending it to the brokers.
    This increases latency but also increases throughput (because we send more messages at once, there is less overhead per message).

**compression.type**

    By default, messages are sent uncompressed
    supported compression types: snappy, gzip and lz4.
    snappy is recommended, with low CPU and good performance and decent comression ratio.
    Gzip use more CPU and time, but result in better compression ratio.

**max.in.flight.requests.per.connection**

    This controls how many messages the producer will send to the server without receiving responses.
    Setting this high can increase memory usage while improving throughput, but setting it too high can reduce throughput as batching becomes less efficient.
    Setting this to 1 will guarantee that messages will be written to the broker in the order in which they were sent, even when retries occur.

**Methods of sending messages**

    * Fire-and-forget :
        We many loose data in this situation. Possbile cases of lossing data : SerializationException when it fails to serialize the message, a BufferExhaustedException or TimeoutException if the buffer is full, or an InterruptException if the sending thread was interrupted.
        not recommended for production use.
    * Synchronous send
        We user Future.get() to wait for a reply from Kafka
    * Asynchronous send
        We call the send() method with a callback function, which gets triggered when it receives a response from the Kafka broker.

**Type of errors**

    Retriable: errors are those that can be resolved by sending the message again.
              For example, a connection error can be resolved because the connection may get reestablished.
              A “no leader” error can be resolved when a new leader is elected for the partition.
              KafkaProducer can be configured to retry those errors automatically.
    non-retriable: For example, “message size too large.” In those cases, KafkaProducer will not attempt a retry and will return the exception immediately.

**Explain bootstrap.servers**

    bootstrap.servers property so that the producer can find the Kafka cluster.

**Explain client.id**

    Although not required, you should always set a client.id since this allows you to easily correlate requests on the broker with the client instance which made it.


**Where can i find the full list of configs documentation?**

    https://docs.confluent.io/current/installation/configuration/producer-configs.html#cp-config-producer

**unclean.leader.election.enable**

    default value is true, this will allow out of sync replicas to become leaders.
    This should be disable in critical applications like banking system managing transactions.

**min.insync.replicas**

    This will ensure the minumm number of replicas are in sync.
    NotEnoughReplicasException will be throw to producer when the in sync replicas are less then what is configured.


