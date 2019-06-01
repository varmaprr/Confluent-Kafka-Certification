# Kafka Consumer

**What is consumer group?**



**What is consumer?**

    When multiple consumers are subscribed to a topic and belong to the same consumer group, each consumer in the group will receive messages from a different subset of the partitions in the topic.
    If we add more consumers to a single group with a single topic than we have partitions, some of the consumers will be idle and get no messages at all.

**What is rebalance?**

    Moving partition ownership from one consumer to another is called rebalance.
    During a rebalance, consumers can’t consume messages, so a rebalance is basically a short window of unavailability of the entire consumer group.

**What is group coordinator?**

    The way consumers maintain membership in a consumer group and ownership of the partitions assigned to them is by sending heartbeats to a Kafka broker designated as the group coordinator.
    Heartbeats are sent when the consumer polls (i.e., retrieves records) and when it commits records it has consumed.

**what are the consumer mandatory properties**

    bootstrap.servers, key.deserializer and value.deserializer.
    group.id is not mandatory, but in most of the situations it will be populated.

**subscribing to topics**

    subscribe method will take list of topics as parameters like below
        consumer.subscribe(Collections.singletonList("customerCountries"));
    It is also possible to call subscribe with a regular expression.
        consumer.subscribe("test.*");

**poll loops**

    poll loop handles all details of coordination, partition rebalances, heartbeats, and data fetching, leaving the developer with a clean API that simply returns available data from the assigned partitions.
    The parameter we pass, poll(), is a timeout interval and controls how long poll() will block if data is not available in the consumer buffer. If this is set to 0, poll() will return immediately; otherwise, it will wait for the specified number of milliseconds for data to arrive from the broker.
    consumer.poll(100);
    poll() returns a list of records. Each record contains the topic and partition the record came from, the offset of the record within the partition, and of course the key and the value of the record.
    Always close() the consumer before exiting. This will close the network connections and sockets.
    The poll loop does a lot more than just get data. The first time you call poll() with a new consumer, it is responsible for finding the GroupCoordinator, joining the consumer group, and receiving a partition assignment. If a rebalance is triggered, it will be handled inside the poll loop as well. And of course the heartbeats that keep consumers alive are sent from within the poll loop.


**fetch.min.bytes**

    This property allows a consumer to specify the minimum amount of data that it wants to receive from the broker when fetching records.

**fetch.max.wait.ms**

    By setting fetch.min.bytes, you tell Kafka to wait until it has enough data to send before responding to the consumer.
    default value is 500 ms.

**max.partition.fetch.bytes**

    This property controls the maximum number of bytes the server will return per partition.

**session.timeout.ms**

    The amount of time a consumer can be out of contact with the broker while still considered alive defaults to 3 seconds.
    heatbeat.interval.ms must be lower than session.timeout.ms, and is usually set to one-third of the timeout value. So if session.timeout.ms is 3 seconds, heartbeat.interval.ms should be 1 second.

**auto.offset.reset**

    latest or earliest

**enable.auto.commit**

    default is true.
    If you set enable.auto.commit to true, then you might also want to control how frequently offsets will be committed using auto.commit.interval.ms.

**partition.assignment.strategy**

    * Range : default
    * Roundrobin

**client.id**

    This can be any string, and will be used by the brokers to identify messages sent from the client. It is used in logging and metrics, and for quotas.

**max.poll.records**

    This controls the maximum number of records that a single call to poll() will return. This is useful to help control the amount of data your application will need to process in the polling loop.

**receive.buffer.bytes and send.buffer.bytes**

    These are the sizes of the TCP send and receive buffers used by the sockets when writing and reading data. If these are set to -1, the OS defaults will be used. It can be a good idea to increase those when producers or consumers communicate with brokers in a different datacenter, because those network links typically have higher latency and lower bandwidth.

**commits and offsets**

    Kafka stores the offsets in __consumer_offsets topic.
    commitsync() will retry untill it either succeeds or encounters a nonretraiable failure. commitAsync() will not retry.

**commitasync()**

    If we are using commit async and handling retries in our code, then we need to be careful about the order of commits.

**combining synchronous and asynchronous commits**

    But if we know that this is the last commit before we close the consumer, or before a rebalance, we want to make extra sure that the commit succeeds.
    Therefore, a common pattern is to combine commitAsync() with commitSync() just before shutdown.

**Rebalance listeners**

    ConsumerRebalanceListener onPartitionsRevoked(Collection<TopicPartition> partitions) and onPartitionsRevoked(Collection<TopicPartition> partitions).
    consumer.subscribe(topics, new HandleRebalanceCsutomListener());