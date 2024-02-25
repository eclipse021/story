package Homework.Fighting.src.user.service;

import Homework.Fighting.src.user.dto.LoginDto;
import Homework.Fighting.src.user.entity.UserEntity;
import Homework.Fighting.src.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void signUp(LoginDto loginDto) {
        //비밀번호 암호화
        String rawPassword = loginDto.getPassword();;
        String encPasswrod = bCryptPasswordEncoder.encode(rawPassword);

        UserEntity userEntity = new UserEntity(loginDto, encPasswrod);
        userEntity.setRole("ROLE_USER");

        userRepository.save(userEntity);
    }
}
