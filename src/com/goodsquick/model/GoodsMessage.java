package com.goodsquick.model;

import java.util.List;

public class GoodsMessage extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String sender;
	private long receiverId;
	private List<Long> targetUsers;
	private String messageType;
	private String messageTypeName;
	private String messageTitle;
	private String messageContent;
	/**
	 * 发件人物讯状态
	 * 0 删除  1有效
	 */
	private String statusSender;
	/**
	 * 收件人物讯状态
	 * 0 删除 1未读 2已读
	 */
	private String statusReceiver;
	
	private String createDateFull;
	private String updateDateFull;
	
	private String receiverNames;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessageTypeName() {
		return messageTypeName;
	}
	public void setMessageTypeName(String messageTypeName) {
		this.messageTypeName = messageTypeName;
	}
	public String getMessageTitle() {
		return messageTitle;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	public String getStatusSender() {
		return statusSender;
	}
	public void setStatusSender(String statusSender) {
		this.statusSender = statusSender;
	}
	public String getStatusReceiver() {
		return statusReceiver;
	}
	public void setStatusReceiver(String statusReceiver) {
		this.statusReceiver = statusReceiver;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public List<Long> getTargetUsers() {
		return targetUsers;
	}
	public void setTargetUsers(List<Long> targetUsers) {
		this.targetUsers = targetUsers;
	}
	public long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}
	public String getCreateDateFull() {
		return createDateFull;
	}
	public void setCreateDateFull(String createDateFull) {
		this.createDateFull = createDateFull;
	}
	public String getUpdateDateFull() {
		return updateDateFull;
	}
	public void setUpdateDateFull(String updateDateFull) {
		this.updateDateFull = updateDateFull;
	}
	public String getReceiverNames() {
		return receiverNames;
	}
	public void setReceiverNames(String receiverNames) {
		this.receiverNames = receiverNames;
	}
}
