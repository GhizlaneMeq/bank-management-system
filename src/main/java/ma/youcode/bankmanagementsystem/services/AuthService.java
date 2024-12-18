package ma.youcode.bankmanagementsystem.services;

import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.bankmanagementsystem.dtos.auth.AuthenticationResponseDto;
import ma.youcode.bankmanagementsystem.dtos.auth.SigninRequestDto;
import ma.youcode.bankmanagementsystem.dtos.auth.SignupRequestDto;

public interface AuthService {
    AuthenticationResponseDto signup(SignupRequestDto request, HttpServletResponse response);

    AuthenticationResponseDto signin(SigninRequestDto request, HttpServletResponse response);

    void signout(HttpServletResponse response);
}
