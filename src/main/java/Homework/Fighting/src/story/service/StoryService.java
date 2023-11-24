package Homework.Fighting.src.story.service;

import Homework.Fighting.config.BaseEntity;
import Homework.Fighting.config.BaseException;
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
        try {
            UserEntity user = new UserEntity(userDto);
            userRepository.save(user);
        }
        catch (Exception e){
            throw e;
        }

    }
    public void createBlog(BlogDto blogDto, Long userId) throws Exception{
        UserEntity user = userRepository.findUserEntityByUserIdAndStatus(userId, Status.ACTIVE).orElseThrow(
                () ->  new Exception("사용자가 존재하지 않습니다")
        );

        BlogEntity blog = new BlogEntity(blogDto, user);
        user.getBlogList().add(blog);
        blogRepository.save(blog);

        System.out.println("블로그가 생성 되었습니다");
    }

    public void updateBlog(BlogDto blogDto, Long userId, Long blogId) throws Exception{
        UserEntity user = userRepository.findUserEntityByUserIdAndStatus(userId, Status.ACTIVE).orElseThrow(
                () ->  new Exception("사용자가 존재하지 않습니다")
        );

        BlogEntity blog = blogRepository.findBlogEntityByBlogIdAndStatus(blogId, Status.ACTIVE).orElseThrow(
                () -> new Exception("블로그가 존재하지 않습니다")
        );

        if(blog.getUser() == user){
            blog.updateBlog(blogDto);

            blogRepository.save(blog);
        }
        else {
            throw new Exception("해당 사용자는 권한이 없습니다");
        }
    }

    public BlogEntity getBlogScreen(Long blogId) throws Exception{
        return blogRepository.findBlogEntityByBlogIdAndStatus(blogId, Status.ACTIVE).orElseThrow(
                () -> new Exception("블로그가 존재하지 않습니다")
        );
    }
}
