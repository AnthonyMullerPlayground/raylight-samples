package com.sap.webi.sample.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Dictionary {

	@XmlElement(name = "expression")
	public List<Expression> expressionList = new ArrayList<>();
	
	public List<Expression> getExpressions() {
		return expressionList;
	}
}
