package com.ruankennedy.socialnetwork.service.userAreaService;


import com.ruankennedy.socialnetwork.dto.response.UserDTO;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.UserRepository;
import com.ruankennedy.socialnetwork.service.serviceAction.UserAreaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles(value = "test") // Quando o teste for rodado, ele será rodado em ambiente de teste.
@SpringBootTest
public class MyProfile { // Classe que testa o service associado com a funcionalidade MyProfile.

    @Autowired
    private UserAreaService userAreaService; // Usado para testar o método MyProfile.

    @Autowired
    private UserRepository userRepository; // Usado para buscar um usuário do banco.

    @Test
    void loggedUserData() {

        User user = userRepository.findByEmail("user2@hotmail.com").get(); // Usuário do banco de dados.

        UserDTO userDTO = userAreaService.myProfile(user); // Quando passado o usuário, o método retorna um DTO desse usuário.

        Assertions.assertEquals(user.getNickname(), userDTO.getNickname()); // O nome do DTO tem que ser o mesmo do usuário no banco.
        Assertions.assertEquals(user.getUsername(), userDTO.getEmail()); // O e-mail do DTO tem que ser o mesmo do usuário no banco.

    }

}
