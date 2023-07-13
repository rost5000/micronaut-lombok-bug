package com.example.services;

import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
@Named("first")
public class FirstService implements Service{
    @Override
    public String saySmth() {
        return "Hello from one";
    }
}
