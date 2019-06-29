# Kafka ksql

## Steps to connect to ksql running with docker. ##

     docker exec -it ksql-server /bin/bash

## comamnd to data generate to ksql stream topic ##
    docker exec -it ksql-datagen bash
    ksql-datagen \
      bootstrap-server=broker:29092 \
      quickstart=pageviews \
      format=delimited \
      topic=pageviews \
      maxInterval=500

**KSQL will execute in 2 modes**

    Interactive and headless

**is rest API supported in headless mode?**

    No. rest API is not supported in headless way.

**what is KTable?**

**what is GlobalKTable?**

**Ktable vs GlobalKTable?**

**Type of join supported by KSQL?**



