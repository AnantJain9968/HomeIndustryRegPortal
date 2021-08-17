package com.nagarro.HomeIndustryReg.dto;


/**
 * experience documents information class
 * 
 * @author Anantjain
 *
 */
public class ExperienceDocInfo{
	
	String docName;
	long docId;
	/**
	 * @param docName
	 * @param docId
	 */
	public ExperienceDocInfo(String docName, long docId) {
		super();
		this.docName = docName;
		this.docId = docId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public long getDocId() {
		return docId;
	}
	public void setDocId(long docId) {
		this.docId = docId;
	}
	
	
	
}