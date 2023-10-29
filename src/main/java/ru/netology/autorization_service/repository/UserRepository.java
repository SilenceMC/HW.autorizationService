package ru.netology.autorization_service.repository;

import org.springframework.stereotype.Repository;
import ru.netology.autorization_service.model.UserLogin;
import ru.netology.autorization_service.service.enums.Authorities;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {

    private Map<UserLogin, List<Authorities>> usersAuthorities = new ConcurrentHashMap<>();


    public List<Authorities> getUserAuthorities(UserLogin userLogin) {
        if (usersAuthorities.containsKey(userLogin)) {
            return usersAuthorities.get(userLogin);
        }
        return Collections.emptyList();
    }
}
