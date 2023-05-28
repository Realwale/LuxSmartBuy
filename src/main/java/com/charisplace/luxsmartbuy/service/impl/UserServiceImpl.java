package com.charisplace.luxsmartbuy.service.impl;

import com.charisplace.luxsmartbuy.dto.users.SignUpResponseDTO;
import com.charisplace.luxsmartbuy.dto.users.SignupDTO;
import com.charisplace.luxsmartbuy.exceptions.CustomException;
import com.charisplace.luxsmartbuy.model.AuthenticationToken;
import com.charisplace.luxsmartbuy.model.User;
import com.charisplace.luxsmartbuy.repository.UserRepository;
import com.charisplace.luxsmartbuy.service.AuthenticationService;
import com.charisplace.luxsmartbuy.service.UserService;
import jakarta.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public SignUpResponseDTO signUp(SignupDTO signupDTO) throws CustomException {

        if(Objects.nonNull(userRepository.findByEmail(signupDTO.getEmail()))){
            throw new CustomException("User already exists!");
        }

        String encryptedPassword = signupDTO.getPassword();
        try{
            encryptedPassword = hashPassword(signupDTO.getPassword());
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }

        User user = new User(signupDTO.getFirstName(), signupDTO.getLastName(), signupDTO.getEmail(), encryptedPassword);
        try{
            userRepository.save(user);

            final AuthenticationToken authenticationToken = new AuthenticationToken(user);

            authenticationService.saveConfirmationToken(authenticationToken);

            return new SignUpResponseDTO("success", "User created successfully");
        } catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        byte[] digest = messageDigest.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return myHash;
    }
}
