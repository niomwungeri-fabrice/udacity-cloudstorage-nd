package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public int createCredentials(Credential credential){
        return credentialMapper.addOne(credential);
    }

    public List<Credential> getMyCredentials(int userId){
        return credentialMapper.getMyItems(userId);
    }

    public Credential getOneCredential(int credentialId){
        return credentialMapper.getOne(credentialId);
    }

    public int updateCredentials(Credential credential){
        return credentialMapper.updateCredential(credential.getCredentialId(),  credential.getUrl(),credential.getUsername(), credential.getKey(),credential.getPassword());
    }
    public int deleteCredentials(int credentialId){
        return credentialMapper.delete(credentialId);
    }
}
