package dailybook.hello.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/content")
public class MainController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String getHello(){
        return "User has access to this URL";
    }

}
