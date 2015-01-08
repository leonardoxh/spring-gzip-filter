/*   
 * Copyright 2015 Leonardo Rossetto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
