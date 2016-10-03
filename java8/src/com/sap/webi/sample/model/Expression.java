package com.sap.webi.sample.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * "expression": [{
 *				"@dataType": "String",
 *				"@qualification": "Dimension",
 *				"id": "DP1.DOa6",
 *				"name": "City",
 *				"description": "City located.",
 *				"dataSourceObjectId": "DS1.DOa6",
 *				"formulaLanguageId": "[City]"
 *			}
 *			
 * @author Anthony MULLER
 */
public class Expression {

	private String id;
	private String name;
	private String description;
	private String dataSourceObjectId;
	private String formulaLanguageId;
	private String dataType;
	private String qualification;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDataSourceObjectId() {
		return dataSourceObjectId;
	}

	public void setDataSourceObjectId(String dataSourceObjectId) {
		this.dataSourceObjectId = dataSourceObjectId;
	}

	public String getFormulaLanguageId() {
		return formulaLanguageId;
	}

	public void setFormulaLanguageId(String formulaLanguageId) {
		this.formulaLanguageId = formulaLanguageId;
	}

	@XmlAttribute(name = "dataType")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@XmlAttribute(name = "qualification")	
	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
}
