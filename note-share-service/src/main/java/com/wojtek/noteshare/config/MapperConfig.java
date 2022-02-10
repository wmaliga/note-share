package com.wojtek.noteshare.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MapperConfig {

    @Bean
    public Mapper mapper() {
        List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add("dozer.xml");

        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(mappingFiles);
        return mapper;
    }
}
