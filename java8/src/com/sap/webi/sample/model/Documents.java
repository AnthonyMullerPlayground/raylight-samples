package com.sap.webi.sample.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Documents {

	@XmlElement(name = "document")
	public List<Document> documentList = new ArrayList<>();
}
