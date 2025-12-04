package Business;

import Business.Entities.Client;
import Business.Entities.PhoneNumber;
import Persistance.ClientJsonDao;

import java.util.List;

public class ClientManager {
    private List<Client> clients;
    private ClientJsonDao clientJsonDao;

    public ClientManager(ClientJsonDao clientJsonDao) {
        this.clientJsonDao = clientJsonDao;
        clients = clientJsonDao.readClientFile();
    }

    public void signUp(int clientID, String name, List<PhoneNumber> phones){
        //Crea cliente
        Client client = new Client(clientID, name, phones);
        clients.add(client);
    }

    public Client logIn(int id){
        //Retornar cliente
        for (Client client : clients){
            if(id == client.getClientID()){
                //Retornar client
                return client;
            }
        }
        return null;
    }

    public Client getClientById(int id){
        for (Client client : clients){
            if(id == client.getClientID()){
                //Retornar client
                return client;
            }
        }
        return null;
    }

    public boolean cheeckFile (){
        ClientJsonDao clientJsonDao = new ClientJsonDao();
        return clientJsonDao.cheekFile();
    }

}
