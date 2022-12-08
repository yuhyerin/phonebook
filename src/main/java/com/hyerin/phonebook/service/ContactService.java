package com.hyerin.phonebook.service;

import com.hyerin.phonebook.dto.ContactDto;
import com.hyerin.phonebook.mapper.ContactMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactMapper contactMapper;


    public List<ContactDto> findAllContact() {
        return contactMapper.findAllContact();
    }

    public void insertContact(ContactDto contact) {
        contactMapper.insertContact(contact);
    }
}
