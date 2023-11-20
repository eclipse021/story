package Homework.Fighting.src.hello.repository;

import Homework.Fighting.src.hello.dto.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    MemberDto save(MemberDto memberDto);
    Optional<MemberDto> findById(Long id);
    Optional<MemberDto> findByName(String name);

    List<MemberDto> findAll();


}
