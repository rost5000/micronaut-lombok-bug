package com.example;

import com.example.services.Service;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomApplicationReadyEvent  implements ApplicationEventListener<StartupEvent> {
    @Named("first")
    Service service;

    @Override
    public void onApplicationEvent(StartupEvent event) {
        log.info("My service says: {}", service.saySmth());
    }
}
