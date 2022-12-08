package com.hyerin.phonebook.controller;

import com.hyerin.phonebook.dto.ContactDto;
import com.hyerin.phonebook.dto.ResponseDto;
import com.hyerin.phonebook.repo.PhoneBookRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Api(tags = "전화번호부 API 2")
@RequestMapping(value = "/api/v2/app")
@RequiredArgsConstructor
public class ContactInMemoryController {
    private final PhoneBookRepository phoneBookRepository; // 메모리에 저장하고 조회

    @GetMapping("/contacts")
    @ApiOperation(value = "전체 전화번호부 조회(등록된 전화번호가 많은 순서, 이름 오름차순 정렬)(v2)", response=ContactDto.class)
    public ResponseEntity<?> findAllContacts(){

        List<Map.Entry<String, List<String>>> nameAndPhoneNumberList = phoneBookRepository.selectAllContacts();

        ResponseDto responseDTO = new ResponseDto();
        responseDTO.setResultCode("ok");
        responseDTO.setData(nameAndPhoneNumberList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/contacts/{name}")
    @ApiOperation(value = "특정이름의 전화번호 리스트 조회(v2)", response=ContactDto.class)
    public ResponseEntity<?> findContactByName(@PathVariable String name) {

        List<String> phoneNumbers = phoneBookRepository.selectContact(name);

        ResponseDto responseDTO = new ResponseDto();
        responseDTO.setResultCode("ok");
        responseDTO.setData(phoneNumbers);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/contacts")
    @ApiOperation(value = "전화번호 등록(v2)")
    public ResponseEntity<?> findAllUsers(@RequestBody ContactDto contact) {
        ResponseDto responseDTO = new ResponseDto();
        phoneBookRepository.insertContact(contact);
        responseDTO.setResultCode("ok");
        responseDTO.setData(contact);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
