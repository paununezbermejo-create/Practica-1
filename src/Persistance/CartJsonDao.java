package Persistance;
import com.google.gson.Gson;
import Business.Entities.Cart;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class CartJsonDao implements Cheek {

    private final String path = "data/cart.json";

    public List<Cart> readCartFile(){
        Gson gson = new Gson();
        try (FileReader fr = new FileReader(path);
             JsonReader reader = new JsonReader(fr)) {

            Type ListType = new TypeToken<List<Cart>>(){}.getType();

            return gson.fromJson(reader, ListType);

        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + path);
            return null;
        }
    }

    public Boolean createFile () {
        try {
            File file = new File(path);
            if (!cheekFile()) {
                return file.createNewFile();  // true si lo crea, false si ya existía
            }else{
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al crear archivo: " + path, e);
        }
    }

    @Override
    public boolean cheekFile (){
        File file = new File(path);
        return file.exists();
    }
}
