package Business;

import Business.Entities.Sale;
import Persistance.SaleCsvDao;

import java.util.ArrayList;
import java.util.List;

public class SaleManager {
    private SaleCsvDao saleCsvDao;
    private List<Sale> sales;

    public SaleManager(SaleCsvDao saleCsvDao) {
        this.saleCsvDao = new SaleCsvDao();
        this.sales = saleCsvDao.readSaleFile();
    }

    public void setSales (List<Sale> ventas) {
        sales.addAll(ventas);
    }

    public void addSale(Sale sale) {
        sales.add(sale);
    }

    public List<Sale> getHistorialClient(int idClient) {
        List<Sale> filtradas = new ArrayList<>();

        // Recolectar todas las ventas de ese cliente
        for (Sale sale : sales) {
            if (sale.getClientId() == idClient) {
                filtradas.add(sale);
            }
        }

        // Ordenar por fecha (descendente)
        filtradas.sort((a, b) -> Long.compare(b.getPurchaseDate(), a.getPurchaseDate()));

        // Devolver máximo 10
        if (filtradas.size() > 10) {
            return filtradas.subList(0, 10);
        } else {
            return filtradas; // si tiene menos de 10, devuelves todas
        }
    }

    public List<Sale> getSales() {
        return sales;
    }

    public boolean cheeckFile (){
        saleCsvDao = new SaleCsvDao();
        return saleCsvDao.cheekFile();
    }

}
