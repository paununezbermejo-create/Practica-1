package Persistance;
import com.google.gson.Gson;
import Business.Entities.Cart;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
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


    public void actualizaFile(List<Cart> nuevosCarts) {
        Gson gson = new Gson();
        File file = new File(path);
        List<Cart> cartsExistentes = new ArrayList<>();
        Type listType = new TypeToken<List<Cart>>() {}.getType();

        try {

            if (file.exists()) {
                try (FileReader reader = new FileReader(file)) {
                    cartsExistentes = gson.fromJson(reader, listType);

                    if (cartsExistentes == null) {
                        cartsExistentes = new ArrayList<>();
                    }
                }
            }
            // 2. Actualizar lista: eliminar los antiguos que coinciden con el userId
            for (Cart nuevo : nuevosCarts) {
                cartsExistentes.removeIf(c -> c.getClientId() == nuevo.getClientId());
                cartsExistentes.add(nuevo);
            }

            try (FileWriter writer = new FileWriter(file, false)) {
                gson.toJson(cartsExistentes, writer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean cheekFile (){
        File file = new File(path);
        return file.exists();
    }
}
