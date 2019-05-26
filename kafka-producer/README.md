# Kafka producer

## Key points

**When will a produced message will be ready to consume?**

    Messages written to the partition leader are not immediately readable by consumers regardless of the producer's acknowledgement settings.
    When all in-sync replicas have acknowledged the write, then the message is considered committed, which makes it available for reading.
    This ensures that messages cannot be lost by a broker failure after they have already been read.

**What are the producer key configuration?**

    key configurations are explined here : https://docs.confluent.io/current/clients/producer.html

**Explain bootstrap.servers**

    bootstrap.servers property so that the producer can find the Kafka cluster.

**Explain bootstrap.servers**

    Although not required, you should always set a client.id since this allows you to easily correlate requests on the broker with the client instance which made it.

**Where can i find the full list of configs documentation?**

    https://docs.confluent.io/current/installation/configuration/producer-configs.html#cp-config-producer

**How produced messages are sent to kafka server?

**What is a batch?

    A batch is just a collection of messages, all of which are being produced to the same topic and partition.
    Batches are also typically compressed, providing more efficient data transfer and storage at the cost of some processing power

**What is the controller?

    Within a cluster of brokers, one broker will also function as the cluster controller (elected automatically from the live members of the cluster).
    The controller is responsible for administrative operations, including assigning partitions to brokers and monitoring for broker failures.

**What is a leader?

    A partition is owned by a single broker in the cluster, and that broker is called the leader of the partition.
    A partition may be assigned to multiple brokers, which will result in the partition being replicated (as seen in Figure 1-7).
    This provides redundancy of messages in the partition, such that another broker can take over leadership if there is a broker failure

**What is retension?

    Kafka brokers are configured with a default retention setting for topics, either retaining messages for some period of time (e.g., 7 days) or until the topic reaches a certain size in bytes (e.g., 1 GB).
    Once these limits are reached, messages are expired and deleted so that the retention configuration is a minimum amount of data available at any time.

**What is log compaction?

    Topics can also be configured as log compacted, which means that Kafka will retain only the last message produced with a specific key.
    This can be useful for changelog-type data, where only the last update is interesting.

**What is ensemble?

    A Zookeeper cluster is called an ensemble.
    Due to the algorithm used, it is recommended that ensembles contain an odd number of servers (e.g., 3, 5, etc.) as a majority of ensemble members (a quorum) must be working in order for Zookeeper to respond to requests.
    This means that in a three-node ensemble, you can run with one node missing.
    With a five-node ensemble, you can run with two nodes missing.

** Ensemble configuration?

    Example:
    tickTime=2000
    dataDir=/var/lib/zookeeper
    clientPort=2181
    initLimit=20
    syncLimit=5
    server.1=zoo1.example.com:2888:3888
    server.2=zoo2.example.com:2888:3888
    server.3=zoo3.example.com:2888:3888


    initLimit   is the amount of time to allow followers to connect with a leader.
    SyncLimit   The syncLimit value limits how out-of-sync followers can be with the leader
    clientPort  2181
    The servers are specified in the format server.X=hostname:peerPort:leaderPort, with
    the following parameters:
    X           The ID number of the server. This must be an integer, but it does not need to be zero-based or sequential.
    hostname    The hostname or IP address of the server.
    peerPort    The TCP port over which servers in the ensemble communicate with each other. (default port: 2888)
    leaderPort  The TCP port over which leader election is performed. (default port: 3888)

unclean.leader.election.enable