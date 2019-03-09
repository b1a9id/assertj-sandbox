package com.example.assertjsandbox.model;

public class Address {
	private String prefecture;

	private String following;

	public Address(String prefecture, String following) {
		this.prefecture = prefecture;
		this.following = following;
	}

	public String getPrefecture() {
		return prefecture;
	}

	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}

	public String getFollowing() {
		return following;
	}

	public void setFollowing(String following) {
		this.following = following;
	}
}
