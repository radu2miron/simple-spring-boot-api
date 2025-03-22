package edu.tucn.li.security;

import edu.tucn.li.exceptions.UnauthorizedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:radu.miro@aut.utcluj.ro">Radu Miron</a>
 */
@RestController // creates an instance of the current class
@RequestMapping("/login") // maps the requests starting with '/login' to this controller
public class LoginController {

    @PostMapping
    public JwtTokenDTO login(@RequestBody CredentialsDTO credentialsDTO) {
        if ("user1".equals(credentialsDTO.getUsername()) && "pwd1".equals(credentialsDTO.getPassword())) {
            JwtTokenDTO jwtTokenDTO = new JwtTokenDTO();
            jwtTokenDTO.setToken(JwtUtil.generateToken(credentialsDTO.getUsername()));
            return jwtTokenDTO;
        } else {
            throw new UnauthorizedException("Bad credentials");
        }
    }
}
