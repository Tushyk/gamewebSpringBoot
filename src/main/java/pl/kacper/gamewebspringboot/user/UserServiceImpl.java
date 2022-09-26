package pl.kacper.gamewebspringboot.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kacper.gamewebspringboot.error.UserAlreadyExistException;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder, VerificationTokenRepository tokenRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
    }
    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public User saveUser(UserDto userDto) throws UserAlreadyExistException {
        User user = userExistCheck(userDto);
        user.setEnabled(0);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
        return user;
    }
    @Override
    public void saveAdmin(UserDto userDto) throws UserAlreadyExistException  {
        User user = userExistCheck(userDto);
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
    @Override
    public User editUser(UserDto userDto) throws UserAlreadyExistException {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getEmail(), userDto.getEmail()) && !Objects.equals(user.getId(), userDto.getId())){
                throw new UserAlreadyExistException("konto o takim emailu juz isnieje: "
                        + userDto.getEmail());
            }
            if (Objects.equals(user.getUsername(), userDto.getUsername()) && !Objects.equals(user.getId(), userDto.getId())){
                throw new UserAlreadyExistException("konto o takim loginie juz isnieje: "
                        + userDto.getUsername());
            }
        }
        User user = userRepository.findById(userDto.getId()).orElseThrow(EntityNotFoundException::new);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        userRepository.save(user);
        return user;
    }
    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken();
        myToken.setUser(user);
        myToken.setToken(token);
        tokenRepository.save(myToken);
    }
    private User userExistCheck(UserDto userDto) {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("konto o takim emailu juz isnieje: "
                    + userDto.getEmail());
        }
        if (usernameExists(userDto.getUsername())) {
            throw new UserAlreadyExistException("konto o takim loginie juz istnieje: "
                    + userDto.getUsername());
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        return user;
    }
    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
    @Override
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }
    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
}
