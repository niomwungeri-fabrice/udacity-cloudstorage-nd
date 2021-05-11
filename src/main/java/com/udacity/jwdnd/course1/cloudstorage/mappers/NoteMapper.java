package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoteMapper extends GenericMapper<Note> {
}
