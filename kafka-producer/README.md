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


