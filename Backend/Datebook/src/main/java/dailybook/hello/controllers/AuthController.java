package dailybook.hello.controllers;

import dailybook.hello.domain.Role;
import dailybook.hello.domain.RoleName;
import dailybook.hello.domain.User;
import dailybook.hello.message.request.LoginForm;
import dailybook.hello.message.request.RegForm;
import dailybook.hello.message.response.JwtResponse;
import dailybook.hello.repos.RoleRepo;
import dailybook.hello.repos.UserRepo;
import dailybook.hello.security.jwt.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AuthController(AuthenticationManager authenticationManager, UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;

    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegForm regForm) {

        if (userRepo.existsByUsername(regForm.getUsername()))
            return new ResponseEntity<>("Failed -> Username is already exist!", HttpStatus.BAD_REQUEST);

        User user = new User(regForm.getName(), regForm.getUsername(), passwordEncoder.encode(regForm.getPassword()));

        Set<String> strRole = regForm.getRole();
        Set<Role> roles = new HashSet<>();

        strRole.forEach(role -> {
                    switch (role) {
                        default:
                            Role userRole = roleRepo.findByName(RoleName.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                            roles.add(userRole);
                    }
                }
        );

        user.setRoles(roles);
        userRepo.save(user);

        return ResponseEntity.ok().body("User have been added");

    }

    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getUsername(),
                        loginForm.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
