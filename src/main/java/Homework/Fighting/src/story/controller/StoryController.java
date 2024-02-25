package Homework.Fighting.src.story.controller;

import Homework.Fighting.config.BaseEntity;
import Homework.Fighting.config.BaseException;
import Homework.Fighting.config.BaseResponse;
import Homework.Fighting.src.story.dto.*;
import Homework.Fighting.src.story.entity.BlogEntity;
import Homework.Fighting.src.story.entity.CommentEntity;
import Homework.Fighting.src.story.service.StoryService;
import Homework.Fighting.src.user.dto.UserDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Transactional
public class StoryController {

    private final StoryService storyService;
    //로그인 세션을 만들기 전까지 활동 userId는 1로 통일
    private Long userId = 1L;

   /* @Autowired
    public StoryController(StoryService storyService){
        this.storyService = storyService;
    }*/

    //계정 생성 api
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

    //계정 수정 api
    @PatchMapping("/user/{user_id}")
    public BaseResponse<String> updateUser(@RequestBody @Valid UserDto userDto, @PathVariable("user_id") Long userId){
        try{
            storyService.updateUser(userDto, userId);
            return new BaseResponse<>("계정이 변경되었습니다.");
        }
        catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    //블로그 생성
    //로그인 세션을 만들기 전까지 userId는 1로 통일
    @PostMapping("/blog")
    public BaseResponse<String> createBlog(@RequestBody @Valid BlogDto blogDto){
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
    public BaseResponse<String> updateBlog(@RequestBody @Valid BlogDto blogDto, @PathVariable("blog_id") Long blogId){
        try{
            storyService.updateBlog(blogDto, userId, blogId);
            return new BaseResponse<>("블로그가 수정되었습니다.");
        }
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    //블로그 조회 구현 중
    /*@GetMapping("/blog/{blog_id}")
    public BaseResponse<GetBlogDto> getBlogScreen(@PathVariable("blog_id") Long blogId){
        try{
            return storyService.getBlogScreen(blogId);
        }
        catch (BaseException e){
            return new BaseResponse<GetBlogDto>(e.getStatus());
        }
    }*/

    //게시글 생성
    @PostMapping("/{blog_id}/post")
    public BaseResponse<String> createPost(@RequestBody @Valid PostDto postDto, @PathVariable("blog_id") Long blogId){
        try{
            storyService.createPost(postDto, userId, blogId);
            return new BaseResponse<>("게시글이 생성되었습니다.");
        }
        catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }

    }

    //게시글 조회
    @GetMapping("/{blog_id}/{post_id}")
    public BaseResponse<GetPostDto> GetPost(@PathVariable("blog_id") Long blogId, @PathVariable("post_id") Long postId){
        try{
            GetPostDto getPostDto = storyService.getPost(userId,blogId, postId);
            return new BaseResponse<>(getPostDto);

        }
        catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }


    //댓글 작성
    @PostMapping("/{post_id}/comment")
    public BaseResponse<String> createComment(@RequestBody @Valid CommentDto commentDto, @PathVariable("post_id") Long postId){
        try{
            storyService.createComment(commentDto,userId ,postId);
            return new BaseResponse<>("댓글이 작성되었습니다.");
        }catch (Exception e){
            return new BaseResponse<>(e.getMessage());
        }
    }




}
