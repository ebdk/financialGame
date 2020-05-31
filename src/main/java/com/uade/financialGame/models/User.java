package com.uade.financialGame.models;

import com.uade.financialGame.messages.UserDto;
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
    public User(UserDto userDto) {
        this.userId = userDto.getUserId();
        this.userName = userDto.getUserName();
        this.password = userDto.getPassword();
        this.rank = UserRank.valueOf(userDto.getRank());
    }

    public User() {
    }

    //METHODS
    public UserDto toDto() {
        return new UserDto(this);
    }

}
