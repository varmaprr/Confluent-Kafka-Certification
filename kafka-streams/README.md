# Kafka streams

**What is a topology?**

    Topology represent the DAG for streaming process.

**what are kafka streams?**

    Kafka streams leveeraged the consumer and producer API, so all the properties applicable to consumer and producer is sill applicable here.
    application.id specific to stream applications will be used for
    consumer group.id = application.id
    default client.id prefix
    prefix to the internal changelog topics

**Kafka streams creates internal intermidiate topics**

    repartitioning topics
    changelog topics

**What are the types of stores in streams?**

    1. local or internal state
    2. external state

**What is state store?**

    Some operations(like avg, count etc) in streams depends on previously calculated infromation for which state need to be stored.

**What happens to local state if a node goes down?**

    local state will be stored in rockDB and it is also writted in kafka topic, in case of node failure state will be recreated from kafka.


**What are the type of windowing options in streams?**

    1. Tumbling window or sliding window
    2. hoping window

**How out of sync records are handled in streams?**

**Stream vs table??**

    stream represents all the events occured in table. insert only mode.
    table represents the current state of the records. upsert mode.