package org.kook.spring.boot.exploration.singularity.starter;

import java.util.UUID;

public class DummyObjectSingularity implements ObjectSingularity {

    private final String prefix;
    private final int magicNumber;

    public DummyObjectSingularity(String prefix, int magicNumber) {
        this.prefix = prefix;
        this.magicNumber = magicNumber;
    }

    @Override
    public String get() {
        return prefix + magicNumber + "-" + UUID.randomUUID();
    }

}
