package com.webservice.tictactoe.domain;

import com.webservice.tictactoe.enums.GameStatus;
import com.webservice.tictactoe.enums.GameType;
import com.webservice.tictactoe.enums.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Check(constraints = "first_player_piece_code='O' or first_player_piece_code='X'" +
        "and game_type='COMPUTER' or game_type='COMPETITION' " +
        "and game_status='IN_PROGRESS' or game_status='FIRST_PLAYER_WON' or game_status='SECOND_PLAYER_WON'" +
        "or game_status='TIE' or game_status='WAITS_FOR_PLAYER' ")
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="second_player_id",nullable=true)
    private Player secondPlayer;

    @ManyToOne
    @JoinColumn(name = "first_player_id",nullable = false)
    private Player firstPlayer;

    @Enumerated(EnumType.STRING)
    private Piece firstPlayerPieceCode;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @Column(name = "created", nullable = false)
    private Date created;

}
