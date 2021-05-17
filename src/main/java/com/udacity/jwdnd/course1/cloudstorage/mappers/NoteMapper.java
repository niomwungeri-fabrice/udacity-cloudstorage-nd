package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper extends GenericMapper<Note> {
    @Delete("DELETE FROM NOTES WHERE NOTEID=#{id}")
    @Override
    int delete(int id);

    @Insert("INSERT INTO NOTES (NOTETITLE, NOTEDESCRIPTION, USERID) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    @Override
    int addOne(Note note);

    @Select("SELECT * FROM NOTES WHERE NOTEID=#{id}")
    @Override
    Note getOne(int id);

    @Select("SELECT * FROM NOTES")
    @Override
    List<Note> getMyItems(int userId);

    @Update("UPDATE NOTES SET NOTETITLE=#{noteTitle}, NOTEDESCRIPTION=#{noteDescription} WHERE NOTEID=#{id}")
    int updateNote(int id, @Param("noteTitle") String noteTitle, @Param("noteDescription") String noteDescription);

}
