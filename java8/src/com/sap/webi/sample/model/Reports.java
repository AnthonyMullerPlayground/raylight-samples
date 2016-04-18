package com.sap.webi.sample.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reports {

	@XmlElement(name = "report")
	public List<Report> reportList = new ArrayList<>();
}
