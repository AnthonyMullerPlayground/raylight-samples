package com.sap.webi.sample.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class About {

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
