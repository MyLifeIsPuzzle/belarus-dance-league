package com.artsiomtretinnikov.service;

import com.artsiomtretinnikov.config.ServiceConfiguration;
import com.artsiomtretinnikov.dto.club.ClubForAllViewDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ClubServiceTest {

    @Autowired
    private ClubService clubService;

    @Test
    public void cacheTest() {
        clubService.getAll();
        List<ClubForAllViewDto> all = clubService.getAll();
        System.out.println(all);
    }
}
