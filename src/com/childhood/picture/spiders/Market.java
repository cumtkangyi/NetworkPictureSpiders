package com.childhood.picture.spiders;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.childhood.picture.spiders.data.AppInfo;
import com.google.gson.Gson;

public class Market {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String baseId = "appId_";
		List<AppInfo> list = new ArrayList<AppInfo>();
		list.add(new AppInfo(baseId + 1, "美女秀秀", "2.7 MB", "http://kangyi.com",
				"给你不一样的感觉！天天都有好心情。"));
		list.add(new AppInfo(baseId + 2, "美女刮刮", "2.7 MB", "http://kangyi.com",
				"给你不一样的感觉！天天都有好心情。"));
		list.add(new AppInfo(baseId + 3, "街机游戏", "2.7 MB", "http://kangyi.com",
				"给你不一样的感觉！天天都有好心情。"));
		list.add(new AppInfo(baseId + 4, "大学英语四级词汇", "2.7 MB",
				"http://kangyi.com", "给你不一样的感觉！天天都有好心情。"));
		list.add(new AppInfo(baseId + 5, "大学英语六级词汇", "2.7 MB",
				"http://kangyi.com", "给你不一样的感觉！天天都有好心情。"));
		Gson gson = new Gson();
		System.out.println(gson.toJson(list));
		saveToFile(gson.toJson(list), "market.json");
	}

	static void saveToFile(String content, String fileName) {
		File file = new File("E:\\Android_Code\\tools\\market\\" + fileName);

		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}

		try {
			FileOutputStream out = new FileOutputStream(file);

			out.write(content.getBytes());

			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
