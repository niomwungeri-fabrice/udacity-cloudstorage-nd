package com.udacity.jwdnd.course1.cloudstorage.mappers;


import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GenericMapper<T> {
    int delete(int id);

    int addOne(T t);

    T getOne(int id);

    T getOneByName(String anyString);

    List<T> getAll();

    List<T> getMyItems(int id);


}
