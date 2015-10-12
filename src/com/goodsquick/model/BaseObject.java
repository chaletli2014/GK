/*
 * Copyright (c) 2008-2014 VIP.COM, All rights reserved.
 */

package com.goodsquick.model;

import java.io.Serializable;
import java.util.Date;

public class BaseObject implements Serializable {
	private static final long serialVersionUID = 3977859557769162803L;
	protected static final String NULL2STRINGEMPTY = "";
	protected static final Long NULL2LONGZERO = new Long(0L);
	protected static final Integer NULL2INTEGERZERO = new Integer(0);
	protected static final Float NULL2FLOATZERO = new Float(0.0D);
	protected static final boolean NULL2FALSE = false;
	public static final int ROWSTATUS_DETACHED = 1;
	public static final int ROWSTATUS_UNCHANGED = 2;
	public static final int ROWSTATUS_ADDED = 4;
	public static final int ROWSTATUS_DELETED = 8;
	public static final int ROWSTATUS_MODIFIED = 16;
	public static final int ROWSTATUS_LOADING = 32;
	private String createdByUser;
	private String createdOffice;
	private Date createdDtmLoc;
	private String createdTimeZone;
	private String updatedByUser;
	private String updatedOffice;
	private Date updatedDtmLoc;
	private String updatedTimeZone;
	private Long recordVersion;
	private int rowStatus = 2;
	private int extRowStatus = 0;
	private String principalGroupCode;
	private boolean logged = false;
	private boolean isDeleted = false;

	public void setRowStatus(int paramInt) {
		this.rowStatus = paramInt;
	}

	public int getRowStatus() {
		return this.rowStatus;
	}

	public String getCreatedByUser() {
		return this.createdByUser;
	}

	public void setCreatedByUser(String paramString) {
		this.createdByUser = paramString;
	}

	public Date getCreatedDtmLoc() {
		return this.createdDtmLoc;
	}

	public void setCreatedDtmLoc(Date paramDate) {
		this.createdDtmLoc = paramDate;
	}

	public String getCreatedOffice() {
		return this.createdOffice;
	}

	public void setCreatedOffice(String paramString) {
		this.createdOffice = paramString;
	}

	public String getCreatedTimeZone() {
		return this.createdTimeZone;
	}

	public void setCreatedTimeZone(String paramString) {
		this.createdTimeZone = paramString;
	}

	public String getUpdatedOffice() {
		return this.updatedOffice;
	}

	public void setUpdatedOffice(String paramString) {
		this.updatedOffice = paramString;
	}

	public String getUpdatedByUser() {
		return this.updatedByUser;
	}

	public void setUpdatedByUser(String paramString) {
		this.updatedByUser = paramString;
	}

	public Date getUpdatedDtmLoc() {
		return this.updatedDtmLoc;
	}

	public void setUpdatedDtmLoc(Date paramDate) {
		this.updatedDtmLoc = paramDate;
	}

	public String getUpdatedTimeZone() {
		return this.updatedTimeZone;
	}

	public void setUpdatedTimeZone(String paramString) {
		this.updatedTimeZone = paramString;
	}

	public Long getRecordVersion() {
		return this.recordVersion;
	}

	public void setRecordVersion(Long paramLong) {
		this.recordVersion = paramLong;
	}


	public boolean isDirty() {
		return (this.rowStatus == 4) || (this.rowStatus == 8) || (this.rowStatus == 16);
	}

	public void setModifyStatus() {
		if ((getRowStatus() == 1) || (getRowStatus() == 4))
			setRowStatus(4);
		else
			setRowStatus(16);
	}

	public String getPrincipalGroupCode() {
		return this.principalGroupCode;
	}

	public void setPrincipalGroupCode(String paramString) {
		this.principalGroupCode = paramString;
	}

	public boolean isLogged() {
		return this.logged;
	}

	public void setLogged(boolean paramBoolean) {
		this.logged = paramBoolean;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

    
    public int getExtRowStatus() {
        return extRowStatus;
    }

    
    public void setExtRowStatus(int extRowStatus) {
        this.extRowStatus = extRowStatus;
    }
	
}

