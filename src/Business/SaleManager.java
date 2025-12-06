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
        sales = saleCsvDao.readSaleFile();
    }

    public void addSale(Sale sale) {
        sales.add(sale);
        saleCsvDao.addSale(sale);
    }

    public List<Sale> getHistorialClient(int idClient) {
        List<Sale> filtradas = new ArrayList<>();

        for (Sale sale : sales) {
            if (sale.getClientId() == idClient) {
                filtradas.add(sale);
            }
        }

        filtradas.sort((a, b) -> Long.compare(b.getPurchaseDate(), a.getPurchaseDate()));

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
