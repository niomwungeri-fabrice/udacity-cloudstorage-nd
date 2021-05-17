package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper extends GenericMapper<Credential> {
    @Delete("DELETE FROM CREDENTIALS WHERE CREDENTIALID=#{id}")
    @Override
    int delete(int id);

    @Insert("INSERT INTO CREDENTIALS (URL, USERNAME, KEY, PASSWORD,USERID) VALUES (#{url}, #{username}, #{key}, #{password},#{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    @Override
    int addOne(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE CREDENTIALID=#{id}")
    @Override
    Credential getOne(int id);

    @Select("SELECT * FROM CREDENTIALS WHERE USERID=#{id}")
    @Override
    List<Credential> getMyItems(int id);

    @Update("UPDATE CREDENTIALS SET URL=#{url}, USERNAME=#{username}, KEY=#{key}, PASSWORD=#{password} WHERE CREDENTIALID=#{id}")
    int updateCredential(int id, @Param("url") String url, @Param("username") String username, @Param("key") String key, @Param("password") String password);
}
