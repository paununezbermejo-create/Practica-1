package Persistance;

import Business.Entities.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ProductJsonDao implements Cheek {
    private final String path = "data/products.json";

    public List<Product> readFile(){
        Gson gson = new Gson();

        try (FileReader fr = new FileReader(path);
             JsonReader reader = new JsonReader(fr)) {

            Type ListType = new TypeToken<List<Product>>(){}.getType();

            return gson.fromJson(reader, ListType);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean cheekFile() {
        File file = new File(path);
        return file.exists();
    }
}
