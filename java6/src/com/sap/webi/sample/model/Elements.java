package com.sap.webi.sample.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Elements {

	@XmlElement(name = "element")
	public List<Element> elementList = new ArrayList<Element>();
}
