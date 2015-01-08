package com.github.leonardoxh.gzipfilter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GZIPResponseWrapper extends HttpServletResponseWrapper {

    private GZIPResponseStream gzipResponse;
    private ServletOutputStream servletOuput;
    private PrintWriter printerWriter;

    public GZIPResponseWrapper(HttpServletResponse response) {
        super(response);
        response.addHeader("Content-Encoding", "gzip");
    }

    public void finish() throws IOException {
        if(printerWriter != null) {
            printerWriter.close();
        }
        if(servletOuput != null) {
            servletOuput.close();
        }
        if(gzipResponse != null) {
            gzipResponse.close();
        }
    }

    @Override
    public void flushBuffer() throws IOException {
        if(printerWriter != null) {
            printerWriter.flush();
        }
        if(servletOuput != null) {
            servletOuput.flush();
        }
        super.flushBuffer();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if(servletOuput == null) {
            gzipResponse = new GZIPResponseStream(getResponse().getOutputStream());
            servletOuput = gzipResponse;
        }
        return servletOuput;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if(printerWriter == null) {
            gzipResponse = new GZIPResponseStream(getResponse().getOutputStream());
            printerWriter = new PrintWriter(new OutputStreamWriter(gzipResponse, getResponse().getCharacterEncoding()));
        }
        return printerWriter;
    }

}