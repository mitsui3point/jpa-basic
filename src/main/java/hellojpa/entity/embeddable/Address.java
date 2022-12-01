package hellojpa.entity.embeddable;

import javax.persistence.Embeddable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(getCity(), address1.getCity()) &&
                Objects.equals(getAddress(), address1.getAddress()) &&
                Objects.equals(getZipcode(), address1.getZipcode());
    }

    @Override
    public int hashCode() {
        //https://tecoble.techcourse.co.kr/post/2020-07-29-equals-and-hashCode/
        return Objects.hash(getCity(), getAddress(), getZipcode());
    }
}
