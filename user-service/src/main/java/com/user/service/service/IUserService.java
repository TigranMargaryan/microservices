package com.user.service.service;

import com.user.service.config.exception.InvalidEmailFormatException;
import com.user.service.domain.User;
import javassist.bytecode.DuplicateMemberException;

public interface IUserService {

    void create(User user) throws DuplicateMemberException, InvalidEmailFormatException;

    User getUser(String id);
}
