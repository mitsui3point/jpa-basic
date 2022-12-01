package hellojpa.entity.embeddable;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;
    private String address;
    private String zipcode;

    public Address() {
    }

    public Address(String city, String address, String zipcode) {
        this.city = city;
        this.address = address;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
