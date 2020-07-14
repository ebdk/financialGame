package com.uade.financialGame.models;

import com.uade.financialGame.messages.responses.GameMiniResponse;
import com.uade.financialGame.messages.responses.GameResponse;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.uade.financialGame.models.Game.GameLobbyStatus.*;

@Entity(name = "Game")
@Table(name = "game")
@Getter
public class Game {

    public static int GAME_FULL_NUMBER = 4;

    //ATTRIBUTES
    @Id
    @Column(name="GAME_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameId;
    private GameType gameType;
    private GameDifficulty gameDifficulty;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Company> companies;

	public GameMiniResponse toMiniDto() {
        return new GameMiniResponse(this);
	}

	public enum GameType {
        NORMAL
    }

    public enum GameDifficulty {
        EASY,
        MEDIUM,
        HARD
    }

    public enum GameLobbyStatus {
        EMPTY,
        FULL,
        AWAITING_PLAYERS
    }


    //BUILDERS
    public Game(GameType gameType, GameDifficulty gameDifficulty) {
        this.gameType = gameType;
        this.gameDifficulty = gameDifficulty;
        this.players = new ArrayList<>();
    }

    public Game() {
    }

    //METHODS
    public GameLobbyStatus getStatus(){
        return players.isEmpty() ? EMPTY :
                (players.size() >= GAME_FULL_NUMBER ? FULL : AWAITING_PLAYERS);
    }

    public int getGameSize(){
        return players.size();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public GameResponse toDto() {
        return new GameResponse(this);
    }

}
