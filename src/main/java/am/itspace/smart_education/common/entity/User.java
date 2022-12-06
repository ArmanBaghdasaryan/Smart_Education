package am.itspace.smart_education.common.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String bio;
    private String picture;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany
    @JoinTable(name = "subscription",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    private Set<Lesson> lessons;
    private String verifyToken;
    private boolean isEnable;
}
