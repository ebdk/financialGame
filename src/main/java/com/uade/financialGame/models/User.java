package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.UserRequest;
import com.uade.financialGame.messages.responses.UserResponse;
import com.uade.financialGame.models.Player.PlayerType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static com.uade.financialGame.models.User.UserRank.BEGGINER;
import static com.uade.financialGame.utils.StringUtils.generateRandomString;

@Entity(name = "User")
@Table(name = "user")
@Getter
@Setter
public class User {

    //ATTRIBUTES
    @Id
    @Column(name="USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String userName;
    private String password;
    private Integer coins;
    private UserRank rank;

    @OneToMany(mappedBy = "user")
    private List<Player> games;

    public enum UserRank {
        BEGGINER,
        PROFESSIONAL,
        ECONOMIST,
        CPU
    }


    //BUILDERS
    public User(UserRequest userRequest) {
        this.userName = userRequest.getUserName();
        this.password = userRequest.getPassword();
        this.rank = userRequest.getRank() != null ? UserRank.valueOf(userRequest.getRank()) : BEGGINER;
        this.coins = (userRequest.getCoins() == null || userRequest.getCoins() == 0) ? 1000 : userRequest.getCoins();
    }

    public User(PlayerType playerType) {
        if(com.uade.financialGame.models.Player.PlayerType.CPU.equals(playerType)) {
            //DEFAULT BOT CREATOR
            this.userId = 0L;
            this.userName = "bot_user";
            this.coins = 2147483;
            this.rank = UserRank.CPU;
            this.password = generateRandomString();
        } else {
            new User();
        }
    }

    public User() {
    }

    //METHODS
    public UserResponse toDto() {
        return new UserResponse(this);
    }

}
