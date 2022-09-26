package pl.kacper.gamewebspringboot.user;

import lombok.*;
import pl.kacper.gamewebspringboot.validation.PasswordMatches;
import pl.kacper.gamewebspringboot.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class UserDto {
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    @ValidPassword // commit for easier registration
    private String password;
    private String matchingPassword;
    private Long id;
    @NotNull
    @NotEmpty
    @Email
    private String email;
}
