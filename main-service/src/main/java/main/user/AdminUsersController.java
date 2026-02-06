package main.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import main.exception.ConditionsNotMetException;
import main.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import main.user.dto.UserCreateDto;
import main.user.dto.UserFullDto;
import main.user.service.UserService;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/users", produces = "application/json")
public class AdminUsersController {
    private final UserService userService;

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserFullDto addUser(@RequestBody @Valid UserCreateDto userCreate) throws ConditionsNotMetException {
        User user = userService.addUser(userCreate);
        return UserMapper.toUserFullDto(user);
    }

    @DeleteMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUserById(@PathVariable @NotNull Long userId) throws NotFoundException {
        userService.deleteUserById(userId);
    }

    @GetMapping
    public Collection<UserFullDto> getUsersByParams(
            @RequestParam(name = "from", defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(name = "size", defaultValue = "10") @Positive Integer size,
            @RequestParam(name = "ids", required = false) List<Long> ids) {
        Collection<User> users = userService.getUsersByParams(from, size, ids);

        return users.stream()
                .map(UserMapper::toUserFullDto)
                .toList();
    }
}
