package cn.i623.myfxutil.util;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Http {
    public static void main(String[] args) {
//        getRequest();

        makeService();
    }

    private static void makeService() {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NEVER)
                .proxy(ProxySelector.of(new InetSocketAddress("www-proxy.com", 8080)))
                .authenticator(Authenticator.getDefault())
                .build();
    }

    private static void getRequest() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

    }
}
