package com.webservice.tictactoe;

import com.webservice.tictactoe.domain.Player;
import com.webservice.tictactoe.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private PlayerRepository playerRepository;

    @Autowired
    public DataLoader(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void run(ApplicationArguments args) {
        playerRepository.save(new Player("admin", "lichenma123@gmail.com", passwordEncoder.encode("password")));
        playerRepository.save(new Player("lma", "lichenma@gmail.com", passwordEncoder.encode("password")));
        playerRepository.save(new Player("jli", "lichenma12@gmail.com", passwordEncoder.encode("password")));
        System.out.println("============= Initialized database =========");
    }
}
