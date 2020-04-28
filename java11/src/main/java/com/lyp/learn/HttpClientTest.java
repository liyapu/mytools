package com.lyp.learn;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *    这是 Java 9 开始引入的一个处理 HTTP 请求的的 HTTP Client API，该 API 支持同步和异步，
 *   而在 Java 11 中已经为正式可用状态，
 *   你可以在 java.net 包中找到这个 API
 *
 *
 */
public class HttpClientTest {

    @Test
    public void test01() throws IOException, InterruptedException {
        String url = "http://baidu.com";

        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(url))
                // .GET() 可以省略，默认请求方式为 Get！
                .GET()
                .build();

        HttpResponse<String> httpResponse = HttpClient.newHttpClient()
                //send() 发送同步请求
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        String body = httpResponse.body();
        System.out.println("body = " +  body);
        System.out.println(httpResponse.version());
        System.out.println(httpResponse.statusCode());

    }

    @Test
    public void test02() throws IOException, InterruptedException, ExecutionException {
        String url = "http://baidu.com";

        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(url))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> httpResponseCompletableFuture = HttpClient.newHttpClient()
                //sendAsync() 发送异步请求
                .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        HttpResponse<String> stringHttpResponse = httpResponseCompletableFuture.get();
        String body = stringHttpResponse.body();
        System.out.println("body = " +  body);

    }

}
