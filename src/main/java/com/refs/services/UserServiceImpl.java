package com.refs.services;

import com.refs.commands.UserCommand;
import com.refs.converters.UserCommandToUser;
import com.refs.converters.UserToUserCommand;
import com.refs.models.Category;
import com.refs.models.User;
import com.refs.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserToUserCommand userToUserCommand;
    private final UserCommandToUser userCommandToUser;

    public UserServiceImpl(UserRepository userRepository, UserToUserCommand userToUserCommand, UserCommandToUser userCommandToUser) {
        this.userRepository = userRepository;
        this.userToUserCommand = userToUserCommand;
        this.userCommandToUser = userCommandToUser;
    }

    @Override
    public Set<User> getUsers() {

        Set<User> users = new HashSet<>();
    log.debug("user list");
    System.out.println(userRepository.findAll());
        userRepository.findAll().iterator().forEachRemaining(users::add);
    System.out.println(users.size());
        return users;
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new RuntimeException("user Not Found!");
        }

        return user.get();
    }

    @Override
    public UserCommand saveUserCommand(UserCommand command) {

        //Advertisement detachedAdvertisement = advertisementCommandToAdvertisement,convert(command);

        User detachedUser = userCommandToUser.convert(command);

        User savedUser = userRepository.save(detachedUser);

        return userToUserCommand.convert(savedUser);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
