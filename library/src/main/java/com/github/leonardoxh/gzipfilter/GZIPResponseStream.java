package com.github.leonardoxh.gzipfilter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class GZIPResponseStream extends ServletOutputStream {

    private GZIPOutputStream gzipOutput;
    private final AtomicBoolean open = new AtomicBoolean(true);

    public GZIPResponseStream(OutputStream output) throws IOException {
        this.gzipOutput = new GZIPOutputStream(output);
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setWriteListener(WriteListener arg0) {
        
    }

    @Override
    public void flush() throws IOException {
        gzipOutput.flush();
    }

    @Override
    public void close() throws IOException {
        if(open.compareAndSet(true, false)) {
            gzipOutput.close();
        }
    }

    @Override
    public void write(int b) throws IOException {
        
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if(!open.get()) {
            throw new IOException("closed");
        }
        gzipOutput.write(b, off, len);
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

}