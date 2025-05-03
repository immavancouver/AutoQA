package org.andersen.lab.utils.links;

public enum Links {
	ANDERSEN_LOGIN("https://qa-course-01.andersenlab.com/login");

	private String link;

	public String getLink() {
		return link;
	}

	Links(String link) {
		this.link = link;
	}
}
