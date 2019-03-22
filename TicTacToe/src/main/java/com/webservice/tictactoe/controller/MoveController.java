package com.webservice.tictactoe.controller;

import com.webservice.tictactoe.DTO.CreateMoveDTO;
import com.webservice.tictactoe.domain.Game;
import com.webservice.tictactoe.domain.Move;
import com.webservice.tictactoe.service.GameService;
import com.webservice.tictactoe.service.MoveService;
import com.webservice.tictactoe.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/move")
public class MoveController {

    @Autowired
    private MoveService moveService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Autowired
    private HttpSession httpSession;

    Logger logger = LoggerFactory.getLogger(MoveController.class);

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Move createMove(@RequestBody CreateMoveDTO createMoveDTO) {

        Long gameId = (Long) httpSession.getAttribute("gameId");
        logger.info("Move to Insert: " + createMoveDTO.getBoardColumn() + createMoveDTO.getBoardRow());

        Move move = moveService.createMove(gameService.getGame(gameId), playerService.getLoggedUser(), createMoveDTO);
        Game game = gameService.getGame(gameId);
        gameService.updateGameStatus(gameService.getGame(gameId), moveService.checkCurrentGameStatus(game));

        return move;
    }

    @RequestMapping(value = "/autocreate", method = RequestMethod.GET)
    public Move autoCreateMove() {

        Long gameId = (Long) httpSession.getAttribute("gameId");
        logger.info("AUTO Move to Insert: ");

        Move move = moveService.autoCreateMove(gameService.getGame(gameId));
        Game game = gameService.getGame(gameId);
        
    }
}
