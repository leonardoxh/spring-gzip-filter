package com.github.leonardoxh.gzipfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GZIPFilter implements Filter {

    @Override
    public void destroy() { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if(supportsGzip(request)) {
            GZIPResponseWrapper gzipResponse = new GZIPResponseWrapper((HttpServletResponse) response);
            chain.doFilter(request, gzipResponse);
            gzipResponse.finish();
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    private boolean supportsGzip(ServletRequest request) {
        String acceptEncoding = ((HttpServletRequest) request).getHeader("Accept-Encoding");
        return acceptEncoding != null && acceptEncoding.contains("gzip");
    }

}