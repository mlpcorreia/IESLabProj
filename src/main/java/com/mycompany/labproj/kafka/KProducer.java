/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labproj.kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author miguel
 */
public class KProducer {
    
    Properties properties;
    KafkaProducer kafkaProducer;
    
    public KProducer() {
        this.properties = new Properties();
        this.properties.put("bootstrap.servers", "192.168.160.210:9092");
        this.properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.kafkaProducer = new KafkaProducer(this.properties);
    }
    
    public void sendMessage(String msg) {
        try{
            System.out.println(msg+"Producer");
            kafkaProducer.send(new ProducerRecord("weatherAlarm",msg));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
