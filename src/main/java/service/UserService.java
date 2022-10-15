package service;

import com.google.gson.Gson;
import command.Console;
import models.ApiResponse;
import models.Order;
import models.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class UserService {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public User getUserByName(URI uri, String name) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + name))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), User.class);
    }

    public ApiResponse postUser(URI uri, User user) throws IOException, InterruptedException {
        String requestBody = gson.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ApiResponse.class);
    }
    public ApiResponse putUser(URI uri, User user) throws IOException, InterruptedException {
        String requestBody = gson.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + user.getUsername()))
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ApiResponse.class);
    }

    public void deleteUser(URI uri, String name) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + name))
                .header("Content-type", "application/json")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(""))
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response status of delete User is " + response.statusCode());
    }
    public ApiResponse postUsersFromList(URI uri, List<User> users) throws IOException, InterruptedException {
        String requestBody = gson.toJson(users);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/createWithList"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ApiResponse.class);
    }

    public ApiResponse postUsersFromArray(URI uri, User[] users) throws IOException, InterruptedException {
        String requestBody = gson.toJson(users);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/createWithArray"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ApiResponse.class);
    }

    public ApiResponse userLogin(URI uri, String login, String password) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/login?username=" + login + "&password="+password))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ApiResponse.class);
    }
    public ApiResponse userLogout(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/logout"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ApiResponse.class);
    }
    public User readUser(Console console){
        User user =new User();
        System.out.println("Please enter user id:");
        while (true){
            try{
                user.setId(Long.parseLong(console.read()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        System.out.println("Please enter user name:");
        user.setUsername(console.read());
        System.out.println("Please enter user first name:");
        user.setFirstName(console.read());
        System.out.println("Please enter user last name:");
        user.setLastName(console.read());
        System.out.println("Please enter user email:");
        user.setEmail(console.read());
        System.out.println("Please enter user password:");
        user.setPassword(console.read());
        System.out.println("Please enter phone:");
        user.setPhone(console.read());
        System.out.println("Please enter user status:");
        while (true){
            try{
                user.setUserStatus(Integer.parseInt(console.read()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid value. Use digits!");
            }
        }
        return user;
    }
}
