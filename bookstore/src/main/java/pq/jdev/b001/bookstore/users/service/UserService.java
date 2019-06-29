package pq.jdev.b001.bookstore.users.service;

import java.text.ParseException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pq.jdev.b001.bookstore.users.model.Person;
import pq.jdev.b001.bookstore.users.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

//    Person findByEmailOrUsername(String email,String username);
    Person findByEmail(String email);
    Person findByUsername(String username);

    Person save(UserRegistrationDto registration);

    void updatePassword(String password, Long personId);


}
