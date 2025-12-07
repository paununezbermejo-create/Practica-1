package Business;

import Business.Entities.Client;
import Business.Entities.PhoneNumber;
import Persistance.ClientJsonDao;

import java.util.List;

public class ClientManager {
    private List<Client> clients;
    private ClientJsonDao clientJsonDao;

    //Constructor del clientManager
    public ClientManager(ClientJsonDao clientJsonDao) {
        this.clientJsonDao = clientJsonDao;
        clients = clientJsonDao.readClientFile();
    }

    //Procediment que crea un nou client i el guarda dins el programa
    public void signUp(int clientID, String name, List<PhoneNumber> phones){
        Client client = new Client(clientID, name, phones);
        clients.add(client);
        clientJsonDao.actualizatClient(clients);
    }

    //Funcio que busca un client i el retorna
    public Client logIn(int id){
        //Bucle que recorre tot el client
        for (Client client : clients){
            if(id == client.getClientID()){
                return client;
            }
        }
        return null;
    }

    //Funcio que busca un client per el id
    public Client getClientById(int id){
        //Bucle que recorre tot el client
        for (Client client : clients){
            if(id == client.getClientID()){
                return client;
            }
        }
        return null;
    }

    //Funcio que retorna el nom d'un client a partir del id
    public String getNameById(int id){
        //Bucle que recorre tot el  client
        for (Client client : clients){
            if(id == client.getClientID()){
                return client.getName();
            }
        }
        return null;
    }

    //Funcio que retorna la comprovacio de que el fitxer existeix
    public boolean cheeckFile (){
        ClientJsonDao clientJsonDao = new ClientJsonDao();
        return clientJsonDao.cheekFile();
    }

}
