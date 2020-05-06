package ru.mail.polis.shakirov;

import java.io.File;
import java.io.IOException;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class GFG {
    public static void main(String[] args) throws IOException {
        File file = new File("/tmp", 1 + ".dat");
        FileChannel fc = FileChannel.open(file.toPath(), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
        final ByteBuffer offsets = ByteBuffer.allocate(2 * Long.BYTES);

        offsets.putLong(fc.position());
        fc.write(ByteBuffer.allocate(Integer.BYTES).putInt(20).rewind());
        fc.write(ByteBuffer.allocate(Integer.BYTES).putInt(35).rewind());

        offsets.putLong(fc.position());
        fc.write(ByteBuffer.allocate(Integer.BYTES).putInt(11).rewind());
        fc.write(ByteBuffer.allocate(Integer.BYTES).putInt(22).rewind());

        fc.write(offsets.rewind());
        fc.write(ByteBuffer.allocate(Integer.BYTES).putInt(2).rewind());
        fc.close();

        fc = FileChannel.open(file.toPath(), StandardOpenOption.READ);
        ByteBuffer buf = ByteBuffer.allocate(Integer.BYTES);
        fc.read(buf, fc.size() - Integer.BYTES * 3);
        System.out.println(buf.rewind().getInt());
    }
}
