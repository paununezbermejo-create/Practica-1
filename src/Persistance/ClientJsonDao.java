package Persistance;

import Business.Entities.Client;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ClientJsonDao implements Cheek {

    private final String path = "data/clients.json";

    public List<Client> readClientFile() {
        Gson gson = new Gson();

        try (FileReader fr = new FileReader(path);
             JsonReader reader = new JsonReader(fr)) {

            Type ListType = new TypeToken<List<Client>>(){}.getType();

            return gson.fromJson(reader, ListType);

        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + path);
            return null;
        }
    }

    @Override
    public boolean cheekFile() {
        File file = new File(path);

        if (!file.exists()) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Error al crear archivo: " + path, e);
            }
        }
        return false; // ya existía
    }

    public void actualizatClient(Client client) {
        Gson gson = new Gson();

        try (FileWriter fw = new FileWriter(path)) {
            gson.toJson(client, fw);
        } catch (IOException e) {
            throw new RuntimeException("Error al actualizar cliente en: " + path, e);
        }
    }

    public boolean createFile() {
        File file = new File(path);

        if (!file.exists()) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Error al crear archivo: " + path, e);
            }
        }
        return false; // ya existía
    }
}
