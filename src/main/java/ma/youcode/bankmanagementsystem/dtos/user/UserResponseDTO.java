package ma.youcode.bankmanagementsystem.dtos.user;

import lombok.*;
import ma.youcode.bankmanagementsystem.enums.Role;

@Builder
public record UserResponseDTO(
        Long id,
        String name,
        String email,
        int age,
        double monthlyIncome,
        int creditScore,
        Role role
) {
}
