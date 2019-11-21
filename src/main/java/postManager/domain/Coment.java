package postManager.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Entity
@Table(name = "Comentario")
public class Coment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @NotEmpty
    @Column(name = "Texto")
    private String text;
    @Column(name = "Moment")
    private Instant moment;


    public Coment(String id, @NotEmpty String text, Instant moment) {
        this.id = id;
        this.text = text;
        this.moment = moment;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
