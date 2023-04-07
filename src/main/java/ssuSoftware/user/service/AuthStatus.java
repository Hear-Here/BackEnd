package ssuSoftware.user.service;

public enum AuthStatus {
    OAUTH_LOGIN("login"), OAUTH_JOIN("join");

    private String type;

    AuthStatus(String type){this.type = type;}

    public String getType(){return type;}

}
