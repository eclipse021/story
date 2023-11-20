package Homework.Fighting.src.hello.dto;

import java.util.List;

public class MemberListDto {
    private static List<MemberDto> memberDtoList;

    public void setMember(MemberDto memberDto){
        memberDtoList.add(memberDto);
    }
}
