package com.lightbend.lagom.example.hello.impl;

public class RealGreeter implements SaysHello {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + "! This is not a test!\n";
    }
}
