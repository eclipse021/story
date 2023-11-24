package Homework.Fighting.src.story.service;

import Homework.Fighting.config.BaseException;
import Homework.Fighting.config.BaseResponseStatus;
import Homework.Fighting.config.Status;
import Homework.Fighting.src.story.dto.BlogDto;
import Homework.Fighting.src.story.dto.UserDto;
import Homework.Fighting.src.story.entity.BlogEntity;
import Homework.Fighting.src.story.entity.UserEntity;
import Homework.Fighting.src.story.repository.BlogRepository;
import Homework.Fighting.src.story.repository.StoryRepository;
import Homework.Fighting.src.story.repository.PostRepository;
import Homework.Fighting.src.story.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final StoryRepository storyRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

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

        System.out.println("블로그가 생성 되었습니다");
    }

    public void updateBlog(BlogDto blogDto, Long userId, Long blogId) throws BaseException{
        UserEntity user = userRepository.findUserEntityByUserIdAndStatus(userId, Status.ACTIVE).orElseThrow(
                () ->  new BaseException(BaseResponseStatus.User_no_exist)
        );

        BlogEntity blog = blogRepository.findBlogEntityByBlogIdAndStatus(blogId, Status.ACTIVE).orElseThrow(
                () -> new BaseException(BaseResponseStatus.Blog_no_exist)
        );

        if(blog.getUser() == user){
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
}
