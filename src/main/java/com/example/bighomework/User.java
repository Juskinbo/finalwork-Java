package com.example.bighomework;

public class User {
    private int rank;
    private String id;
    private String account;
    private String password;
    private String securityQuestion1;
    private String securityQuestion2;
    private String securityAnswer1;
    private String securityAnswer2;
    private int score;

    public User() {
    }

    public User(String id, String account, String password, String securityQuestion1, String securityAnswer1, String securityQuestion2, String securityAnswer2, int score) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.securityQuestion1 = securityQuestion1;
        this.securityAnswer1 = securityAnswer1;
        this.securityQuestion2 = securityQuestion2;
        this.securityAnswer2 = securityAnswer2;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestion1() {
        return securityQuestion1;
    }

    public String getSecurityQuestion2() {
        return securityQuestion2;
    }

    public String getSecurityAnswer1() {
        return securityAnswer1;
    }

    public String getSecurityAnswer2() {
        return securityAnswer2;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return id + " " + account + " " + password + " " + securityQuestion1 + " " + securityAnswer1 + " " + securityQuestion2 + " " + securityAnswer2 + " " + score;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
