package hellojpa.entity;

import hellojpa.constants.RoleType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//테이블 컬럼 unique 제약조건 @Column unique = true 대신 보편적으로 사용한다.
//@Table(uniqueConstraints = { @UniqueConstraint(name = "uk_name", columnNames = "name")})
public class Member {
    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
