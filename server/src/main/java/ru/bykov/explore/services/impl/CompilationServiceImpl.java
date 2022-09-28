package ru.bykov.explore.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bykov.explore.exceptions.NotFoundException;
import ru.bykov.explore.model.Compilation;
import ru.bykov.explore.model.Event;
import ru.bykov.explore.model.dto.CompilationDto;
import ru.bykov.explore.repositories.CompilationRepository;
import ru.bykov.explore.services.CompilationService;
import ru.bykov.explore.utils.mapperForDto.CompilationMapper;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepository;
    @Override
    public List<CompilationDto> getAllForAll() {
        return compilationRepository.findAll().stream()
                .map(CompilationMapper::toCompilationDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompilationDto getByIdForAll(Long compilationId) {
        return CompilationMapper.toCompilationDto(compilationRepository.findById(compilationId).orElseThrow(() -> new NotFoundException("Нет такой подборки!")));
    }

    @Override
    public CompilationDto createByAdmin(CompilationDto compilationDto) {
        @Valid Compilation compilation = CompilationMapper.toCompilation(compilationDto);
        return CompilationMapper.toCompilationDto(compilationRepository.save(compilation));
    }

    @Override
    public void deleteByIdByAdmin(Long compId) {
        compilationRepository.findById(compId).orElseThrow(() -> new NotFoundException("Нет такой подборки!"));
        compilationRepository.deleteById(compId);
    }

    @Override
    public void deleteEventFromCompByAdmin(Long compId, Long eventId) {
        Compilation compilation = compilationRepository.findById(compId).orElseThrow(() -> new NotFoundException("Нет такой подборки!"));
        compilation.getEvents().removeIf(nextEvent -> nextEvent.getId() == (eventId));

//        Iterator<Cat> catIterator = cats.iterator();//создаем итератор
//        while(catIterator.hasNext()) {//до тех пор, пока в списке есть элементы
//
//            Cat nextCat = catIterator.next();//получаем следующий элемент
//            if (nextCat.name.equals("Филипп Маркович")) {
//                catIterator.remove();//удаляем кота с нужным именем
//            }
//        }
    }
}
