package com.goodsquick.model;

import java.util.Date;

public class WebUserInfo {

    private int id;
    private String name;
    private String loginName;
    private String password;
    private String telephone;
    private String level;
    private String type;
    private String hasHouse;
    private String hasService;
    private int messageNum;
    private Date createdate;
    private Date updatedate;
    private Date lastLoginDate;
    
	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public Date getCreatedate() {
        return createdate;
    }
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    public Date getUpdatedate() {
        return updatedate;
    }
    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }
    public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHasHouse() {
		return hasHouse;
	}
	public void setHasHouse(String hasHouse) {
		this.hasHouse = hasHouse;
	}
	public String getHasService() {
		return hasService;
	}
	public void setHasService(String hasService) {
		this.hasService = hasService;
	}
	public int getMessageNum() {
		return messageNum;
	}
	public void setMessageNum(int messageNum) {
		this.messageNum = messageNum;
	}
}
