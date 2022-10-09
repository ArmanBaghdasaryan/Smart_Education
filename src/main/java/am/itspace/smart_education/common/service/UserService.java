package am.itspace.smart_education.common.service;

import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

}
