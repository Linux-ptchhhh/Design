package networking.communication;

import tools.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaObserver { 
    
    private final ObjectMapper objectMapper;
    private final kafkarepository kafrepo; 

  
    public KafkaObserver(ObjectMapper objectMapper, kafkarepository kafrepo) { 
        this.objectMapper = objectMapper;
        this.kafrepo = kafrepo;
    }

    @KafkaListener(topics = "user-presence", groupId = "chat-database-workers")
    public void listenToPresenceEvents(String rawJsonMessage) {
        try {
            KafkaEntity event = objectMapper.readValue(rawJsonMessage, KafkaEntity.class);

            String ip = event.getIp();
            String status = event.getStatus();

            if ("ONLINE".equals(status)) {
                kafrepo.add(ip,status); 
            } else {
                kafrepo.delete(ip);  
            }

        } catch (Exception e) {
            System.err.println("[ERROR] Could not parse incoming Kafka message: " + e.getMessage());
        }
    }
}