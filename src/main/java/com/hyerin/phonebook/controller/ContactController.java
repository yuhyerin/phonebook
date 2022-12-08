package com.hyerin.phonebook.controller;

import com.hyerin.phonebook.dto.ResponseDto;
import com.hyerin.phonebook.dto.ContactDto;
import com.hyerin.phonebook.service.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "전화번호부 API")
@RequestMapping(value = "/api/v1/app")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/test")
    public String test(){
        return "ok";
    }

    @GetMapping("/contacts")
    @ApiOperation(value = "전화번호부 전체 조회", response=ContactDto.class)
    public ResponseEntity<?> findAllContact() {
        ResponseDto responseDTO = new ResponseDto();
        responseDTO.setResultCode("ok");
        List<ContactDto> list = contactService.findAllContact();
        for(ContactDto user : list){
            log.info(user.toString());
        }
        responseDTO.setData(list);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/contacts")
    @ApiOperation(value = "전화번호 등록")
    public ResponseEntity<?> insertContact(@RequestBody ContactDto contact) {
        ResponseDto responseDTO = new ResponseDto();
        contactService.insertContact(contact);
        responseDTO.setResultCode("ok");
        responseDTO.setData(contact);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
