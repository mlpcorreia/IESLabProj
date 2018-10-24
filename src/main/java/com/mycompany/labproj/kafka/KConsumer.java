/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labproj.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * Kafka consumer
 * @author Henrique Manso Nº 65308
 * @author Miguel Correia Nº69892
 */
public final class KConsumer extends TimerTask{
    
    private final Properties properties;
    private final KafkaConsumer kafkaConsumer;
    private List topics;
    
    public KConsumer() {
        this.properties = new Properties();
        this.properties.put("bootstrap.servers", "192.168.160.210:9092");
        this.properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.properties.put("group.id", "wGroup");
        this.kafkaConsumer = new KafkaConsumer(properties);
        this.topics = new ArrayList();
        this.subTopics("weatherAlarm");
    }
    public static void main(String[] args) {
        TimerTask fetchAlarms = new KConsumer();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(fetchAlarms, 0, 10000);
    }
    
    @Override
    public void run() {
        try {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(10);
            for(ConsumerRecord<String, String> record: records) {
                System.out.println(record.value());
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        } 
    }
    
    public void subTopics(String topic) {
        this.topics.add(topic);
        this.kafkaConsumer.subscribe(topics);
    }
}
