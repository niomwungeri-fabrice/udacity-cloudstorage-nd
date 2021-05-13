package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {

        this.fileMapper = fileMapper;
    }

    public List<File> getAllFiles(){
       return fileMapper.getAll();
    }

    public String saveFile(File file){
        int addedRow = fileMapper.addOne(file);
        if (addedRow < 0) {
            return  "There was an error signing you up. Please try again.";
        }
        return "File added successfully";
    }

    public void deleteFile(int fileId){
        fileMapper.delete(fileId);
    }

    public File getOneFile(int fileId){
        return fileMapper.getOne(fileId);
    }

}
