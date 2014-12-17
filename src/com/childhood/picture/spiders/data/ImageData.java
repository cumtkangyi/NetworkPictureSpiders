package com.childhood.picture.spiders.data;

import java.io.Serializable;

public class ImageData implements Serializable {
	public String url;
	public String totalPage;

	public ImageData(String url, String totalPage) {
		this.url = url;
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		return "url: " + url + " , totalPage: " + totalPage;
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
		if (this.url.equals(other.url)) {
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
