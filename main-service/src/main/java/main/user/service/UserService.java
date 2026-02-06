package main.user.service;

import main.exception.ConditionsNotMetException;
import main.exception.NotFoundException;
import main.user.User;
import main.user.dto.UserCreateDto;

import java.util.Collection;
import java.util.List;

public interface UserService {
    User addUser(UserCreateDto user) throws ConditionsNotMetException;

    void deleteUserById(Long userId) throws NotFoundException;

    Collection<User> getUsersByParams(Integer from, Integer size, List<Long> ids);

    User findById(Long userId) throws NotFoundException;
}
