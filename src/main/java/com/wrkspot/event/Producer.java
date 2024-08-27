package com.wrkspot.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Producer to producer messages to defined binding in application.yml file.
 * @param <T>
 * @author govil
 */
@Service
@Slf4j
public class Producer<T> {

    @Autowired
    private StreamBridge streamBridge;

    public void sendMessage(T message) {
        try {
            Message<T> msg = MessageBuilder.withPayload(message)
                    .build();
            Boolean sent = streamBridge.send("producer-out-0", msg);
        } catch (Exception ex) {
            log.error("Exception caught in Message: {}", ex.getMessage());
        }
    }
}
