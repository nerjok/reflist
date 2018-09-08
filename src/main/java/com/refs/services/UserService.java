package com.refs.services;

import com.refs.commands.CategoryCommand;
import com.refs.commands.UserCommand;
import com.refs.models.User;

import java.util.Set;

public interface UserService {

    Set<User> getUsers();

    User findById(Long id);

    UserCommand saveUserCommand(UserCommand command);

    void deleteById(Long id);

}
