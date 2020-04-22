package ru.mail.polis.shakirov;

import com.google.common.collect.Iterators;
import org.jetbrains.annotations.NotNull;
import ru.mail.polis.DAO;
import ru.mail.polis.Record;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.TreeMap;

public class DAOImpl implements DAO {
    private final NavigableMap<ByteBuffer, ByteBuffer> db = new TreeMap<>();

    @NotNull
    @Override
    public Iterator<Record> iterator(@NotNull final ByteBuffer from) {
        return Iterators.transform(db.tailMap(from).entrySet().iterator(), item -> Record.of(item.getKey(), item.getValue()));
    }

    @Override
    public void upsert(@NotNull final ByteBuffer key, @NotNull final ByteBuffer value) {
        db.put(key, value);
    }

    @Override
    public void remove(@NotNull final ByteBuffer key) {
        db.remove(key);
    }

    @Override
    public void close() {
        // do nothing
    }
}
