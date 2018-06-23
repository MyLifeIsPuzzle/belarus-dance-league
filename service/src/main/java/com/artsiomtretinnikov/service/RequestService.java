package com.artsiomtretinnikov.service;

import com.artsiomtretinnikov.dto.request.CreateRequestDto;
import com.artsiomtretinnikov.dto.request.RequestDto;
import com.artsiomtretinnikov.entity.DanceGroup;
import com.artsiomtretinnikov.entity.Request;
import com.artsiomtretinnikov.repository.DanceGroupRepository;
import com.artsiomtretinnikov.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.artsiomtretinnikov.converter.ModelToDtoConverter.requestCreateDtoToModel;

@Service
@Transactional
public class RequestService {

    private final RequestRepository requestRepository;
    private final DanceGroupRepository danceGroupRepository;
    private final EntityManager entityManager;

    @Autowired
    public RequestService(RequestRepository requestRepository, DanceGroupRepository danceGroupRepository, EntityManager entityManager) {
        this.requestRepository = requestRepository;
        this.danceGroupRepository = danceGroupRepository;
        this.entityManager = entityManager;
    }

    public void save(CreateRequestDto requestDto) {
        DanceGroup danceGroup = danceGroupRepository.findById(requestDto.getDanceGroupId()).orElse(null);

        Request request = requestCreateDtoToModel(requestDto);
        request.setDanceGroup(danceGroup);

        requestRepository.save(request);
    }

    public void delete(Long requestId) {
        requestRepository.deleteById(requestId);
    }

    public void activation(RequestDto requestDto, boolean isActive) {
        Request request = requestRepository.findById(requestDto.getId()).orElse(null);
        entityManager.detach(request);

        DanceGroup danceGroup = danceGroupRepository.findById(requestDto.getGroupId()).orElse(null);

        request.setVersion(requestDto.getVersion());
        request.setDanceGroup(danceGroup);
        request.setActive(isActive);

        requestRepository.save(request);
    }
}
