package com.ebl.TalentBoard.service;

import com.ebl.TalentBoard.dto.UserCreateByAdminDTO;
import com.ebl.TalentBoard.dto.UserRegisterDTO;
import com.ebl.TalentBoard.dto.UserResponseDTO;
import com.ebl.TalentBoard.model.Role;
import com.ebl.TalentBoard.model.User;
import com.ebl.TalentBoard.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserResponseDTO registerCandidate(UserRegisterDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword())) // Encriptar clave
                .role(Role.CANDIDATE)
                .build();

        User savedUser = userRepository.save(user);
        return UserResponseDTO.fromEntity(savedUser);
    }

    public UserResponseDTO createUserByAdmin(UserCreateByAdminDTO dto, User creator) {
        if (creator.getRole() != Role.ADMIN) {
            throw new RuntimeException("Acción denegada: Solo un administrador puede realizar esta operación");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole()) // El admin decide el rol (ADMIN o RECRUITER)
                .build();

        User savedUser = userRepository.save(user);
        return UserResponseDTO.fromEntity(savedUser);
    }

    public void deleteusers(Long id) {
        userRepository.deleteById(id);
    }


    public Page<UserResponseDTO> getAllUsersPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        Page<User> userPage = userRepository.findAll(pageable);

        return userPage.map(UserResponseDTO::fromEntity);
    }
}