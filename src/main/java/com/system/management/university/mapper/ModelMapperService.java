package com.system.management.university.mapper;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {

    ModelMapper forResponse();

    ModelMapper forRequest();
}
