package com.dimas.api;

import java.util.Objects;

public class HelloId {
    private final long value;

    private HelloId(long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HelloId helloId = (HelloId) o;

        return value == helloId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Long.toHexString(value);
    }

    public static HelloId valueOf(String input) {
        return new HelloId(Long.parseLong(input, 16));
    }
}
