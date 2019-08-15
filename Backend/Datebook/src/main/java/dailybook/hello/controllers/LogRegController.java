package dailybook.hello.controllers;

import dailybook.hello.domain.Role;
import dailybook.hello.domain.User;
import dailybook.hello.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class LogRegController {

    private final UserRepo userRepo;

    @Autowired
    public LogRegController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String getRegistration(User user){
        return "registration";
    }


    @PostMapping("/registration")
    public String postRegistration(@Valid User user, BindingResult bindingResult){

        if (bindingResult.hasErrors() || userRepo.findByUsername(user.getUsername()) != null)
            return "registration";

        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);
        userRepo.save(user);

        return "redirect:/login";
    }

}
