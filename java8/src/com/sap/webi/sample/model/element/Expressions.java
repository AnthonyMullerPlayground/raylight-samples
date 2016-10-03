package com.sap.webi.sample.model.element;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Expressions {

	@XmlElement(name = "formula")
	public List<Formula> formulaList = new ArrayList<>();
	
	public List<Formula> getFormulas() {
		return formulaList;
	}
}
