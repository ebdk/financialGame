package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.UserRequest;
import com.uade.financialGame.messages.responses.UserResponse;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "User")
@Table(name = "user")
@Getter
public class User {

    //ATTRIBUTES
    @Id
    @Column(name="USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String userName;
    private String password;
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
        this.rank = UserRank.valueOf(userRequest.getRank());
    }

    public User() {
    }

    //METHODS
    public UserResponse toDto() {
        return new UserResponse(this);
    }

}
