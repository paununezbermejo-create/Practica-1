package Persistance;

import Business.Entities.Sale;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SaleCsvDao implements Cheek {
    private final String path = "data/sales.csv";

    public List<Sale> readSaleFile()  {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of("data/sales.csv"));
            List<Sale> sales = new ArrayList<>();
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] parts = line.split(","); // separa por comas

                int clientId = Integer.parseInt(parts[0].trim());
                String productId = parts[1].trim();
                double pricePaid = Double.parseDouble(parts[2].trim());
                long purchaseDate = Long.parseLong(parts[3].trim());

                sales.add(new Sale(clientId, productId, pricePaid, purchaseDate));
            }
            return sales;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSale(Sale sale) {
        try (FileWriter fw = new FileWriter(path, true)) { // true = append
            String text = String.format("%d,%s,%.2f,%s",
                    sale.getClientId(),
                    sale.getProductId(),
                    sale.getPrice(),
                    sale.getPurchaseDate()  // si es String o LocalDate
            );
            fw.write(text + "\n"); // agrega un salto de línea al final
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createFile(String path) {
        try {
            java.io.File file = new java.io.File(path);
            if (!cheekFile()) {
                boolean created = file.createNewFile(); // true si se creó, false si ya existía
            }
        } catch (IOException e) {
        }
    }
    
    @Override
    public boolean cheekFile() {
        File file = new File(path);
        return file.exists();
    }
}
