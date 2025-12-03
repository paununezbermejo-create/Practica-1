package Business.Entities;

public class PhoneNumber {
    private String country_prefix;
    private String number;

    public PhoneNumber(String prefix, String number) {
        this.country_prefix = prefix;
        this.number = number;
    }

    public String getPrefix() {
        return this.country_prefix;
    }
    public String getNumber() {
        return this.number;
    }

}
