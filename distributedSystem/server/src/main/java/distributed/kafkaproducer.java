package distributed;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class kafkaproducer {

        KafkaProducer produceService(){

            Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9078"); 
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer =
                new KafkaProducer<>(props);

                return producer;
        }

    @SuppressWarnings("unchecked")
    void produce(String jsons,KafkaProducer producer){
        
        try {
            producer.send(
                    new ProducerRecord<>("server-mtc",
                           jsons ));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
