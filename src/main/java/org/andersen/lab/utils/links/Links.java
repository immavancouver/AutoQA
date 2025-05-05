package org.andersen.lab.utils.links;

public enum Links {

	ANDERSEN_LOGIN("https://qa-course-01.andersenlab.com/login"),
	ANDERSEN_REGISTRATION("https://qa-course-01.andersenlab.com/registration");

	private String link;

	public String getLink() {
		return link;
	}

	Links(String link) {
		this.link = link;
	}
}
