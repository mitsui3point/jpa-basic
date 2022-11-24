package hellojpa.entity;

import javax.persistence.*;

@Entity
//테이블 컬럼 unique 제약조건 @Column unique = true 대신 보편적으로 사용한다.
//@Table(uniqueConstraints = { @UniqueConstraint(name = "uk_name", columnNames = "name")})
public class Member {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO//DB 방언에 맞춰 자동생성
    )
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
