# Confluent Certified Developer for Apache Kafka (CCDAK)

This repos is to keep all the relevant informations related for confluent-kafka-certification (CCDAK).

Note: This is work in progress, it will be improved overtime.

## Alternate links for preparation ##

 http://lahotisolutions.blogspot.com/2019/03/apache-kafka-notes.html
 https://www.quora.com/How-do-I-prepare-for-Kafka-certification-confluent-1

## How to run confluent kafka in AWS ##
* Create an ec2 instance
* install git
* install docker
* install docker-compose
* git clone https://github.com/confluentinc/cp-docker-images
* cd cp-docker-images
* git checkout 5.2.1-post
* cd examples/cp-all-in-one/
* docker-compose up -d --build

## Useful Kafka commands
* List topics  - docker-compose exec broker kafka-topics --zookeeper zookeeper:2181 --list
* Create topic  - docker-compose exec broker kafka-topics --create --zookeeper \
                  zookeeper:2181 --replication-factor 1 --partitions 1 --topic users

## Useful docker commands
* bash interactive mode - docker exec -it broker /bin/bash
* sh mode - docker-compose exec broker sh
* run commands example : kafka-topics --zookeeper zookeeper:2181 --list

## Books ##

Kafka: The Definitive Guide

## Links: ##
* Kafka-rest https://docs.confluent.io/current/kafka-rest/index.html
* kafka-rest https://docs.confluent.io/current/kafka-rest/docs/index.html
* kafka-rest https://docs.confluent.io/current/kafka-rest/quickstart.html
* schema registry https://docs.confluent.io/current/schema-registry/schema_registry_tutorial.html
* Kafka-connectors https://www.baeldung.com/kafka-connectors-guide
* Kafka-connect https://data-flair.training/blogs/kafka-connect/amp/
* Kafka-connect-avro https://docs.confluent.io/current/cp-docker-images/docs/tutorials/connect-avro-jdbc.html
* kafka-connect https://www.confluent.io/blog/simplest-useful-kafka-connect-data-pipeline-world-thereabouts-part-1/
* kafka-connect https://docs.confluent.io/current/cp-docker-images/docs/tutorials/connect-avro-jdbc.html
* Kafka-streams https://www.confluent.io/stream-processing-cookbook/
* Kafka-streams https://docs.confluent.io/current/streams/index.html
* KSQL https://docs.confluent.io/current/ksql/docs/faq.html
* KSQL https://docs.confluent.io/current/ksql/docs/tutorials/index.html
* KSQL https://www.michael-noll.com/blog/2018/04/05/of-stream-and-tables-in-kafka-and-stream-processing-part1/
* Kafka Security https://medium.com/@stephane.maarek/introduction-to-apache-kafka-security-c8951d410adf
* Kafka-control-center https://docs.confluent.io/current/tutorials/cp-demo/docs/index.html

## Video tutorials: ##
* Kafka schema registry and rest-proxy - https://youtu.be/5fjw62LGYNg
* Avro: schema evolution - By Stephane Maarek - https://youtu.be/SZX9DM_gyOE
* Avro producer - By Stephane Maarek https://youtu.be/_6HTHH1NCK0
* Kafka Connect Architecture - By Stephane Maarek https://youtu.be/YOGN7qr2nSE
* Kafka Connect Concepts - By Stephane Maarek https://youtu.be/BUv1IgWm-gQ
* Kafka Connect Distributed architecture - By Stephane Maarek https://youtu.be/52HXoxthRs0
* Kafka-connect https://www.youtube.com/playlist?list=PLt1SIbA8guutTlfh0J7bGboW_Iplm6O_B
* KSQL https://www.youtube.com/watch?v=ExEWJVjj-RA&list=PLa7VYi0yPIH2eX8q3mPpZAn3qCS1eDX8W
* kafka-streams explained by Neha https://www.youtube.com/watch?v=A9KQufewd-s&feature=youtu.be
* kafka-streams https://www.youtube.com/watch?v=Z3JKCLG3VP4&list=PLa7VYi0yPIH1vDclVOB49xUruBAWkOCZD
* kafka-streams https://youtu.be/Z3JKCLG3VP4
* kafka-streams https://youtu.be/LxxeXI1mPKo
* kafka-streams https://youtu.be/-y2ALVkU5Bc
* kafka-streams - By Stephane Maarek https://youtu.be/wPw3tb_dl70
* kafka-streams - By Tim berglund https://youtu.be/7JYEEx7SBuE
* kafka-streams - By Tim berglund https://youtu.be/3kJgYIkAeHs

## Kafka Core ##

**How produced messages are sent to kafka server?**

**What is a batch?**

    A batch is just a collection of messages, all of which are being produced to the same topic and partition.
    Batches are also typically compressed, providing more efficient data transfer and storage at the cost of some processing power

**What is the role of a controller?**

    Within a cluster of brokers, one broker will also function as the cluster controller (elected automatically from the live members of the cluster).
    The controller is responsible for administrative operations, including assigning partitions to brokers and monitoring for broker failures.

**What is a leader?**

    A partition is owned by a single broker in the cluster, and that broker is called the leader of the partition.
    A partition may be assigned to multiple brokers, which will result in the partition being replicated (as seen in Figure 1-7).
    This provides redundancy of messages in the partition, such that another broker can take over leadership if there is a broker failure

**Types of replica**

    Leader replica
    follower replica
    prefered replica
    insync replicas Only in-sync replicas are eligible to be elected as partition leaders in case the existing leader fails.
    out of sync : If a replica hasn’t requested a message in more than 10 seconds or if it has requested messages but hasn’t caught up to the most recent message in more than 10 seconds, the replica is considered out of sync.
    replica.lag.time.max.ms: The amount of time a follower can be inactive or behind before it is considered out of sync.
    By default, Kafka is configured with auto.leader.rebalance.enable=true, which will check if the preferred leader replica is not the current leader but is in-sync and trigger leader election to make the preferred leader the current leader.

**What is retension?**

    Kafka brokers are configured with a default retention setting for topics, either retaining messages for some period of time (e.g., 7 days) or until the topic reaches a certain size in bytes (e.g., 1 GB).
    Once these limits are reached, messages are expired and deleted so that the retention configuration is a minimum amount of data available at any time.

**What is log compaction?**

    Topics can also be configured as log compacted, which means that Kafka will retain only the last message produced with a specific key.
    This can be useful for changelog-type data, where only the last update is interesting.

## Zookeeper ##

**What is the role of zookeeper in kafka**

    zookeeper maintains the list of brokers, brokers info is stored in zookeeper under /brokers/ids

***Emphemeral node**

    Every time a broker process starts, it registers itself with its ID in Zookeeper by creating an ephemeral node.
    When a broker loses connectivity to Zookeeper, the ephemeral node that the broker created when starting will be automatically removed from Zookeeper.
    Even though the node representing the broker is gone when the broker is stopped, the broker ID still exists in other data structures. For example, the list of replicas of each topic contains the broker IDs for the replica.
    This way, if you completely lose a broker and start a brand new broker with the ID of the old one, it will immediately join the cluster in place of the missing broker with the same partitions and topics assigned to it.

**What is ensemble?**

    A Zookeeper cluster is called an ensemble.
    Due to the algorithm used, it is recommended that ensembles contain an odd number of servers (e.g., 3, 5, etc.) as a majority of ensemble members (a quorum) must be working in order for Zookeeper to respond to requests.
    This means that in a three-node ensemble, you can run with one node missing.
    With a five-node ensemble, you can run with two nodes missing.


**Ensemble configuration?**

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


## Kafka Broker ##

**broker.id**

    Every Kafka broker must have an integer identifier, which is set using the broker.id configuration.
    By default, this integer is set to 0, but it can be any value.
    The most important thing is that the integer must be unique within a single Kafka cluster.
    The selection of this number is arbitrary, and it can be moved between brokers if necessary for maintenance tasks.
    A good guideline is to set this value to something intrinsic to the host so that when performing maintenance it is not onerous to map broker ID numbers to hosts.
    For example, if your hostnames contain a unique number (such as host1.example.com, host2.example.com, etc.), that is a good choice for the broker.id value.

**port**

    The example configuration file starts Kafka with a listener on TCP port 9092.

**zookeeper.connect**

    The location of the Zookeeper used for storing the broker metadata is set using the zookeeper.
    Connect configuration parameter. The example configuration uses a Zookeeper running on port 2181 on the local host, which is specified as localhost:2181.

**log.dirs**

    Kafka persists all messages to disk, and these log segments are stored in the directories specified in the log.dirs configuration.
    This is a comma-separated list of paths on the local system.
    If more than one path is specified, the broker will store partitions on them in a “least-used” fashion with one partition’s log segments stored within the same path.
    Note that the broker will place a new partition in the path that has the least number of partitions currently stored in it, not the least amount of disk space used.

**num.recovery.threads.per.data.dir**

    Kafka uses a configurable pool of threads for handling log segments. Currently, this thread pool is used:
        • When starting normally, to open each partition’s log segments
        • When starting after a failure, to check and truncate each partition’s log segments
        • When shutting down, to cleanly close log segments
     By default, only one thread per log directory is used.
     As these threads are only used during startup and shutdown, it is reasonable to set a larger number of threads in order to parallelize operations.
     Specifically, when recovering from an unclean shutdown, this can mean the difference of several hours when restarting a broker with a large number of partitions! When setting this parameter, remember that the number configured is per log directory specified with log.dirs.
     This means that if num.recovery.threads.per.data.dir is set to 8, and there are 3 paths specified in log.dirs, this is a total of 24 threads.

**auto.create.topics.enable**

    The default Kafka configuration specifies that the broker should automatically create a topic under the following circumstances:
        • When a producer starts writing messages to the topic
        • When a consumer starts reading messages from the topic
        • When any client requests metadata for the topic
    In many situations, this can be undesirable behavior, especially as there is no way to validate the existence of a topic through the Kafka protocol without causing it to be created.
    If you are managing topic creation explicitly, whether manually or through a provisioning system, you can set the auto.create.topics.enable configuration to false.

**num.partitions**

    default is 1

**log.retention.ms**

    The most common configuration for how long Kafka will retain messages is by time.
    The default is specified in the configuration file using the log.retention.hours
    parameter, and it is set to 168 hours, or one week. However, there are two other
    parameters allowed, log.retention.minutes and log.retention.ms. All three of
    these specify the same configuration—the amount of time after which messages may
    be deleted—but the recommended parameter to use is log.retention.ms, as the
    smaller unit size will take precedence if more than one is specified. This will make
    sure that the value set for log.retention.ms is always the one used. If more than one
    is specified, the smaller unit size will take precedence.

**log.retention.bytes**

    This property will define the amount of data retainied per partition, if the topic has 8 partition then data retained per topic would be 8GB.
    if both the log.retention.ms and log.retention.bytes are configured, messages may be removed when either criteria is met.

**log.segment.bytes**

    Once the log segment has reached the size specified by the log.segment.bytes parameter, which defaults to 1 GB, the log segment is closed and a new one is opened.
    Once a log segment has been closed, it can be considered for expiration.

**log.segment.ms**

    Specifies the amount of time after which a log segment should be closed.
    Kafka will close a log segment either when the size limit is reached or when the time limit is reached, whichever comes first.
    By default, there is no setting for log.segment.ms, which results in only closing log segments by size.

**message.max.bytes**

    message.max.bytes parameter, which defaults to 1000000, or 1 MB.
    This configuration deals with compressed message size, actual uncompressed message can be larger then it.

    There are noticeable performance impacts from increasing the allowable message size.
    Larger messages will mean that the broker threads that deal with processing network connections and requests will be working longer on each request.
    Larger messages also increase the size of disk writes, which will impact I/O throughput.

**fetch.message.max.bytes**

    If this value is smaller than message.max.bytes, then consumers that encounter larger messages will fail to fetch those messages, resulting in a situation where the consumer gets stuck and cannot proceed.


**Memory**

    Most of the cases, consumer is caught up and lagging behind the producers very little. consumer will read are from system page cache resulting in faster reads.
    Having more memory avilable to the system for page cache will improve the performance of consumer clients.
    Kafka itself does not need much heap memory configured for the Java Virtual Machine (JVM). Even a broker that is handling X messages per second and a data rate of X megabits per second can run with a 5 GB heap.

CPU
    CPU power is mainly utilized for compressing messages from disc and recompress the message batch in order to store in disc.

**Request processing:**

   client request --> broker --> partitions leaders --> reponse --> broker --> client
    All requests sent to the broker from a specific client will be processed in the order in which they were received—this guarantee is what allows Kafka to behave as a message queue and provide ordering guarantees on the messages it stores.

**Request header**

    request type
    request version
    correlation id
    client id

**client cache topic metadata**

    client request for the metadata (request type: metadata request, which includes a list of topics the client is interested in).
    metadata containts which partitions exist in the topics, the replicas for each partition, and which replica is the leader
    all brokers caches the metadata informations.
    metadata.max.age.ms defines the time to refresh the medadata in client.
    if a client receives "not a leader", it will refresh metadata before retrying.

**where does kafka writes the produced messages**

    On Linux, the messages are written to the filesystem cache and there is no guarantee about when they will be written to disk.
    Kafka does not wait for the data to get persisted to disk—it relies on replication for message durability.

**what are segments**

    partitions are further divided into segments, default size of segment is either 1 GB of data or a week of data.
    currently writting segments is called active segment. active segment will never be deleted even the retension is passed.
    Kafka broker will keep an open file handle to every segment in every partition—even inactive segments. This leads to an usually high number of open file handles, and the OS must be tuned accordingly.

**message additional infor**

    Each message contains—in addition to its key, value, and offset—things like the message size, checksum code that allows us to detect corruption, magic byte that indicates the version of the message format, compression codec (Snappy, GZip, or LZ4), and a timestamp (added in release 0.10.0). The timestamp is given either by the producer when the message was sent or by the broker when the message arrived—depending on configuration.

**Indexes**

    Kafka maintains indexes for each partition, indexes maps offsets to segment files and position within the file.

**compaction**

Policies:
    delete --> delete events older then retension time.
    compact --> keeps only the recent version of a particular key.

**How compactions works?**

    clean
    dirty
    Deleted events --> producer will send a mesasge with key and value as null.

    Compact policy will never delete a compact messages in current segment.

**Where does kafka stores dynamic per broker configurations?**

    zookeeper.

**Where does dynamic cluster-wide default configs stored?**

    zookeeper.

## Kafka producer ##
[README.md](kafka-producer/README.md)
## Kafka consummer ##
[README.md](kafka-consumer/README.md)
## Kakfa Connectors ##
[README.md](kafka-connectors/README.md)
## Kakfa schema-registry ##
[README.md](kafka-schema-registry/README.md)
## Kakfa Streams ##
[README.md](kafka-streams/README.md)
## Kafka KSQL ##
[README.md](kafka-ksql/README.md)
## Kafka-DSL ##

## Kafka-docker ##

## Kafka-control-center ##

## Kafka-security ##

## Architecture and Adavanced Concepts ##

* Exactly once semantics : https://www.confluent.io/blog/exactly-once-semantics-are-possible-heres-how-apache-kafka-does-it/

## realtime use-cases ##

