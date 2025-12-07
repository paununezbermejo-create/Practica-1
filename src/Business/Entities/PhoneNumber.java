package Business.Entities;

public class PhoneNumber {
    private String country_prefix;
    private String number;

    //Constructor de phoneNumber
    public PhoneNumber(String prefix, String number) {
        this.country_prefix = prefix;
        this.number = number;
    }

    //Funcio que retorna el prefix del telefon
    public String getPrefix() {
        return this.country_prefix;
    }

    //Funcio que retorna el numero del telefon
    public String getNumber() {
        return this.number;
    }

}
