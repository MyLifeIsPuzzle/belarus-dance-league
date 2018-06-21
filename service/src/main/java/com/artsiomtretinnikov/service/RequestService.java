package com.artsiomtretinnikov.service;

import com.artsiomtretinnikov.dto.request.CreateRequestDto;
import com.artsiomtretinnikov.entity.DanceGroup;
import com.artsiomtretinnikov.entity.Request;
import com.artsiomtretinnikov.repository.DanceGroupRepository;
import com.artsiomtretinnikov.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.artsiomtretinnikov.converter.ModelToDtoConverter.requestCreateDtoToModel;

@Service
@Transactional
public class RequestService {

    private final RequestRepository requestRepository;
    private final DanceGroupRepository danceGroupRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository, DanceGroupRepository danceGroupRepository) {
        this.requestRepository = requestRepository;
        this.danceGroupRepository = danceGroupRepository;
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

    public void activation(Long requestId, boolean isActive) {
        Request request = requestRepository.findById(requestId).orElse(null);

        assert request != null;
        request.setActive(isActive);

        requestRepository.save(request);
    }
}
