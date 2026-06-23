package com.ebl.TalentBoard.config;

import com.ebl.TalentBoard.model.User;
import com.ebl.TalentBoard.model.Role;
import com.ebl.TalentBoard.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verificamos si el admin "ebl" ya existe para no duplicarlo cada vez que reinicies
        String adminEmail = "ebl@talentboard.com"; // Usa el correo que prefieras para el login

        if (!userRepository.existsByEmail(adminEmail)) {
            User masterAdmin = User.builder()
                    .firstName("EBL")
                    .lastName("Master")
                    .email(adminEmail)
                    .password(passwordEncoder.encode("123")) // Aquí queda perfectamente hasheada
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(masterAdmin);
            System.out.println(">> Administrador maestro (ebl) creado exitosamente con contraseña hasheada.");
        }
    }
}