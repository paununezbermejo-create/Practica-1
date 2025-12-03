package Business.Entities;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int client_id;
    private String full_name;
    private List<PhoneNumber> phone_numbers;

    public Client(int clientID, String name, ArrayList<PhoneNumber> phones) {
        this.client_id = clientID;
        this.full_name = name;
        this.phone_numbers = phones;
    }

    public int getClientID() {
        return this.client_id;
    }

    public String getName() {
        return this.full_name;
    }

    public List<PhoneNumber> getPhones() {
        return this.phone_numbers;
    }
}
