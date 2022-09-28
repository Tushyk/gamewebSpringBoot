package pl.kacper.gamewebspringboot.user;

import pl.kacper.gamewebspringboot.error.UserAlreadyExistException;

public interface UserService {
    User findByUserName(String name);
    User saveUser(UserDto userDto);

    void saveAdmin(UserDto userDto) throws UserAlreadyExistException;

    User editUser(UserDto userDto) throws UserAlreadyExistException;

    void createVerificationToken(User user, String token);

    void  blockUser(Long id);

    void  unblockUser(Long id);

    boolean emailExists(String email);

    boolean usernameExists(String username);

    VerificationToken getVerificationToken(String VerificationToken);
}
