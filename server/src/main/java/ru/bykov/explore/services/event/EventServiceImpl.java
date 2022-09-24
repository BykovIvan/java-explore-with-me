package ru.bykov.explore.services.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bykov.explore.exceptions.NotFoundException;
import ru.bykov.explore.model.User;
import ru.bykov.explore.model.dto.event.EventDto;
import ru.bykov.explore.model.dto.event.EventRequestDto;
import ru.bykov.explore.model.dto.event.EventShortDto;
import ru.bykov.explore.repositories.EventRepository;
import ru.bykov.explore.repositories.UserRepository;
import ru.bykov.explore.utils.mappingForDto.EventMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public List<EventShortDto> getAllForAllUsers() {
        return eventRepository.findAll().stream()
                .map(EventMapping::toEventShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventShortDto getByIdForAllUsers(Long eventId) {
        return EventMapping.toEventShortDto(eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Нет такого события!")));
    }

    @Override
    public List<EventDto> findByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Нет такого пользователя!"));
        return null;
    }

    @Override
    public EventDto updateByUserId(Long userId, EventDto eventDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Нет такого пользователя!"));
        return null;
    }

    @Override
    public EventDto createByUserId(Long userId, EventDto eventDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Нет такого пользователя!"));
        return null;
    }

    @Override
    public EventDto findByUserIdAndEventId(Long userId, Long eventId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Нет такого пользователя!"));
        return null;
    }

    @Override
    public EventDto canselByUserIdAndEventId(Long userId, EventDto eventDto, EventDto eventDto1) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Нет такого пользователя!"));
        return null;
    }

    @Override
    public List<EventRequestDto> findRequestsByUserIdAndEventId(Long userId, Long eventId) {
        return null;
    }

    @Override
    public EventRequestDto confirmRequestByUserIdAndEventId(Long userId, Long eventId, Long reqId) {
        return null;
    }

    @Override
    public EventRequestDto rejectRequestByUserIdAndEventId(Long userId, Long eventId, Long reqId) {
        return null;
    }
}
