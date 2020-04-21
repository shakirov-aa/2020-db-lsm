package ru.mail.polis.shakirov;

import org.jetbrains.annotations.NotNull;
import ru.mail.polis.DAO;
import ru.mail.polis.Record;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.TreeMap;

public class DAOImpl implements DAO {
    private final NavigableMap<ByteBuffer, Record> db = new TreeMap<>();

    @NotNull
    @Override
    public Iterator<Record> iterator(@NotNull final ByteBuffer from) throws IOException {
        return db.tailMap(from).values().iterator();
    }

    @Override
    public void upsert(@NotNull final ByteBuffer key, @NotNull final ByteBuffer value) throws IOException {
        db.put(key, Record.of(key, value));
    }

    @Override
    public void remove(@NotNull final ByteBuffer key) throws IOException {
        db.remove(key);
    }

    @Override
    public void close() throws IOException {
        // do nothing
    }
}
