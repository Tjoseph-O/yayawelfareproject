package com.yayawelfare.welfareproject.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
//    public ModelMapper mapper(){
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setSkipNullEnabled(true);
//        mapper.getConfiguration().setAmbiguityIgnored(true);
//        return mapper;
//    }

    public ModelMapper modelMapper(){return new ModelMapper();}
}
