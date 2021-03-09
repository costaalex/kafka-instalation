package main.java;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KafkaTopicCreationInJava
{
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileReader(new File("kafka.properties")));

        AdminClient adminClient = AdminClient.create(properties);
        NewTopic newTopic = new NewTopic("jj", 3, (short)1); //new NewTopic(topicName, numPartitions, replicationFactor)

        List<NewTopic> newTopics = new ArrayList<NewTopic>();
        newTopics.add(newTopic);

        adminClient.createTopics(newTopics);
        adminClient.close();

    }
}

/*
package main.java;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KafkaTopicCreationInJava {
    private static final int NUM_PARTITIONS=3;
    private static final int REPLICATION_FACTOR=1;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        //props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        AdminClient kafkaAdminClient = KafkaAdminClient.create(props);
        CreateTopicsResult result = kafkaAdminClient.createTopics(
                Stream.of("gfg", "dfd").map(
                        name -> new NewTopic(name, NUM_PARTITIONS, (short) REPLICATION_FACTOR)
                ).collect(Collectors.toList())
        );
        result.all().get();
    }
}

* */


