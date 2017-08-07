package com.dimas.api;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.common.base.MoreObjects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

public class Hello {

    private final HelloId id;

    private final UUID otherId;

    private Hello(HelloId id, UUID otherId) {
        this.id = checkNotNull(id);
        this.otherId = checkNotNull(otherId);
    }

    public HelloId getId() {
        return id;
    }

    public UUID getOtherId() {
        return otherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Hello hello = (Hello) o;

        return Objects.equals(id, hello.id) &&
                Objects.equals(otherId, hello.otherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, otherId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("otherId", otherId)
                .toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        @NotNull
        @Pattern(regexp = "[0-9a-fA-F]{16}")
        private String id;

        @NotNull
        @Pattern(regexp = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}")
        private String otherId;

        @JsonSetter("id")
        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        @JsonSetter("otherId")
        public Builder withOtherId(String otherId) {
            this.otherId = otherId;
            return this;
        }

        public Hello build() {
            return new Hello(
                    HelloId.valueOf(id),
                    UUID.fromString(otherId));
        }
    }
}
