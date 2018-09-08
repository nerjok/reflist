package com.refs.converters;

import com.refs.commands.CategoryCommand;
import com.refs.commands.UserCommand;
import com.refs.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.Convert;
import java.lang.annotation.Annotation;

@Component
public class UserToUserCommand implements Converter<User, UserCommand> {
    @Override
    public UserCommand convert(User source) {

        final UserCommand userCommand = new UserCommand();

        userCommand.setId(source.getId());
        userCommand.setName(source.getName());
        userCommand.setLastName(source.getName());
        userCommand.setParentUser(source.getParentUser());
        userCommand.setPassword(source.getPassword());
        userCommand.setUserRole(source.getUserRole());


        return userCommand;
    }
}
