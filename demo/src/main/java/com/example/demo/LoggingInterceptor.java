package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    /**
     *  在 execution.execute(request, body); 前或者后加判断逻辑来实现拦截器，用于拦截请求前后的功能，
     *      *
     *  Filter
     * @param request
     * @param body
     * @param execution
     * @return
     * @throws IOException
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        URI uri = request.getURI();
        HttpMethod method = request.getMethod();
        HttpHeaders headers = request.getHeaders();
        System.out.println("请求的URI 是"+uri+" 请求的方法是"+method);
        // 请求的URI 是http://provider/postParam 请求的方法是POST
        ClientHttpResponse clientHttpResponse = execution.execute(request, body);
        HttpStatus statusCode = clientHttpResponse.getStatusCode();
        InputStream body1 = clientHttpResponse.getBody();
        System.out.println("status code="+statusCode+"-------------- body="+body1);
        return clientHttpResponse;
    }
}
