package ma.youcode.bankmanagementsystem.services;

import ma.youcode.bankmanagementsystem.dtos.PageResponse;
import ma.youcode.bankmanagementsystem.dtos.user.*;
import ma.youcode.bankmanagementsystem.dtos.user.CreateUserRequestDTO;
import ma.youcode.bankmanagementsystem.dtos.user.UpdateUserRequestDTO;
import ma.youcode.bankmanagementsystem.dtos.user.UserResponseDTO;
import ma.youcode.bankmanagementsystem.entities.User;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserResponseDTO createUser(CreateUserRequestDTO request);

    UserResponseDTO getUserById(Long id);

    PageResponse<UserResponseDTO> getAllUsers(Pageable pageable);

    UserResponseDTO updateUser(Long id, UpdateUserRequestDTO request);

    void deleteUser(Long id);

    PageResponse<UserResponseDTO> searchUsers(String query, Pageable pageable);

    UserResponseDTO getCurrentUserProfile();

    PageResponse<UserResponseDTO> getAllCustomers(Pageable pageable);

    User getCurrentUser();
}