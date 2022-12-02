package hellojpa.entity;

import hellojpa.entity.embeddable.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD",
            joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();


    @ElementCollection
    @CollectionTable(name = "ADDRESS",
            joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<>();

    public Member() {
    }

    public Member(String name, Address homeAddress) {
        this.name = name;
        this.homeAddress = homeAddress;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public List<Address> getAddressHistory() {
        return addressHistory;
    }

    //==연관관계 편의 메서드==//
    public void putHomeAddress(Address address) {
        this.homeAddress = address;
    }
}
