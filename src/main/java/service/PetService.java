package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.ApiResponse;
import models.Pet;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.Channels;
import java.nio.channels.Pipe;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PetService {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public Pet getPetById(URI uri, Long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + id))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), Pet.class);
    }

    public List<Pet> getPetByStatus(URI uri, String status) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/findByStatus?status=" + status))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), new TypeToken<List<Pet>>(){}.getType());
    }

    public Pet postPet(URI uri, Pet pet) throws IOException, InterruptedException {
        String requestBody = gson.toJson(pet);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), Pet.class);
    }

    public Pet putPet(URI uri, Pet pet) throws IOException, InterruptedException {
        String requestBody = gson.toJson(pet);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), Pet.class);
    }

    public void deletePet (URI uri, Long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + id))
                .header("Content-type", "application/json")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(""))
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response status of delete Pet is " + response.statusCode());
    }

    public ApiResponse updatePetFrom(URI uri, Long id, String name, String status) throws IOException, InterruptedException {
        String requestBody = "name=" + name + "&status=" + status;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + id))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ApiResponse.class);
    }

    public ApiResponse uploadPetImage (URI uri, Long id, Path path) throws IOException, InterruptedException {
        HttpEntity httpEntity = MultipartEntityBuilder.create()
                .addBinaryBody("file", new File(path.toUri()),
                        ContentType.parse(Files.probeContentType(path)), path.getFileName().toString())
                .build();
        Pipe pipe = Pipe.open();
        new Thread(() -> {
            try (OutputStream outputStream = Channels.newOutputStream(pipe.sink())) {
                httpEntity.writeTo(outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        HttpRequest request = HttpRequest.newBuilder(URI.create(uri + "/" + id + "/uploadImage"))
                .header("Content-Type", httpEntity.getContentType().getValue())
                .POST(HttpRequest.BodyPublishers.ofInputStream(() -> Channels.newInputStream(pipe.source()))).build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ApiResponse.class);
    }
}
