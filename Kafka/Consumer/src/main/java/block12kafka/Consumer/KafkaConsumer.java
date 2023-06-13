package block12kafka.Consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {


    @KafkaListener(topics = "Topic")
    public void addMessageToHistory(String message) {
        System.out.println(message);
    }

}