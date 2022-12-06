package am.itspace.smart_education.common.repository;

import am.itspace.smart_education.common.entity.Role;
import am.itspace.smart_education.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String username);

    List<User> findAllByRole(Role teacher);

    Optional<User> findByEmailAndVerifyToken(String email, String token);
}
