package com.hyerin.phonebook.repo;

import com.hyerin.phonebook.dto.ContactDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class PhoneBookRepository {

    // key-value 구조 : [이름] - {전화번호1, 전화번호2, ... }
    private static Map<String, List<String>> phonebook = new ConcurrentHashMap<>();

    // 테스트용 데이터 추가
    @PostConstruct
    public void init() {
        phonebook.put("혜린",List.of("010-1111-2222","010-3333-4444","010-5555-6666"));
        phonebook.put("호야",List.of("010-0000-0000","010-0000-0001"));
        phonebook.put("쮸냥",List.of("010-0000-0002","010-0000-0003"));
        phonebook.put("혜민",List.of("010-1234-0000"));
        phonebook.put("뽀끼",List.of("010-1111-2223","010-3333-4445","010-5555-6667"));
        phonebook.put("찌냥",List.of("010-1234-9177"));
    }

    // 전화번호 저장
    public void insertContact(ContactDto contact){

        if(phonebook.containsKey(contact.getName())){
            phonebook.get(contact.getName()).add(contact.getPhone());
        }else{
            phonebook.put(contact.getName(), List.of(contact.getName()));
        }
    }

    // 특정 전화번호 조회
    public List<String> selectContact(String name){
        if(phonebook.containsKey(name)){
            return phonebook.get(name);
        }else{
            return List.of(); // 조건-없을 경우엔 무조건 빈 리스트를 반환해야 한다.
        }
    }

    // 등록된 전화번호 수가 많은 순서, 이름 오름차순으로 정렬하기
    public List<Map.Entry<String, List<String>>> selectAllContacts() {

        List<Map.Entry<String, List<String>>> result = phonebook.entrySet()
                                                                .stream()
                                                                .sorted(Map.Entry.comparingByKey())
                                                                .sorted((p1, p2)-> p2.getValue().size()-p1.getValue().size())
                                                                .collect(Collectors.toList());

        return result;
    }
}
