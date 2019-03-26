package com.webservice.tictactoe.service;

import com.webservice.tictactoe.domain.Game;
import com.webservice.tictactoe.domain.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class GameLogic {
    private final Game game;

    public GameLogic(Game game) {
        this.game=game;
    }

    /*
     * Checks for player win condition
     * @param positions - Board positions from player moves
     * @return true or false if player is a winner
     */
    static boolean isWinner(List<Position> positions) {

        return  getWinningPositions().stream().anyMatch(positions::containsAll);
    }

    /*
     * Stores list of winning position
     * @return the list of the winning position's list
     */
    static List<List<Position>> getWinningPositions() {
        List<List<Position>> winningPositions = new ArrayList<>();

        winningPositions.add(asList(new Position(1,1), new Position(1,2), new Position(1,3)));
        winningPositions.add(asList(new Position(2,1), new Position(2,2), new Position(2,3)));
        winningPositions.add(asList(new Position(3,1), new Position(3,2), new Position(3,3)));

        winningPositions.add(asList(new Position(1,1), new Position(2,1), new Position(3,1)));
        winningPositions.add(asList(new Position(1,2), new Position(2,2), new Position(3,2)));
        winningPositions.add(asList(new Position(1,3), new Position(2,2), new Position(3,3)));


        winningPositions.add(asList(new Position(1,1), new Position(2,2), new Position(3,3)));
        winningPositions.add(asList(new Position(3,1), new Position(2,2), new Position(1,3)));

        return winningPositions;
    }

    /*
     * Stores all pairs of available rows and columns
     * @return list of all board's positions
     */
    static List<Position> getAllPositions() {

        List<Position> positions = new ArrayList<>();
        for (int row=1; row<=3; row++) {
            for (int col=1; col<=3; col++) {
                positions.add(new Position(row,col));
            }
        }
        return positions;
    }


    /*
     * @param numberOfFirstPlayerMovesInGame
     * @param numberOfSecondPlayerMovesInGame
     * @return true or false depending on the count of the player's moves
     */
    static boolean playerTurn(int numberOfFirstPlayerMovesInGame, int numberOfSecondPlayerMovesInGame) {
        return numberOfFirstPlayerMovesInGame == numberOfSecondPlayerMovesInGame || numberOfFirstPlayerMovesInGame == 0;
    }

    static boolean boardIsFull(List<Position> takenPositions) {
        return takenPositions.size() == 9;
    }

    // GENERATE COMPUTER'S MOVES
    static List<Position> getOpenPositions(List<Position> takenPositions) {
        return getAllPositions().stream().filter(p -> !takenPositions.contains(p)).collect(Collectors.toList());
    }

    static Position nextAutoMove(List<Position> takenPositions) {
        return getOpenPositions(takenPositions).get(0);
    }
}
