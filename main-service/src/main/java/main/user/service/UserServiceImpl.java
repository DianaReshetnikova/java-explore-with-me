package main.user.service;

import lombok.RequiredArgsConstructor;
import main.exception.ConditionsNotMetException;
import main.exception.NotFoundException;
import main.user.User;
import main.user.UserMapper;
import main.user.UserRepository;
import main.user.dto.UserCreateDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User addUser(UserCreateDto userCreateDto) throws ConditionsNotMetException {
        checkEmailExists(userCreateDto.getEmail());
        return userRepository.save(UserMapper.toUser(userCreateDto));
    }

    @Override
    @Transactional
    public void deleteUserById(Long userId) throws NotFoundException {
        validateUserId(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public Collection<User> getUsersByParams(Integer from, Integer size, List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            return userRepository.findByIdIn(ids);
        } else {
            return userRepository.findAll(PageRequest.of(from, size)).stream()
                    .toList();
        }
    }

    @Override
    public User findById(Long userId) throws NotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id: " + userId + " не найден."));
    }

    private void validateUserId(Long userId) throws NotFoundException {
        if (userId == null) {
            throw new IllegalArgumentException("User id can not be null");
        }

        findById(userId);
    }

    private void checkEmailExists(String email) throws ConditionsNotMetException {
        if (userRepository.existsByEmail(email)) {
            throw new ConditionsNotMetException("Данный email уже занят.");
        }
    }
}