package ru.mail.polis.shakirov;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public class Value implements Comparable<Value> {
    private final long timestamp;
    @NotNull
    private final ByteBuffer data;

    public Value(final long timestamp, @NotNull final ByteBuffer data) {
        assert timestamp > 0L;
        this.timestamp = timestamp;
        this.data = data;
    }

    static Value tombstone(final long time) {
        return new Value(time, null);
    }

    public boolean isTombstone() {
        return data == null;
    }

    @NotNull
    ByteBuffer getData() {
        assert !isTombstone();
        return data.asReadOnlyBuffer();
    }

    @Override
    public int compareTo(@NotNull final Value o) {
        return -Long.compare(this.timestamp, o.timestamp);
    }



    public long getTimestamp() {
        return timestamp;
    }
}
