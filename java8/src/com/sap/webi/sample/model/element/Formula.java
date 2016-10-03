package com.sap.webi.sample.model.element;

import javax.xml.bind.annotation.XmlAttribute;

public class Formula {

	private String dataType;
	private String dataObjectId;
	
	@XmlAttribute(name = "dataType")
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@XmlAttribute(name = "dataObjectId")
	public String getDataObjectId() {
		return dataObjectId;
	}
	public void setDataObjectId(String dataObjectId) {
		this.dataObjectId = dataObjectId;
	}
}
