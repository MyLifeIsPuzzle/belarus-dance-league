package com.artsiomtretinnikov.service;

import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class LogService {

    public List<String> getLogs() {
        try {
            String logs = FileUtil.readAsString(new File("D:\\Programming\\Logs\\logfile.log"));
            return Arrays.asList(logs.split("\r\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
