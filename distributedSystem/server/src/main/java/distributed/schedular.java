package distributed;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class schedular {
    private double timestamp = 0;
    mGET gets = new mGET();
    
    void runschedule(KafkaProducer producer)throws Exception{
       
           ScheduledExecutorService scheduler =
                Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {

            try {
                
                double value = gets.getvaluesMetrices();

                timestamp+=15;

               jsonCreator(value,timestamp,producer);
               
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, 0, 15, TimeUnit.SECONDS);
    }

    void jsonCreator(double value,double timestamp,KafkaProducer producer) throws JsonProcessingException{

         Map<String, Double> data = new LinkedHashMap<>();
         data.put("time",timestamp);
         data.put("usages",value);
         ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
        kafkaproducer kafka = new kafkaproducer();
        kafka.produce(jsonResult,producer);
    }
}
