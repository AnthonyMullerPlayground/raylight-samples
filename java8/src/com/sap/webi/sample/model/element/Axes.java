package com.sap.webi.sample.model.element;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Axes {
	
	private String duplicateRowAggregation;
	
	@XmlElement(name = "axis")
	public List<Axis> axisList = new ArrayList<>();
	
	public List<Axis> getAxes() {
		return axisList;
	}

	@XmlAttribute(name = "duplicateRowAggregation")
	public String getDuplicateRowAggregation() {
		return duplicateRowAggregation;
	}

	public void setDuplicateRowAggregation(String duplicateRowAggregation) {
		this.duplicateRowAggregation = duplicateRowAggregation;
	}
}
