package pl.kacper.gamewebspringboot.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private final pl.kacper.gamewebspringboot.user.User user;
    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.kacper.gamewebspringboot.user.User user) {
        super(username, password, authorities);
        this.user = user;
    }
    public pl.kacper.gamewebspringboot.user.User getUser() {return user;}

}
