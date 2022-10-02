package ru.bykov.explore.controllers.notUsers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bykov.explore.model.dto.NewCategoryDto;
import ru.bykov.explore.services.CategoryService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<NewCategoryDto> allCategories() {
        log.info("Получен запрос к эндпоинту /categories получение всех. Метод GET");
        return categoryService.getAllForAllUsers();
    }

    @GetMapping("/{catId}")
    public NewCategoryDto categoryById(@PathVariable("catId") Long categoryId) {
        log.info("Получен запрос к эндпоинту /categories получение по id. Метод GET");
        return categoryService.getByIdForAllUsers(categoryId);
    }

}
