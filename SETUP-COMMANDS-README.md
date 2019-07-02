#Setup & Commamnds#

## How to run confluent kafka in AWS ##
```
* Create an ec2 instance
* install git
* install docker
* install docker-compose
* git clone https://github.com/confluentinc/cp-docker-images
* cd cp-docker-images
* git checkout 5.2.1-post
* cd examples/cp-all-in-one/
* docker-compose up -d --build
```
## Useful Kafka commands
```
* List topics  - docker-compose exec broker kafka-topics --zookeeper zookeeper:2181 --list
* Create topic  - docker-compose exec broker kafka-topics --create --zookeeper \
                  zookeeper:2181 --replication-factor 1 --partitions 1 --topic users
```

## Useful docker commands
* bash interactive mode - docker exec -it broker /bin/bash
* sh mode - docker-compose exec broker sh
* run commands example : kafka-topics --zookeeper zookeeper:2181 --list
