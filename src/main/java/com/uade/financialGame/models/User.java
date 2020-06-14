package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.UserRequest;
import com.uade.financialGame.messages.responses.UserResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static com.uade.financialGame.models.User.UserRank.BEGGINER;

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
    private List<GameUser> games;

    public enum UserRank {
        BEGGINER,
        PROFESSIONAL,
        ECONOMIST
    }


    //BUILDERS
    public User(UserRequest userRequest) {
        this.userName = userRequest.getUserName();
        this.password = userRequest.getPassword();
        this.rank = userRequest.getRank() != null ? UserRank.valueOf(userRequest.getRank()) : BEGGINER;
        this.coins = (userRequest.getCoins() == null || userRequest.getCoins() == 0) ? 1000 : userRequest.getCoins();
    }

    public User() {
    }

    //METHODS
    public UserResponse toDto() {
        return new UserResponse(this);
    }

}
