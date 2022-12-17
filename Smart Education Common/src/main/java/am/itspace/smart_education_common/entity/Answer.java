package am.itspace.smart_education_common.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answers")
@Builder
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String answerText;
    @ManyToOne
    private Question question;
    @ManyToOne
    private User user;

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answerText='" + answerText + '\'' +
                ", question=" + question.getId() +
                ", user=" + user +
                '}';
    }
}
