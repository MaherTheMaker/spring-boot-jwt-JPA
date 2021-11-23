package com.javainuse.dao;

import com.javainuse.model.MyParameter;
import org.springframework.data.repository.CrudRepository;

public interface ParameterRepo extends CrudRepository<MyParameter,Integer> {


    MyParameter findByMyKey(String key);
}
