package com.webservice.tictactoe.repository;

import com.webservice.tictactoe.domain.Game;
import com.webservice.tictactoe.domain.Move;
import com.webservice.tictactoe.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoveRepository extends CrudRepository<Move, Long> {

    List<Move> findByGame(Game game);
    List<Move> findByGameAndPlayer(Game game, Player player);
    int countByGameAndPlayer(Game game, Player player);
}
