package com.webservice.tictactoe.service;

import com.webservice.tictactoe.domain.Game;
import com.webservice.tictactoe.domain.Position;
import javafx.geometry.Pos;
import org.hibernate.collection.internal.PersistentBag;

import java.util.ArrayList;
import java.util.List;

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

        return  getWinningPositions().steam().
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
     * @param numberOfFirstPlayer
     */
}
