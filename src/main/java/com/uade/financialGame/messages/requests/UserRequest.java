package com.uade.financialGame.messages.requests;

@lombok.Getter
public class UserRequest implements com.uade.financialGame.messages.Response {

    //ATTRIBUTES
    private String userName;
    private String password;
    private String rank;

    //BUILDERS
    public UserRequest(com.uade.financialGame.models.User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.rank = user.getRank().toString();
    }

    public UserRequest() {
    }

}
