package Business;

import Business.Entities.Sale;
import Persistance.SaleCsvDao;

import java.util.ArrayList;
import java.util.List;

public class SaleManager {
    private SaleCsvDao saleCsvDao;
    private List<Sale> sales;

    //Constructor del saleManager
    public SaleManager(SaleCsvDao saleCsvDao) {
        this.saleCsvDao = new SaleCsvDao();
        sales = saleCsvDao.readSaleFile();
    }

    //Procediment que crea una nova venta
    public void addSale(Sale sale) {
        sales.add(sale);
        saleCsvDao.addSale(sale);
    }

    //Funcio que retorna el historial de ventes d'un client
    public List<Sale> getHistorialClient(int idClient) {
        List<Sale> filtradas = new ArrayList<>();
        //Bucle que recorret tot sale
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

    //Funcio que retorna totes les ventes guardades dins el sistema
    public List<Sale> getSales() {
        return sales;
    }

    //Funcio que retorna la comprovacio de que el fitxer existeix
    public boolean cheeckFile (){
        saleCsvDao = new SaleCsvDao();
        return saleCsvDao.cheekFile();
    }


}
