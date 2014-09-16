package com.childhood.picture.spiders.data;

public class AppInfo {

	public String appId;
	public String appName;
	public String appSize;
	public String appUrl;
	public String appDesc;

	public AppInfo(String appId, String appName, String appSize, String appUrl,
			String appDesc) {
		this.appId = appId;
		this.appName = appName;
		this.appSize = appSize;
		this.appUrl = appUrl;
		this.appDesc = appDesc;
	}

	@Override
	public String toString() {
		return "appId: " + appId + "appName: " + appName + ", appSize: "
				+ appSize + ", appUrl: " + appUrl + ", appDesc: " + appDesc;
	}
}
