package hellojpa.entity.embeddable;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;

    public Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    private void setStreet(String address) {
        this.street = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(getCity(), address1.getCity()) &&
                Objects.equals(getStreet(), address1.getStreet()) &&
                Objects.equals(getZipcode(), address1.getZipcode());
    }

    @Override
    public int hashCode() {
        //https://tecoble.techcourse.co.kr/post/2020-07-29-equals-and-hashCode/
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }
}
