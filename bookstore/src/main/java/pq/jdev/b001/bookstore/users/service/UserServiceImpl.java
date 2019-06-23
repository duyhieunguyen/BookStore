package pq.jdev.b001.bookstore.users.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pq.jdev.b001.bookstore.users.model.Person;
import pq.jdev.b001.bookstore.users.model.Role;
import pq.jdev.b001.bookstore.users.repository.UserRepository;
import pq.jdev.b001.bookstore.users.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Person findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Date setbirth(String date)
    {
    	Date day;
		try {
			day = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(date);
			return day;
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return null;
    }
    public Person save(UserRegistrationDto registration) {
        Person user = new Person();
        user.setFirstname(registration.getFirstName());
        user.setLastname(registration.getLastName());
        user.setPhone(registration.getPhone());
        user.setAddress(registration.getAddress());
        user.setSex(registration.getSex());
        user.setBirthday((java.sql.Date ) new java.sql.Date(setbirth(registration.getBirthday()).getTime()));
        user.setEmail(registration.getEmail());
        user.setUsername(registration.getUserName());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_PERSON")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
            user.getPassword(),
            mapRolesToAuthorities(user.getRoles()));
    }

    private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection <Role> roles) {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    }

	@Override
	public void updatePassword(String password, Long personId) {
		
	}
}
