# Kafka schema-registry


## docker-commands
docker exec -it schema-registry /bin/bash

## kafka avro console produder

kafka-avro-console-producer \
  --broker-list broker:29092 --topic bar \
  --property value.schema='{"type":"record","name":"myrecord","fields":[{"name":"f1","type":"string"}]}'

 sample mesages
 {"f1": "value1"}
 {"f2": "value2"}

 kafka-avro-console-consumer \
 --topic customer-avro \
 --bootstrap-server broker:29092 \
 --from-beginning

