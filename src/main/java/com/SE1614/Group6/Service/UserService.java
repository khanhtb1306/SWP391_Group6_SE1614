package com.SE1614.Group6.Service;

import com.SE1614.Group6.Exception.UserNotFoundException;
import com.SE1614.Group6.Model.User;
import com.SE1614.Group6.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository repo;

    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }

    public User save(User user) {
        User user1 = repo.save(user);
        return user1;
    }


    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID " + id);
    }

    public User getEmail(String email) throws UserNotFoundException {
        Optional<User> result = repo.findByEmail(email);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with email " + email);
    }

    public void delete(Integer id) throws UserNotFoundException {

        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("Could not find any users with ID " + id);
        }
        repo.deleteById(id);
    }

    public void updateResetPassword(String token, String email) throws UserNotFoundException {
        Optional<User> user = repo.findByEmail(email);
        if(user !=null){
            user.get().setResetPassword(token);
            repo.save(user.get());
        }else{
            throw new UserNotFoundException("Could not find any users with email " + email);
        }
    }
    public User getPass(String reset_pass_token){
        return repo.findByResetPassword(reset_pass_token);
    }

    public void updatePassword(User user, String newPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPassword(null);
        repo.save(user);
    }

    public void ChangePassword(User user, String newPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(newPassword);
        user.setPassword(encodedPassword);
        repo.save(user);
    }



}
