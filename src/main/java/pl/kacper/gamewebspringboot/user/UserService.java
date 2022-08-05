package pl.kacper.gamewebspringboot.user;

public interface UserService {
    User findByUserName(String name);
    void saveUser(User user);
}
