package com.childhood.picture.spiders.data;

public class Meinv {
	public String url;
	public String des;

	public Meinv(String url, String des) {
		this.url = url;
		this.des = des;
	}

	@Override
	public String toString() {
		return "url: " + url + "\ndes: " + des;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Meinv other = (Meinv) obj;
		if (this.url == other.url) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.url.hashCode();
	}

}