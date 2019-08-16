package dailybook.hello.message.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class RegForm {

    @NotBlank
    @Size(min = 6, max = 30)
    private String name;

    @NotBlank
    @Size(min = 6, max = 30)
    private String username;

    @NotBlank
    @Size(min = 6, max = 60)
    private String password;

    private Set<String> role;

}