package com.example.crud;

import com.example.crud.Model.User;
import com.example.crud.Repo.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.SQLOutput;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//test real database
@Rollback(true)//let data not update in database
public class UserRepositoryTest {
    @Autowired private UserRepository repo;

    @Test
    public void testAddUser(){
        User user=new User();
        user.setFullName("Nguyen Van A");
        user.setAddress("Moon");
        user.setAvatar("http://localhost");
        user.setEmail("lmaolmado@gmail.com");
        user.setGender("nam");
        user.setPassword("lmao");
        user.setPhone("1234567890");
        user.setRole(3);
        user.setUsername("lmao");

        User savedUser=repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for(User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
        Integer id=3;
        Optional<User> optionalUser = repo.findById(id);
        User user=optionalUser.get();
        user.setPassword("you have been changed");
        repo.save(user);

        User updatedUser=repo.findById(id).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("you have been changed");
    }

    @Test
    public void testGet(){
        Integer id=4;
        Optional<User> optionalUser = repo.findById(id);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser);
    }

    @Test
    public void testDelete(){
        Integer id=3;
        repo.deleteById(id);
        Optional<User> optionalUser = repo.findById(id);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
