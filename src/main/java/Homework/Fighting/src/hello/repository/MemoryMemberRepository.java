package Homework.Fighting.src.hello.repository;

import Homework.Fighting.src.hello.dto.MemberDto;
import Homework.Fighting.src.hello.dto.MemberListDto;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository{

    MemberListDto memberListDto;
    @Override
    public MemberDto save(MemberDto memberDto) {
        memberListDto.setMember(memberDto);
        return memberDto;
    }

    @Override
    public Optional<MemberDto> findById(Long id) {

        return Optional.empty();
    }

    @Override
    public Optional<MemberDto> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<MemberDto> findAll() {
        return null;
    }
}
