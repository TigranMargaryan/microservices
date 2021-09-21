package com.user.service.service;

import com.user.service.config.exception.InvalidEmailFormatException;
import com.user.service.domain.User;
import com.user.service.repository.UserRepository;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class UserService implements IUserService{

    private static final String EMAIL_VALIDATION = "[a-zA-Z0-9]{1,}[@]{1}[a-z]{5,}[.]{1}+[a-z]{3}";

    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(User user) throws DuplicateMemberException, InvalidEmailFormatException {

        if (!Pattern.matches(EMAIL_VALIDATION, user.getEmail())) {
            throw new InvalidEmailFormatException("email.invalid");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new DuplicateMemberException("email already exist");
        }
        userRepository.save(user);
    }

    @Override
    public User getUser(String id) {
        return userRepository.findById(id);
    }


}
