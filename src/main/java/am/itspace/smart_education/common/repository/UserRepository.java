package am.itspace.smart_education.common.repository;

import am.itspace.smart_education.common.entity.Role;
import am.itspace.smart_education.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String username);

    List<User> findAllByRole(Role teacher);
}
