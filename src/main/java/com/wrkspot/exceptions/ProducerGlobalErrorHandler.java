package com.wrkspot.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProducerGlobalErrorHandler {

    @Bean
    public MessageChannel errorChannel() {
        return new DirectChannel();
    }

    @ServiceActivator(inputChannel = "errorChannel")
    public void handleGlobalError(Message<?> message) {
        if (message instanceof ErrorMessage) {
            Throwable exception = ((ErrorMessage) message).getPayload();
            log.error("Global error occurred: {}", exception.getMessage(), exception);
            // Implement custom error handling logic here
        }
    }
}
