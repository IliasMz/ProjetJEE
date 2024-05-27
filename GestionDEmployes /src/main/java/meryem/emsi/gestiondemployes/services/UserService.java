package meryem.emsi.gestiondemployes.services;

import meryem.emsi.gestiondemployes.entities.User;
import meryem.emsi.gestiondemployes.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email){
        User user;
        user =  userRepository.findUserByEmail(email);
        return user;
    }
    public  User createUser(User user){
        User newuser = userRepository.save(user);
        userRepository.flush();
        return newuser;
    }


}