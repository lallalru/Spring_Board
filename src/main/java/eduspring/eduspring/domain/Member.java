package eduspring.eduspring.domain;

import javax.persistence.*;

@Entity
@Table(name="MEMBER")
public class Member {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
