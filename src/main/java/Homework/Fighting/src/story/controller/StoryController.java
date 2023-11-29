package Homework.Fighting.src.story.controller;

import Homework.Fighting.config.BaseException;
import Homework.Fighting.config.BaseResponse;
import Homework.Fighting.src.story.dto.BlogDto;
import Homework.Fighting.src.story.dto.UserDto;
import Homework.Fighting.src.story.entity.BlogEntity;
import Homework.Fighting.src.story.service.StoryService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Transactional
public class StoryController {

    private final StoryService storyService;

   /* @Autowired
    public StoryController(StoryService storyService){
        this.storyService = storyService;
    }*/

    //유저 생성
    /*@PostMapping("/user")
    public BaseReponse<String> createUser(@RequestBody @Valid UserDto userDto){
        try{
            storyService.createUser(userDto);
            return new BaseReponse<>("계정이 생성되었습니다");
        }
        catch (Exception e){
            return new BaseReponse<>(e.getMessage());
        }
    }*/

    @PostMapping("/user")
    public BaseResponse<String> createUser(@RequestBody @Valid UserDto userDto){
        try{
            storyService.createUser(userDto);
            return new BaseResponse<>("계정이 생성되었습니다.");
        }
        catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    //블로그 생성
    @PostMapping("/blog")
    public BaseResponse<String> createBlog(@RequestBody BlogDto blogDto, @RequestBody Long userId){
        try{
            storyService.createBlog(blogDto, userId);
            return new BaseResponse<>("블로그가 생성되었습니다.");
        }
       catch (BaseException e){
            System.out.println(e.getMessage());
            return new BaseResponse<>(e.getStatus());
       }
    }

    //블로그 수정
    @PatchMapping("/blog/{blog_id}")
    public BaseResponse<String> updateBlog(@RequestBody BlogDto blogDto, @RequestBody Long userId, @PathVariable("blog_id") Long blogId){
        try{
            storyService.updateBlog(blogDto, userId, blogId);
            return new BaseResponse<>("블로그가 수정되었습니다.");
        }
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
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
