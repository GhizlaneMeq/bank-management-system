package ma.youcode.bankmanagementsystem.dtos.auth;

import lombok.*;

@Builder
public record AuthenticationResponseDto(
        String name,
        String email,
        String role,
        String profilePicture
) {
}