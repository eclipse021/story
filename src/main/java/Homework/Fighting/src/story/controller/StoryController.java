package Homework.Fighting.src.story.controller;

import Homework.Fighting.src.story.dto.BlogDto;
import Homework.Fighting.src.story.dto.UserDto;
import Homework.Fighting.src.story.entity.BlogEntity;
import Homework.Fighting.src.story.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StoryController {

    private final StoryService storyService;

   /* @Autowired
    public StoryController(StoryService storyService){
        this.storyService = storyService;
    }*/

    //유저 생성
    @PostMapping("/user")
    public String createUser(@RequestBody UserDto userDto){
        try{
            storyService.createUser(userDto);
            return "계정이 생성되었습니다";
        }
        catch (Exception e){
            e.getMessage();
            return "예외처리";
        }
    }

    //블로그 생성
    @PostMapping("/blog")
    public String createBlog(@RequestBody BlogDto blogDto, @RequestBody Long userId){
        try{
            storyService.createBlog(blogDto, userId);
            return "블로그가 생성되었습니다";
        }
       catch (Exception e){
            e.getMessage();
            return "예외처리";
       }
    }

    //블로그 수정
    @PatchMapping("/blog/{blog_id}")
    public String updateBlog(@RequestBody BlogDto blogDto, @RequestBody Long userId, @PathVariable("blog_id") Long blogId){
        try{
            storyService.updateBlog(blogDto, userId, blogId);
            return "블로그가 수정되었습니다";
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    //블로그 보기
    @GetMapping("/blog/{blog_id}")
    public BlogEntity getBlogScreen(@PathVariable("blog_id") Long blogId){
        try{
            return storyService.getBlogScreen(blogId);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new BlogEntity();
        }
    }



}
