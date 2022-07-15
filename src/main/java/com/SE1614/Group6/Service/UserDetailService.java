package com.SE1614.Group6.Service;

import com.SE1614.Group6.Email.token.ConfirmationToken;
import com.SE1614.Group6.Email.token.ConfirmationTokenService;
import com.SE1614.Group6.Model.User;
import com.SE1614.Group6.Repo.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
    private  final  static String USER_NOT_FOUND_MSG = "user with email %s found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    @SneakyThrows
    public String signUpUser(User user){
        boolean userExists = userRepository.findByEmail(user.getEmail())
                .isPresent();

        if(userExists){
            throw new IllegalAccessException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);


        String token  = UUID.randomUUID().toString();
        //TODO: send Confirmation token
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        //TODO: SEND EMAIL

        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
    public int lockedUser(String email) {
        return userRepository.lockedUser(email);
    }

}
