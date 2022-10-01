package ru.bykov.explore.controllers.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.bykov.explore.model.dto.user.UserDto;
import ru.bykov.explore.services.UserService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "admin/users")
public class UserControllerAdmin {

    private final UserService userService;

    @PostMapping
    //TODO проверить нужна ли транзакция
    @Transactional
    public UserDto create(@RequestBody UserDto userDto) {
        log.info("Получен запрос к эндпоинту /admin/users. Метод POST");
        return userService.createFromAdmin(userDto);
    }

    @GetMapping()
    public List<UserDto> userById(@RequestParam(value = "ids") Long[] ids,
                                  @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                  @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Получен запрос к эндпоинту /admin/users получение пользователей по param. Метод GET");
        return userService.getByParamFromAdmin(ids, from, size);
    }

    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable("userId") Long userId) {
        log.info("Получен запрос к эндпоинту /admin/users удаление по id {}. Метод DELETE", userId);
        userService.deleteByIdFromAdmin(userId);
    }
}
