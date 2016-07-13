package com.sap.webi.sample.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class About {

	private String title;
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
