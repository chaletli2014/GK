package com.goodsquick.model;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class GoodsHouseFile extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6554770656464724111L;

	private int id;
	
	/**
	 * 物库编码
	 */
	private String repositoryCode;

	/**
	 * 文件类型
	 */
	private String fileType;
	
	/**
	 * 文件名称
	 */
	private String fileName;
	
	/**
	 * 文件路径
	 */
	private String filePath;
	
	/**
	 * 是否封面
	 */
	private String isMain;
	
	private String status;
	
	private MultipartFile sourceFile;
	
	private File targetFile;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRepositoryCode() {
		return repositoryCode;
	}
	public void setRepositoryCode(String repositoryCode) {
		this.repositoryCode = repositoryCode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getIsMain() {
		return isMain;
	}
	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public MultipartFile getSourceFile() {
		return sourceFile;
	}
	public void setSourceFile(MultipartFile sourceFile) {
		this.sourceFile = sourceFile;
	}
	public File getTargetFile() {
		return targetFile;
	}
	public void setTargetFile(File targetFile) {
		this.targetFile = targetFile;
	}
	
}
