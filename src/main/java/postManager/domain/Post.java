package postManager.domain;


import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "Titulo")
    private String title;

    @Column(name = "Corpo")
    private String body;

    @Column(name = "Moment")
    private Instant moment;

    public Post(String id, String title, String body, Instant moment) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.moment = moment;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
