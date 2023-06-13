package block12kafka.Sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    //Para enviar un mensaje se necesita una instancia de KafkaTemplate
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //Con este método le pasamos el mensaje por parámetro
    //que lo enviará al clúster
    public void sendMessage(String message) {
        kafkaTemplate.send("Topic", message);
    }

}