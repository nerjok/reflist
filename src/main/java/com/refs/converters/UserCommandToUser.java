package com.refs.converters;

import com.refs.commands.CategoryCommand;
import com.refs.commands.UserCommand;
import com.refs.models.Category;
import com.refs.models.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserCommandToUser  implements Converter<UserCommand, User> {

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User convert(UserCommand source) {

        if (source == null) {
            return null;
        }

        final User user = new User();

        user.setId(source.getId());
        user.setName(source.getName());
        user.setLastName(source.getLastName());
        user.setParentUser(source.getParentUser());
        user.setPassword(bCryptPasswordEncoder.encode(source.getPassword()));
        user.setUsername(source.getUsername());
        user.setUserRole(source.getUserRole());

        return user;
    }
}
