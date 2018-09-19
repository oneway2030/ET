package com.xnhb.et.bean;

/**
 * 作者 oneway on 2018/9/19
 * 描述:
 * 参考链接:
 */
public class UserInfo {

    /**
     * id : 13
     * createTime : 2018-09-08 12:37:29
     * updateTime : 2018-09-19 16:06:38
     * remark : null
     * version : null
     * pwd : e10adc3949ba59abbe56e057f20f883e
     * phone : 15171446097
     * phoneStr : 151****6097
     * email : 2237586617@qq.com
     * emailStr : 2***7@qq.com
     * realName : null
     * realNameStr : null
     * idcart : null
     * bankName : 中国邮政储蓄银行
     * bankAddress : 1111111
     * bankNo : 11111111111111111
     * parentId : 0
     * treeLevel : 1
     * treeInfo : ,13,
     * status : 1
     * authStatus : 1
     * authStatusStr : 未认证
     * phoneAuth : 0
     * phoneAuthStr : 未认证
     * emailAuth : 0
     * emailAuthStr : 未认证
     * pwdStrength : 1
     * pwdStrengthStr : 弱
     * tradePassword : e10adc3949ba59abbe56e057f20f883e
     * leaderName : null
     * leaderRealName : null
     * special : 0
     * whiteIf : 0
     * statusStr : 已激活
     * specialStr : 否
     * whiteIfStr : 否
     * priority : 0
     * verifytypeLogin : 0
     * verifytypeTrade : 0
     * verifytypeWithdrawal : 0
     * lastloginIp : 171.113.154.50
     * lastloginCity : 武汉
     * robot : 0
     * robotStr : 否
     */

    private int id;
    private int authStatus; //1未认证  2认证中 3已认证  4认证失败\
    private String bankName;  //开户行
    private String bankAddress; //银行卡地址
    private String bankNo;  //银行卡号
    private String createTime;
    private String updateTime;
    private Object remark;
    private Object version;
    private String pwd;
    private String phone;
    private String phoneStr;
    private String email;
    private String emailStr;
    private Object realName;
    private Object realNameStr;
    private Object idcart;
    private int parentId;
    private int treeLevel;
    private String treeInfo;
    private int status;
    private String authStatusStr;
    private int phoneAuth;
    private String phoneAuthStr;
    private int emailAuth;
    private String emailAuthStr;
    private int pwdStrength;
    private String pwdStrengthStr;
    private String tradePassword;
    private Object leaderName;
    private Object leaderRealName;
    private int special;
    private int whiteIf;
    private String statusStr;
    private String specialStr;
    private String whiteIfStr;
    private int priority;
    private int verifytypeLogin;
    private int verifytypeTrade;
    private int verifytypeWithdrawal;
    private String lastloginIp;
    private String lastloginCity;
    private int robot;
    private String robotStr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public Object getVersion() {
        return version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneStr() {
        return phoneStr;
    }

    public void setPhoneStr(String phoneStr) {
        this.phoneStr = phoneStr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailStr() {
        return emailStr;
    }

    public void setEmailStr(String emailStr) {
        this.emailStr = emailStr;
    }

    public Object getRealName() {
        return realName;
    }

    public void setRealName(Object realName) {
        this.realName = realName;
    }

    public Object getRealNameStr() {
        return realNameStr;
    }

    public void setRealNameStr(Object realNameStr) {
        this.realNameStr = realNameStr;
    }

    public Object getIdcart() {
        return idcart;
    }

    public void setIdcart(Object idcart) {
        this.idcart = idcart;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(int treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getTreeInfo() {
        return treeInfo;
    }

    public void setTreeInfo(String treeInfo) {
        this.treeInfo = treeInfo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(int authStatus) {
        this.authStatus = authStatus;
    }

    public String getAuthStatusStr() {
        return authStatusStr;
    }

    public void setAuthStatusStr(String authStatusStr) {
        this.authStatusStr = authStatusStr;
    }

    public int getPhoneAuth() {
        return phoneAuth;
    }

    public void setPhoneAuth(int phoneAuth) {
        this.phoneAuth = phoneAuth;
    }

    public String getPhoneAuthStr() {
        return phoneAuthStr;
    }

    public void setPhoneAuthStr(String phoneAuthStr) {
        this.phoneAuthStr = phoneAuthStr;
    }

    public int getEmailAuth() {
        return emailAuth;
    }

    public void setEmailAuth(int emailAuth) {
        this.emailAuth = emailAuth;
    }

    public String getEmailAuthStr() {
        return emailAuthStr;
    }

    public void setEmailAuthStr(String emailAuthStr) {
        this.emailAuthStr = emailAuthStr;
    }

    public int getPwdStrength() {
        return pwdStrength;
    }

    public void setPwdStrength(int pwdStrength) {
        this.pwdStrength = pwdStrength;
    }

    public String getPwdStrengthStr() {
        return pwdStrengthStr;
    }

    public void setPwdStrengthStr(String pwdStrengthStr) {
        this.pwdStrengthStr = pwdStrengthStr;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public Object getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(Object leaderName) {
        this.leaderName = leaderName;
    }

    public Object getLeaderRealName() {
        return leaderRealName;
    }

    public void setLeaderRealName(Object leaderRealName) {
        this.leaderRealName = leaderRealName;
    }

    public int getSpecial() {
        return special;
    }

    public void setSpecial(int special) {
        this.special = special;
    }

    public int getWhiteIf() {
        return whiteIf;
    }

    public void setWhiteIf(int whiteIf) {
        this.whiteIf = whiteIf;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getSpecialStr() {
        return specialStr;
    }

    public void setSpecialStr(String specialStr) {
        this.specialStr = specialStr;
    }

    public String getWhiteIfStr() {
        return whiteIfStr;
    }

    public void setWhiteIfStr(String whiteIfStr) {
        this.whiteIfStr = whiteIfStr;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getVerifytypeLogin() {
        return verifytypeLogin;
    }

    public void setVerifytypeLogin(int verifytypeLogin) {
        this.verifytypeLogin = verifytypeLogin;
    }

    public int getVerifytypeTrade() {
        return verifytypeTrade;
    }

    public void setVerifytypeTrade(int verifytypeTrade) {
        this.verifytypeTrade = verifytypeTrade;
    }

    public int getVerifytypeWithdrawal() {
        return verifytypeWithdrawal;
    }

    public void setVerifytypeWithdrawal(int verifytypeWithdrawal) {
        this.verifytypeWithdrawal = verifytypeWithdrawal;
    }

    public String getLastloginIp() {
        return lastloginIp;
    }

    public void setLastloginIp(String lastloginIp) {
        this.lastloginIp = lastloginIp;
    }

    public String getLastloginCity() {
        return lastloginCity;
    }

    public void setLastloginCity(String lastloginCity) {
        this.lastloginCity = lastloginCity;
    }

    public int getRobot() {
        return robot;
    }

    public void setRobot(int robot) {
        this.robot = robot;
    }

    public String getRobotStr() {
        return robotStr;
    }

    public void setRobotStr(String robotStr) {
        this.robotStr = robotStr;
    }
}
