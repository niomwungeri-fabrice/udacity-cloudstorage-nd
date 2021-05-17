package com.udacity.jwdnd.course1.cloudstorage.utils;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Runners implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(Runners.class);
    NoteService noteService;
    UserService userService;
    FileService fileService;
    CredentialService credentialService;
    public Runners(NoteService noteService, UserService userService, FileService fileService, CredentialService credentialService) {
        this.noteService = noteService;
        this.userService = userService;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("====================== TESTING USERS ====================== ");
        User user = new User();
        user.setFirstname("Fabrice");
        user.setUsername("henry");
        user.setLastname("NIYOMWUNGERI");
        user.setPassword("123456");
        userService.createUser(user);
        User dbUser = userService.getUser(user.getUsername());
        logger.info("====================== TESTING NOTES ====================== ");
        // 1. test insert a Note
        Note note = new Note();
        note.setNoteTitle("TITLE");
        note.setNoteDescription("DESCDDDD");
        note.setUserId(dbUser.getUserid());
        int addedRows = noteService.createNote(note);
        System.out.println(addedRows == 1);
        // 2. Get One Note
        Note note1 = noteService.getOneNote(1);
        System.out.println(note1.getNoteTitle().equals(note.getNoteTitle()));
        // 3. Update a note
        note1.setNoteTitle("RASANA");
        int updatedRows = noteService.updateNote(note1.getNoteId(), note1.getNoteTitle(), note1.getNoteDescription());
        System.out.println(updatedRows==1);
        // 4. Get User notes
        List<Note> myNotes = noteService.getMyGets(dbUser.getUserid());
        System.out.println(myNotes.size() == 1);
        // 5 delete a note
        int deletedRows = noteService.deleteNote(note1.getNoteId());
        System.out.println(deletedRows==1);
        logger.info("====================== TESTING FILES ====================== ");
        List<File> myFiles = fileService.getMyFiles(dbUser.getUserid());
        System.out.println(myFiles.size() == 0);
        logger.info("====================== TESTING Credentials ====================== ");
        Credential credential = new Credential();
        credential.setKey("Hello");
        credential.setUsername(dbUser.getUsername());
        credential.setPassword("abc");
        credential.setUrl("www.google.com");
        credential.setUserid(dbUser.getUserid());
        // test add
        int addCreRows = credentialService.createCredentials(credential);
        System.out.println(addCreRows == 1);
        // get my creds
        List<Credential> myCreds = credentialService.getMyCredentials(dbUser.getUserid());
        System.out.println(myCreds.size() == 1);
        // get one cred
        Credential myCred = credentialService.getOneCredential(1);
        System.out.println(myCred.getUsername().equals(credential.getUsername()));
        // update
        myCred.setUrl("www.yahoo.com");

        int updatedCredRows = credentialService.updateCredentials(myCred);
        System.out.println(updatedCredRows ==1);
        // delete
        int deletedCred = credentialService.deleteCredentials(myCred.getCredentialId());
        System.out.println(deletedCred==1);

    }
}
