package service;

import com.google.gson.Gson;
import models.Order;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OrderService {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public Order getOrderById(URI uri, Long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + id))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), Order.class);
    }

    public void getOrderInventory() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://petstore.swagger.io/v2/store/inventory"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public Order postOrder(URI uri, Order order) throws IOException, InterruptedException {
        String requestBody = gson.toJson(order);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), Order.class);
    }

    public void deleteOrder(URI uri, Long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + id))
                .header("Content-type", "application/json")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(""))
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response status of delete Order is " + response.statusCode());
    }

}
