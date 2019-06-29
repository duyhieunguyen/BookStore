package pq.jdev.b001.bookstore.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pq.jdev.b001.bookstore.users.model.Person;


@Repository
public interface UserRepository extends JpaRepository<Person, Long> {

    Person findByEmail(String email);
    Person findByUsername(String username);
//    Person findByEmailOrUsername(String email,String username);
    @Modifying
    @Query("update Person u set u.password = :password where u.id = :id")
    void updatePassword(@Param("password") String password, @Param("id") Long id);

}
