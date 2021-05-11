package com.udacity.jwdnd.course1.cloudstorage.mappers;


import java.util.List;

public interface GenericMapper<T> {
    void delete(T id);
    int addOne(T t);
    T getOne(int id);
    List<T> getAll();
}
