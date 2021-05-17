package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int createNote(Note note){
        return noteMapper.addOne(note);
    }

    public List<Note> getMyGets(int userId){
        return noteMapper.getMyItems(userId);
    }

    public Note getOneNote(int noteId){
        return noteMapper.getOne(noteId);
    }

    public int updateNote(int noteId, String noteTitle, String noteDesc){
        return noteMapper.updateNote(noteId, noteTitle, noteDesc);
    }
    public int deleteNote(int noteId){
       return noteMapper.delete(noteId);
    }
}
