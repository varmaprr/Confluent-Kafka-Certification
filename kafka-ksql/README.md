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

 ksql-datagen schema=./userprofile.avro format=json topic=USERPROFILE key=userid maxInterval=5000 iterations=100ootstrap-server=broker:29092

**KSQL will execute in 2 modes**

    Interactive and headless

**is rest API supported in headless mode?**

    No. rest API is not supported in headless way.

**what is KTable?**

**what is GlobalKTable?**

**Ktable vs GlobalKTable?**

**Type of join supported by KSQL?**

**output types in joins**

    join between a stream and a stream will return a new stream
    join between a stream and a table will return a stream
    join between a table and a table will return a table

**How to terminate a query**

    terminate query "query_name"

**How to run a ksql script from cli**

    run script "./path/to/file.ksql"

**How to print a topic from beginning**

    print 'topicname' from beginning

**How to stream from beginning**

    set 'auto.offset.reset'='earliest';
    select * from stream;

**Create a new stream from a stream**

    create stream user_profile_pretty as select firstname || ' ' || ucase( lastname) || ' from ' || countrycode || ' has a rating of ' || cast(rating as varchar) || ' stars. ' || case when rating < 2.5 then 'Poor' when rating between 2.5 and 4.2 then 'Good'else 'Excellent'end as description from userprofile;

