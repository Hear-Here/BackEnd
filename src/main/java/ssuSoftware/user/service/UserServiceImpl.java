package ssuSoftware.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssuSoftware.hearHear.core.dto.user.AuthResponse;
import ssuSoftware.user.entity.User;
import ssuSoftware.user.repository.UserRepository;
import ssuSoftware.user.service.UserService;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User getUser(Long id){
    return userRepository.findByUserId(id).orElseThrow()
    }
}
