package com.childhood.picture.spiders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.childhood.picture.spiders.data.Meinv;
import com.google.gson.Gson;

public class SpidersHttp {

	public SpidersHttp() {
		// TODO Auto-generated constructor stub
	}

	// static ArrayList<Meinv> meinvList = new ArrayList<Meinv>();
	static HashSet<Meinv> meinvList = new HashSet<Meinv>();
	static ArrayList<Category> templist = new ArrayList<Category>();
	static List<String> categoryList = new ArrayList<String>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println(executeHttpGet());

		categoryList.add("小清新");
		categoryList.add("性感美女");
		categoryList.add("写真");
		categoryList.add("诱惑");
		categoryList.add("长腿");
		categoryList.add("甜素纯");
		categoryList.add("足球宝贝");
		categoryList.add("清纯");
		categoryList.add("校花");
		categoryList.add("网络美女");
		categoryList.add("唯美");
		categoryList.add("气质");
		categoryList.add("嫩萝莉");
		categoryList.add("时尚");
		categoryList.add("长发");
		categoryList.add("可爱");
		categoryList.add("车模");
		categoryList.add("古典美女");
		categoryList.add("素颜");
		categoryList.add("非主流");
		categoryList.add("短发");
		categoryList.add("高雅大气很有范");
		// categoryList.add("全部");
		int i = 10000;
		DownloadMeinvImage oInstance;
		String baseFilePath = "E:\\Android_Code\\tools\\meinv\\";
		// File folder = null;
		for (String category : categoryList) {

			i++;
			for (int startIndex = 0; startIndex < 181; startIndex += 60) {
				JsonToList(executeHttpGet(getUrl(category, startIndex)));
			}

			// oInstance = new DownloadMeinvImage();

			// for (int k = 0; k < list.size(); k++) {
			/*
			 * folder = new File(list.get(k).url, baseFilePath + category); if
			 * (!folder.isDirectory()) { folder.mkdir(); }
			 */
			// oInstance.addItem(list.get(k).url, baseFilePath + category
			// + "\\pic_" + i + "_" + k + ".jpg");
			// }
			// oInstance.downLoadByList();

			Gson gson = new Gson();
			// saveToFile(gson.toJson(templist), "p11" + i + ".data");
			//
			// String encode = URLEncoder.encode(gson.toJson(list));

			// for (Meinv m : meinvList) {
			// if (!noRepeatMeinvList.contains(m)) {
			// noRepeatMeinvList.add(m);
			// }
			// }
			// Set<Meinv> set = new HashSet<Meinv>(meinvList);
			// meinvList.clear();
			// meinvList.addAll(set);
			System.out.println("meinvList.size() = " + meinvList.size());
			saveToFile(gson.toJson(meinvList), "p" + i + ".data");
			System.out.println(gson.toJson(meinvList));
			// System.out.println(URLDecoder.decode(encode));
			// System.out.println("list.size() = " + list.size());
			meinvList.clear();
		}

		// Gson gson = new Gson();
		// saveToFile(gson.toJson(templist), "p11" + i + ".data");
		/*
		 * if (list.size() > 0) { for (Meinv m : list) { System.out.println(m);
		 * } }
		 */
	}

	/**
	 * Save json data to local file
	 * 
	 * @param content
	 * @param fileName
	 */
	static void saveToFile(String content, String fileName) {
		File file = new File("E:\\Android_Code\\tools\\meinv\\" + fileName);

		try {
			FileOutputStream out = new FileOutputStream(file);

			out.write(content.getBytes());

			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Json data paser to meinv List
	 * 
	 * @param str
	 */
	static void JsonToList(String str) {
		try {

			long startTime = System.currentTimeMillis();
			JSONObject jo = new JSONObject(str);
			JSONArray array = jo.getJSONArray("imgs");
			System.out.println("JSONArray.lenght()= " + array.length());
			JSONObject jb = null;
			String desc = "";
			for (int i = 0; i < array.length(); i++) {
				jb = new JSONObject(array.get(i).toString());
				if (jb.has("desc") && jb.has("imageUrl")) {
					desc = jb.getString("desc");
					if (desc.length() > 10) {
						desc = desc.substring(0, 10);
					}
					Meinv m = new Meinv(jb.getString("imageUrl").trim(), desc);
					meinvList.add(m);

					// Iterator<Meinv> it = meinvList.iterator();
					// while (it.hasNext()) {
					// if (it.hashCode() != m.hashCode()) {
					// meinvList.add(m);
					// } else {
					// continue;
					// }
					// }
					// templist.add(m);
				}
			}
			System.out.println("Json解析时间： "
					+ (System.currentTimeMillis() - startTime));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Http get request
	 * 
	 * @param baseUrl
	 * @return
	 */
	public static String executeHttpGet(String baseUrl) {
		String result = null;
		URL url = null;
		HttpURLConnection connection = null;
		InputStreamReader in = null;
		try {
			url = new URL(baseUrl);
			connection = (HttpURLConnection) url.openConnection();
			in = new InputStreamReader(connection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(in);
			StringBuffer strBuffer = new StringBuffer();
			String line = null;
			boolean flag = false;
			while ((line = bufferedReader.readLine()) != null) {
				strBuffer.append(line.trim());
			}
			result = strBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return result;
	}

	/**
	 * 构造请求url及参数
	 * 
	 * @param tag
	 * @param startIndex
	 * @return
	 */
	static String getUrl(String tag, int startIndex) {
		String url = "http://image.baidu.com/data/imgs?col=美女&tag=" + tag
				+ "&sort=0&tag3=&pn=" + startIndex + "&rn=60&p=channel&from=1";
		System.out.println("Request url： " + url);
		return url;
	}

}
