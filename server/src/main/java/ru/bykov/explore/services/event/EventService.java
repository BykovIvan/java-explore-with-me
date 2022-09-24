package ru.bykov.explore.services.event;

import ru.bykov.explore.model.dto.event.EventDto;
import ru.bykov.explore.model.dto.event.EventRequestDto;
import ru.bykov.explore.model.dto.event.EventShortDto;

import java.util.List;

public interface EventService {

    List<EventShortDto> getAllForAllUsers();

    EventShortDto getByIdForAllUsers(Long eventId);

    //Получение событий, добавленых текущим пользователем
    List<EventDto> findByUserId(Long userId);

    EventDto updateByUserId(Long userId, EventDto eventDto);

    EventDto createByUserId(Long userId, EventDto eventDto);

    EventDto findByUserIdAndEventId(Long userId, Long eventId);

    EventDto canselByUserIdAndEventId(Long userId, EventDto eventDto, EventDto eventDto1);

    List<EventRequestDto> findRequestsByUserIdAndEventId(Long userId, Long eventId);

    EventRequestDto confirmRequestByUserIdAndEventId(Long userId, Long eventId, Long reqId);

    EventRequestDto rejectRequestByUserIdAndEventId(Long userId, Long eventId, Long reqId);

}
