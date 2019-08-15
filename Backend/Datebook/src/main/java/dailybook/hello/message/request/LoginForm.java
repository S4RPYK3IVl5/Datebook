package dailybook.hello.message.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginForm {

    @NotBlank
    @Size(min = 6, max = 60)
    private String username;

    @NotBlank
    @Size(min = 6, max = 60)
    private String password;
}
