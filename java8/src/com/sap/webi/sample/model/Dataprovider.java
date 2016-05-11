package com.sap.webi.sample.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
/*
 * 
 * {
	"dataprovider": {
		"id": "DP1",
		"name": "Query 1",
		"dataSourceId": "5365",
		"dataSourcePrefix": "DS1",
		"dataSourceType": "unv",
		"updated": "2010-08-26T13:58:51.000Z",
		"duration": 1,
		"isPartial": "false",
		"rowCount": 456,
		"flowCount": 1,
		"dictionary": {
			"expression": [{
				"@dataType": "String",
				"@qualification": "Dimension",
				"id": "DP1.DOa6",
				"name": "City",
				"description": "City located.",
				"dataSourceObjectId": "DS1.DOa6",
				"formulaLanguageId": "[City]"
			}, {
				"@dataType": "Numeric",
				"@qualification": "Dimension",
				"id": "DP1.DOb9",
				"name": "Month",
				"description": "Month number in year, 1-12.",
				"dataSourceObjectId": "DS1.DOb9",
				"formulaLanguageId": "[Month]"
			}, {
				"@dataType": "String",
				"@qualification": "Dimension",
				"id": "DP1.DOda",
				"name": "State",
				"description": "State located.",
				"dataSourceObjectId": "DS1.DOda",
				"formulaLanguageId": "[State]"
			}, {
				"@dataType": "String",
				"@qualification": "Dimension",
				"id": "DP1.DO178",
				"name": "Store name",
				"description": "Name of store.",
				"dataSourceObjectId": "DS1.DO178",
				"formulaLanguageId": "[Store name]"
			}, {
				"@dataType": "String",
				"@qualification": "Dimension",
				"id": "DP1.DObc",
				"name": "Year",
				"description": "Year 1999 - 2001.",
				"dataSourceObjectId": "DS1.DObc",
				"formulaLanguageId": "[Year]"
			}, {
				"@dataType": "Numeric",
				"@qualification": "Measure",
				"@highPrecision": "false",
				"id": "DP1.DO92",
				"name": "Margin",
				"description": "Margin $ = Revenue - Cost of sales",
				"dataSourceObjectId": "DS1.DO92",
				"formulaLanguageId": "[Margin]",
				"aggregationFunction": "Sum"
			}, {
				"@dataType": "Numeric",
				"@qualification": "Measure",
				"@highPrecision": "false",
				"id": "DP1.DO94",
				"name": "Quantity sold",
				"description": "Quantity sold - number of SKU sold",
				"dataSourceObjectId": "DS1.DO94",
				"formulaLanguageId": "[Quantity sold]",
				"aggregationFunction": "Sum"
			}, {
				"@dataType": "Numeric",
				"@qualification": "Measure",
				"@highPrecision": "false",
				"id": "DP1.DO93",
				"name": "Sales revenue",
				"description": "Sales revenue $ - $ revenue of SKU sold",
				"dataSourceObjectId": "DS1.DO93",
				"formulaLanguageId": "[Sales revenue]",
				"aggregationFunction": "Sum"
			}]
		},
		"query": "SELECT Calendar_year_lookup.Yr, Calendar_year_lookup.Mth, Outlet_Lookup.State, Outlet_Lookup.City, Outlet_Lookup.Shop_name, sum(Shop_facts.Amount_sold), sum(Shop_facts.Quantity_sold), sum(Shop_facts.Margin) FROM  Calendar_year_lookup,  Outlet_Lookup,  Shop_facts WHERE  ( Outlet_Lookup.Shop_id=Shop_facts.Shop_id )  AND ( Shop_facts.Week_id=Calendar_year_lookup.Week_id ) GROUP BY Calendar_year_lookup.Yr, Calendar_year_lookup.Mth, Outlet_Lookup.State, Outlet_Lookup.City, Outlet_Lookup.Shop_name"
	}
}
 */
@XmlRootElement
public class Dataprovider {

	private String id;
	private String name;
	private Integer dataSourceId;
	private String dataSourcePrefix;
	private String dataSourceType;
	private Date updated;
	
	
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

	public Integer getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(Integer dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public String getDataSourcePrefix() {
		return dataSourcePrefix;
	}

	public void setDataSourcePrefix(String dataSourcePrefix) {
		this.dataSourcePrefix = dataSourcePrefix;
	}

	public String getDataSourceType() {
		return dataSourceType;
	}

	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}	
}
