package com.strategyopr.dataimport.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strategyopr.dataimport.Bean.BookBean;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Properties;
public class KafkaService {
    private KafkaProducer<Integer,String> producer = null;
    public void deliverRecord(BookBean bookBean) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Properties pro = new Properties();
        pro.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, Serializer.class.getName());
        pro.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Serializer.class.getName());
        pro.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG,"1");
        KafkaProducer<Integer, String> producer = new KafkaProducer<>(pro);
        producer.initTransactions();
        producer.beginTransaction();
        producer.send(new ProducerRecord<Integer, String>("book",
                3, objectMapper.writeValueAsString(bookBean)), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    System.out.println("kafka在"+recordMetadata.timestamp()+"发送到"+recordMetadata.topic());
                }
            }
        });

    }
}
