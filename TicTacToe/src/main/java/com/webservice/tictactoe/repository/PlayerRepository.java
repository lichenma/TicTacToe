package com.webservice.tictactoe.repository;

import com.webservice.tictactoe.domain.Game;
import com.webservice.tictactoe.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    Player findOneByUserName(String userName);
}
