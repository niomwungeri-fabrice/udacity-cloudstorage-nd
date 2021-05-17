package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper extends GenericMapper<File> {
    @Select("SELECT * FROM FILES")
    @Override
    List<File> getAll();
    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{filename}, #{contentType}, #{fileSize}, #{userid}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    @Override
    int addOne(File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    @Override
    int delete(int id);

    @Select("SELECT * FROM FILES WHERE fileid=#{fileId}")
    @Override
    File getOne(int id);
    @Select("SELECT * FROM FILES WHERE USERID=#{id}")
    @Override
    List<File> getMyItems(int id);

}
