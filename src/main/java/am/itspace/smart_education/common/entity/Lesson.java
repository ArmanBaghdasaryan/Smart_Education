package am.itspace.smart_education.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Title title;
    private double price;
    @Enumerated(EnumType.STRING)
    private Duration duration;
    private double totalHours;
    private boolean isOnline;
    private String picture;
    @ManyToMany
    @JoinTable(name = "subscription",
    joinColumns = @JoinColumn(name = "lesson_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userSet;

}
