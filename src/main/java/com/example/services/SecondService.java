package com.example.services;

import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
@Named("Second")
public class SecondService implements Service {
    @Override
    public String saySmth() {
        return "Hello from second";
    }
}
