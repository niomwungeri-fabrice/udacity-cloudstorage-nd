package com.udacity.jwdnd.course1.cloudstorage.mappers;


import java.util.List;

public interface GenericMapper<T> {
    void delete(int id);
    int addOne(T t);
    T getOne(int id);
    T getOneByName(String anyString);
    List<T> getAll();

}
