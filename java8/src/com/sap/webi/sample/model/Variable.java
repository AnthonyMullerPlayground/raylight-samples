package com.sap.webi.sample.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Variable {

	private String id;
	private String name;
	private String definition;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
}
