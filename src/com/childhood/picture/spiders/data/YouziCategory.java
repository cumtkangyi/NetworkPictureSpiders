package com.childhood.picture.spiders.data;

import java.io.Serializable;

public class YouziCategory implements Serializable {
	public String url;
	public String name;
	public String totalPage;
	public String desc;

	public YouziCategory(String url, String name, String desc, String totalPage) {
		this.url = url;
		this.totalPage = totalPage;
		this.name = name;
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "url: " + url + " , name: " + name + " , desc: " + desc
				+ " , totalPage: " + totalPage;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		YouziCategory other = (YouziCategory) obj;
		if (this.name.equals(other.name)) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.name.hashCode();
	}
}
