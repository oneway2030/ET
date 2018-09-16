package com.xnhb.et.bean;

/**
 * @author oneway
 * @describe
 * @since 2018/9/15 0015
 */


public class UserInfo {


    /**
     * verifytypeWithdrawal : 0
     * verifytypeLogin : 0
     * phone : 15171446097
     * verifytypeTrade : 0
     * token : f37d4ca8fbac880cc4ae62dc3bafdb76
     * status : 1
     */

    private int verifytypeWithdrawal;
    private int verifytypeLogin;
    private String phone;
    private int verifytypeTrade;
    private String token;
    private int status;

    public int getVerifytypeWithdrawal() {
        return verifytypeWithdrawal;
    }

    public void setVerifytypeWithdrawal(int verifytypeWithdrawal) {
        this.verifytypeWithdrawal = verifytypeWithdrawal;
    }

    public int getVerifytypeLogin() {
        return verifytypeLogin;
    }

    public void setVerifytypeLogin(int verifytypeLogin) {
        this.verifytypeLogin = verifytypeLogin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getVerifytypeTrade() {
        return verifytypeTrade;
    }

    public void setVerifytypeTrade(int verifytypeTrade) {
        this.verifytypeTrade = verifytypeTrade;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
