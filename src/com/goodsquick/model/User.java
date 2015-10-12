/*
 * Copyright (c) 2008-2014 VIP.COM, All rights reserved.
 */

package com.goodsquick.model;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;


public class User extends BaseObject {

	public static final User NULL = new User();

	private static final long serialVersionUID = 0xae2000000000180L;

	private Boolean active;

	private Date birthDay;

	private String bpcall;

	private Boolean canLogin;

	private Boolean canLoginweb;

	private Boolean canShowinweb;

	private String companyCode;

	private String contactAddress;

	private Date createDate;

	private String createdByUser;

	private Date createdDtmLoc;

	private String createdOffice;

	private String createdTimeZone;

	private String customerCode;

	private String defaultLang;

	private Boolean deleted;

	private String department;

	private String departmentId;

	private String education;

	private String email;

	private String fax;

	private Date fireDate;

	private String firstName;

	private Date hireDate;

	private String homeAddress;

	private String homeTelphone;

	private String idCard;

	private Boolean isCust;

	private String lastName;

	private String marital;

	private String middleName;

	private String mobile;

	private String nativeAddress;

	private String officeId;

	private String password;

	private String position;

	private String principalCode;

	private String principalGroupCode;

	private Long recordVersion;

	private String sex;

	private String superiorUserCode;

	private String superiorUserId;

	private String telephone1;

	private String telephone2;

	private String updatedByUser;

	private Date updatedDtmLoc;

	private String updatedOffice;

	private String updatedTimeZone;

	private String userCode;

	private Set userCompanys;

	private String userId;

	private String userName;

	private String userNameCn;

	private String userType;

	private String zipCode;

	public User() {
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof User)) {
			return false;
		}
		if (this == other) {
			return true;
		}
		User castOther = (User) other;
		boolean isNewEntity = true;
		isNewEntity = isNewEntity && (this.userId == null) && (castOther.userId == null);
		if (isNewEntity) {
			return super.equals(other);
		} else {
			return (new EqualsBuilder()).append(this.userId, castOther.userId).isEquals();
		}
	}

	public Boolean getActive() {
		return this.active;
	}

	public Date getBirthDay() {
		return this.birthDay;
	}

	public String getBpcall() {
		return this.bpcall;
	}

	public Boolean getCanLogin() {
		return this.canLogin;
	}

	public Boolean getCanLoginweb() {
		return this.canLoginweb;
	}

	public Boolean getCanShowinweb() {
		return this.canShowinweb;
	}

	public String getCompanyCode() {
		return this.companyCode;
	}

	public String getContactAddress() {
		return this.contactAddress;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	@Override
	public String getCreatedByUser() {
		return this.createdByUser;
	}

	@Override
	public Date getCreatedDtmLoc() {
		return this.createdDtmLoc;
	}

	@Override
	public String getCreatedOffice() {
		return this.createdOffice;
	}

	@Override
	public String getCreatedTimeZone() {
		return this.createdTimeZone;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public String getDefaultLang() {
		return this.defaultLang;
	}

	public Boolean getDeleted() {
		return this.deleted;
	}

	public String getDepartment() {
		return this.department;
	}

	public String getDepartmentId() {
		return this.departmentId;
	}

	public String getEducation() {
		return this.education;
	}

	public String getEmail() {
		return this.email;
	}

	public String getFax() {
		return this.fax;
	}

	public Date getFireDate() {
		return this.fireDate;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public Date getHireDate() {
		return this.hireDate;
	}

	public String getHomeAddress() {
		return this.homeAddress;
	}

	public String getHomeTelphone() {
		return this.homeTelphone;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public Boolean getIsCust() {
		return this.isCust;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getMarital() {
		return this.marital;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public String getNativeAddress() {
		return this.nativeAddress;
	}


	public String getPassword() {
		return this.password;
	}

	public String getPosition() {
		return this.position;
	}

	public String getPrincipalCode() {
		return this.principalCode;
	}

	@Override
	public String getPrincipalGroupCode() {
		return this.principalGroupCode;
	}

	@Override
	public Long getRecordVersion() {
		return this.recordVersion;
	}

	public String getSex() {
		return this.sex;
	}

	public String getSuperiorUserCode() {
		return this.superiorUserCode;
	}

	public String getSuperiorUserId() {
		return this.superiorUserId;
	}

	public String getTelephone1() {
		return this.telephone1;
	}

	public String getTelephone2() {
		return this.telephone2;
	}

	@Override
	public String getUpdatedByUser() {
		return this.updatedByUser;
	}

	@Override
	public Date getUpdatedDtmLoc() {
		return this.updatedDtmLoc;
	}

	@Override
	public String getUpdatedOffice() {
		return this.updatedOffice;
	}

	@Override
	public String getUpdatedTimeZone() {
		return this.updatedTimeZone;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public Set getUserCompanys() {
		return this.userCompanys;
	}

	public String getUserId() {
		return this.userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getUserNameCn() {
		return this.userNameCn;
	}

	public String getUserType() {
		return this.userType;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	@Override
	public int hashCode() {
		boolean isNewEntity = true;
		isNewEntity = isNewEntity && (this.userId == null);
		if (isNewEntity) {
			return super.hashCode();
		} else {
			int result = 17;
			result = (37 * result) + (this.userId != null ? 0xce2b2e46 : super.hashCode());
			return result;
		}
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public void setBpcall(String bpcall) {
		this.bpcall = bpcall;
	}

	public void setCanLogin(Boolean canLogin) {
		this.canLogin = canLogin;
	}

	public void setCanLoginweb(Boolean canLoginweb) {
		this.canLoginweb = canLoginweb;
	}

	public void setCanShowinweb(Boolean canShowinweb) {
		this.canShowinweb = canShowinweb;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}

	@Override
	public void setCreatedDtmLoc(Date createdDtmLoc) {
		this.createdDtmLoc = createdDtmLoc;
	}

	@Override
	public void setCreatedOffice(String createdOffice) {
		this.createdOffice = createdOffice;
	}

	@Override
	public void setCreatedTimeZone(String createdTimeZone) {
		this.createdTimeZone = createdTimeZone;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public void setDefaultLang(String defaultLang) {
		this.defaultLang = defaultLang;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setFireDate(Date fireDate) {
		this.fireDate = fireDate;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public void setHomeTelphone(String homeTelphone) {
		this.homeTelphone = homeTelphone;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setIsCust(Boolean isCust) {
		this.isCust = isCust;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMarital(String marital) {
		this.marital = marital;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setNativeAddress(String nativeAddress) {
		this.nativeAddress = nativeAddress;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setPrincipalCode(String principalCode) {
		this.principalCode = principalCode;
	}

	@Override
	public void setPrincipalGroupCode(String principalGroupCode) {
		this.principalGroupCode = principalGroupCode;
	}

	@Override
	public void setRecordVersion(Long recordVersion) {
		this.recordVersion = recordVersion;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setSuperiorUserCode(String superiorUserCode) {
		this.superiorUserCode = superiorUserCode;
	}

	public void setSuperiorUserId(String superiorUserId) {
		this.superiorUserId = superiorUserId;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	@Override
	public void setUpdatedByUser(String updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

	@Override
	public void setUpdatedDtmLoc(Date updatedDtmLoc) {
		this.updatedDtmLoc = updatedDtmLoc;
	}

	@Override
	public void setUpdatedOffice(String updatedOffice) {
		this.updatedOffice = updatedOffice;
	}

	@Override
	public void setUpdatedTimeZone(String updatedTimeZone) {
		this.updatedTimeZone = updatedTimeZone;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setUserCompanys(Set userCompanys) {
		this.userCompanys = userCompanys;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserNameCn(String userNameCn) {
		this.userNameCn = userNameCn;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
