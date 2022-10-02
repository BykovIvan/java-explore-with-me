package ru.bykov.explore.services;

import ru.bykov.explore.model.dto.user.NewUserRequest;
import ru.bykov.explore.model.dto.user.UserDto;

import java.util.List;

public interface UserService {

    UserDto createFromAdmin(NewUserRequest newUserRequest);

    void deleteByIdFromAdmin(Long userId);

    List<UserDto> getByParamFromAdmin(Long[] ids, Integer from, Integer size);
}
