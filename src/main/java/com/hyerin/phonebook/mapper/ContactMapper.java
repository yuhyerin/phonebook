package com.hyerin.phonebook.mapper;

import com.hyerin.phonebook.dto.ContactDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ContactMapper {
    List<ContactDto> findAllContact();

    void insertContact(ContactDto contact);
}
