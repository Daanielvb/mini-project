package com.example.demo.core.gateway.impl;

import com.example.demo.core.gateway.Producer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("dummy")
public class DummyProducer implements Producer {

    @Override
    public void produce(String msg) {

    }
}
