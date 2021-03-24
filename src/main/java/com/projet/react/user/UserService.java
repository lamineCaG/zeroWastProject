package com.projet.react.user;

import com.projet.react.registration.token.ConfirmationToken;
import com.projet.react.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final static String USER_NO_FOUND =
            "use with email %s not found";
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    @Override
    public UserDetails loadUserByUsername(String email )
            throws UsernameNotFoundException {
        return usersRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NO_FOUND,email)));
    }

    public String signUpUser(Users users){
        boolean userExits = usersRepository.findByEmail(users.getEmail())
                .isPresent();
        if (userExits){
            throw new IllegalStateException("email already taken");
        }
        String encodePassword = bCryptPasswordEncoder
                .encode(users.getPassword());
        users.setPassword(encodePassword);
        usersRepository.save(users);

        String token = UUID.randomUUID().toString();
        //TODO: send confirmation token
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                users
        );
        confirmationTokenService
                .saveConfirmationToken(confirmationToken);
//TODO: SEND MAIL
        return token;
    }

    public int enableUser(String email) {
        return usersRepository.enableUser(email);
    }
}
