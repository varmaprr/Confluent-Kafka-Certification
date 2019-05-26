# Confluent Certified Developer for Apache Kafka (CCDAK)

This repos is to keep all the relevant informations related for confluent-kafka-certification (CCDAK).

Note: This is work in progress, it will be improved overtime. 

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


## Useful Links: ##
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
