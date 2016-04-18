package com.sap.webi.sample.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Report {

	private Integer id;
	private String name;
	private String reference;
	private Boolean showDataChanges;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Boolean getShowDataChanges() {
		return showDataChanges;
	}
	public void setShowDataChanges(Boolean showDataChanges) {
		this.showDataChanges = showDataChanges;
	}	
}
