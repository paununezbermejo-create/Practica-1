package Business;

import Business.Entities.Client;
import Persistance.ClientJsonDao;

import java.util.List;

public class ClientManager {
    private List<Client> clients;
    private ClientJsonDao clientJsonDao;

    public ClientManager(ClientJsonDao clientJsonDao) {
        this.clientJsonDao = clientJsonDao;
        clients = clientJsonDao.readClientFile();
    }


}
