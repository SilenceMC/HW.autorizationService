package ru.netology.autorization_service.service;

import org.springframework.stereotype.Service;
import ru.netology.autorization_service.exeption.InvalidCredentials;
import ru.netology.autorization_service.exeption.UnauthorizedUser;
import ru.netology.autorization_service.model.UserLogin;
import ru.netology.autorization_service.repository.UserRepository;
import ru.netology.autorization_service.service.enums.Authorities;

import java.util.List;

@Service
public class AuthorizationService {
    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(UserLogin userLogin) {
        if (isEmpty(userLogin.getUser()) || isEmpty(userLogin.getPassword())) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(userLogin);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + userLogin.getUser());
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
