package pq.jdev.b001.bookstore.users.service;

import java.text.ParseException;

import org.springframework.security.core.userdetails.UserDetailsService;

import pq.jdev.b001.bookstore.users.model.Person;
import pq.jdev.b001.bookstore.users.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    Person findByEmail(String email);

    Person save(UserRegistrationDto registration);

    void updatePassword(String password, Long personId);
}
