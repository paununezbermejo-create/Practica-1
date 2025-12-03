package Persistance;

import Business.Entities.Provider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ProviderJsonDao implements Cheek {
    private final String path = "data/providers.json";

    public List<Provider> readFile() {
        Gson gson = new Gson();

        try (FileReader fr = new FileReader(path);
             JsonReader reader = new JsonReader(fr)) {

            Type ListType = new TypeToken<List<Provider>>(){}.getType();

            return gson.fromJson(reader, ListType);

        } catch (Exception e) {
            return null;
        }
    }

    public void actualizaStock(List<Provider> providers) {
        Gson gson = new Gson();

        try (FileWriter fw = new FileWriter(path)) {
            gson.toJson(providers, fw);
        } catch (IOException e) {

        }
    }

    @Override
    public boolean cheekFile() {
        File file = new File(path);
        return file.exists();
    }
}
