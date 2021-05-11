package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CredentialMapper extends GenericMapper<Credential> {
}
