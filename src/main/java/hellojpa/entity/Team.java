package hellojpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    @Column(name = "TEAM_NAME")
    private String name;

    //read only; 연관관계 주인이 아닌 경우
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Member> getMembers() {
        return this.members;
    }

    //==연관관계 메서드==//
    public void addMember(Member member) {
        this.members.add(member);
        member.setTeam(this);
    }
}
