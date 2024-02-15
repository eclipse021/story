package Homework.Fighting.src.story.service;

import Homework.Fighting.config.BaseException;
import Homework.Fighting.config.BaseResponseStatus;
import Homework.Fighting.config.Status;
import Homework.Fighting.src.story.dto.BlogDto;
import Homework.Fighting.src.story.dto.CommentDto;
import Homework.Fighting.src.story.dto.PostDto;
import Homework.Fighting.src.story.entity.BlogEntity;
import Homework.Fighting.src.story.entity.CommentEntity;
import Homework.Fighting.src.story.entity.PostEntity;
import Homework.Fighting.src.story.repository.CommentRepository;
import Homework.Fighting.src.user.entity.UserEntity;
import Homework.Fighting.src.story.repository.BlogRepository;
import Homework.Fighting.src.story.repository.StoryRepository;
import Homework.Fighting.src.story.repository.PostRepository;
import Homework.Fighting.src.user.repository.UserRepository;
import Homework.Fighting.src.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final StoryRepository storyRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    public void createUser(UserDto userDto) throws BaseException {
        if(userRepository.existsUserEntitiesByNickname(userDto.getNickname()) == true){
            throw new BaseException(BaseResponseStatus.User_nickname_duplicate);
        }

        UserEntity user = new UserEntity(userDto);
        userRepository.save(user);

    }
    public void createBlog(BlogDto blogDto, Long userId) throws BaseException{
        UserEntity user = userRepository.findUserEntityByUserIdAndStatus(userId, Status.ACTIVE).orElseThrow(
                () ->  new BaseException(BaseResponseStatus.User_no_exist)
        );

        if(blogRepository.existsByName(blogDto.getName()) == true){
            throw new BaseException(BaseResponseStatus.Blog_name_already_exist);
        }

        BlogEntity blog = new BlogEntity(blogDto, user);
        user.getBlogList().add(blog);
        blogRepository.save(blog);

    }

    public void updateBlog(BlogDto blogDto, Long userId, Long blogId) throws BaseException{
        UserEntity user = userRepository.findUserEntityByUserIdAndStatus(userId, Status.ACTIVE).orElseThrow(
                () ->  new BaseException(BaseResponseStatus.User_no_exist)
        );

        BlogEntity blog = blogRepository.findBlogEntityByBlogIdAndStatus(blogId, Status.ACTIVE).orElseThrow(
                () -> new BaseException(BaseResponseStatus.Blog_no_exist)
        );

        if(blog.getUser() == user){
            if(blogRepository.existsByName(blogDto.getName()) == true){
                throw new BaseException(BaseResponseStatus.Blog_name_already_exist);
            }

            blog.updateBlog(blogDto);
            blogRepository.save(blog);
        }
        else {
            throw new BaseException(BaseResponseStatus.No_privilge);
        }
    }

    public BlogEntity getBlogScreen(Long blogId) throws BaseException{
        return blogRepository.findBlogEntityByBlogIdAndStatus(blogId, Status.ACTIVE).orElseThrow(
                () -> new BaseException(BaseResponseStatus.Blog_no_exist)
        );
    }

    public void createPost(PostDto postDto, Long userId, Long blogId) throws BaseException {
        UserEntity user = userRepository.findUserEntityByUserIdAndStatus(userId, Status.ACTIVE).orElseThrow(
                () -> new BaseException(BaseResponseStatus.User_no_exist)
        );
        BlogEntity blog = blogRepository.findBlogEntityByBlogIdAndStatus(blogId, Status.ACTIVE).orElseThrow(
                () -> new BaseException(BaseResponseStatus.Blog_no_exist)
        );

        PostEntity post = new PostEntity(postDto, user, blog);
        //연관관계 입장에서는 추가 안 해줘도 되지만 객체 입장에서 추가해주는게 좋음
        user.getPostList().add(post);
        blog.getPostList().add(post);
    }

    public void updateUser(UserDto userDto, Long userId) throws BaseException{
        UserEntity user = userRepository.findUserEntityByUserIdAndStatus(userId, Status.ACTIVE).orElseThrow(
                () -> new BaseException(BaseResponseStatus.User_no_exist)
        );
        if(userRepository.existsUserEntitiesByNickname(userDto.getNickname()) == true){
            throw new BaseException(BaseResponseStatus.User_nickname_duplicate);
        }
        else {
            user.updateUser(userDto);
            userRepository.save(user);
        }
    }

    public void createComment(CommentDto commentDto,Long userId ,Long postId) {
        //Optional을 사용함으로 자동으로 유효성 검사도 진행해준다.
        UserEntity user = userRepository.findUserEntityByUserIdAndStatus(userId, Status.ACTIVE).orElseThrow(
                () -> new BaseException(BaseResponseStatus.User_no_exist)
        );

        PostEntity post = postRepository.findByPostIdAndStatus(postId, Status.ACTIVE).orElseThrow(
                () -> new BaseException(BaseResponseStatus.Blog_no_exist)
        );
        try{
            //groupNumber가 없을 수도 있으니 유효성 검사 후 값을 넣어주자.
            Integer maxGroupNumber = commentRepository.maxGroupNumber();
            if(maxGroupNumber == null){
                CommentEntity comment = new CommentEntity(commentDto.getContent(),1 ,0,user,post);
            }else {
                CommentEntity comment = new CommentEntity(commentDto.getContent(),maxGroupNumber+1 ,0,user,post);
            }

        } catch (Exception e){
          throw new BaseException(BaseResponseStatus.Error);
        };

    }
}
