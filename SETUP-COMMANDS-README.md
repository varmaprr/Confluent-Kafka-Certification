# Setup & Commands #

## How to run confluent kafka docker in AWS ##
```
1. Create an ec2 instance
2. install git
3. install docker
4. install docker-compose
5. git clone https://github.com/confluentinc/cp-docker-images
6. cd cp-docker-images
7. git checkout 5.2.1-post
8. cd examples/cp-all-in-one/
9. docker-compose up -d --build
```
## Linux command
```
# List topics docker-compose exec broker kafka-topics --zookeeper zookeeper:2181 --list
# Create topic docker-compose exec broker kafka-topics --create --zookeeper \
    zookeeper:2181 --replication-factor 1 --partitions 1 --topic users
```

## Docker command
```
# docker command to run in bash or sh shell
    bash interactive mode - docker exec -it broker /bin/bash
    sh mode - docker-compose exec broker sh
    kafka-topics --zookeeper zookeeper:2181 --list
```