package com.beyond.util.annotation;

public class UserController {

    @AutoWired
    private UserService userService ;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
