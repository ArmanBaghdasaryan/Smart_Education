package am.itspace.smart_education.common.repositories;

import am.itspace.smart_education.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
