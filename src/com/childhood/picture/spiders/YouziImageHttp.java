package com.childhood.picture.spiders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.childhood.picture.spiders.data.ImageData;
import com.childhood.picture.spiders.data.YouziCategory;
import com.childhood.picture.spiders.data.YouziDataConstant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class YouziImageHttp {

	public YouziImageHttp() {
		// TODO Auto-generated constructor stub
	}

	public static final String BASE_URL = "http://www.douban.com/photos/album/139226242/";
	public static final String URL1 = "小清新";

	static List<ImageData> category = new ArrayList<ImageData>();
	static List<String> albumPhotoUrl = new ArrayList<String>();
	static List<YouziCategory> youziCategory = new ArrayList<YouziCategory>();

	static final String albumjson = "["
			+ "{\"url\":\"5214\",\"totalPage\":\"11\"},"
			+ "{\"url\":\"5237\",\"totalPage\":\"11\"},"
			+ "{\"url\":\"5207\",\"totalPage\":\"15\"},"
			+ "{\"url\":\"5162\",\"totalPage\":\"9\"},"
			+ "{\"url\":\"5163\",\"totalPage\":\"9\"},"
			+ "{\"url\":\"5129\",\"totalPage\":\"9\"},"
			+ "{\"url\":\"5155\",\"totalPage\":\"14\"},"
			+ "{\"url\":\"5114\",\"totalPage\":\"17\"},"
			+ "{\"url\":\"5113\",\"totalPage\":\"13\"},"
			+ "{\"url\":\"5104\",\"totalPage\":\"9\"},"
			+ "{\"url\":\"5079\",\"totalPage\":\"10\"},"
			+ "{\"url\":\"5053\",\"totalPage\":\"10\"},"
			+ "{\"url\":\"4980\",\"totalPage\":\"10\"},"
			+ "{\"url\":\"4692\",\"totalPage\":\"15\"},"
			+ "{\"url\":\"5228\",\"totalPage\":\"10\"},"
			+ "{\"url\":\"5107\",\"totalPage\":\"15\"},"
			+ "{\"url\":\"5129\",\"totalPage\":\"9\"},"
			+ "{\"url\":\"5004\",\"totalPage\":\"6\"},"
			+ "{\"url\":\"5023\",\"totalPage\":\"9\"},"
			+ "{\"url\":\"4862\",\"totalPage\":\"9\"},"
			+ "{\"url\":\"5023\",\"totalPage\":\"9\"},"
			+ "{\"url\":\"5101\",\"totalPage\":\"13\"},"
			+ "{\"url\":\"5099\",\"totalPage\":\"9\"},"
			+ "{\"url\":\"5097\",\"totalPage\":\"9\"},"
			+ "{\"url\":\"5093\",\"totalPage\":\"9\"},"
			+ "{\"url\":\"5083\",\"totalPage\":\"10\"},"
			+ "{\"url\":\"5064\",\"totalPage\":\"12\"},"
			+ "{\"url\":\"5035\",\"totalPage\":\"9\"},"
			+ "{\"url\":\"5021\",\"totalPage\":\"10\"},"
			+ "{\"url\":\"5018\",\"totalPage\":\"7\"}" + "]";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println(executeHttpGet());

		// int i = 10000;
		// for (String url : categoryList) {
		// i++;
		// JsonToList(executeHttpGet(BASE_URL + url + "&s=1"));
		// JsonToList(executeHttpGet(BASE_URL + url + "&s=0"));
		//
		// Gson gson = new Gson();
		// // String encode = URLEncoder.encode(gson.toJson(list));
		// saveToFile(gson.toJson(list), "p" + i + ".data");
		// System.out.println(gson.toJson(list));
		// // System.out.println(URLDecoder.decode(encode));
		// System.out.println("list.size() = " + list.size());
		// list.clear();
		// }
		/*
		 * if (list.size() > 0) { for (Meinv m : list) { System.out.println(m);
		 * } }
		 */
		Gson gson = new Gson();

		category = gson.fromJson(YouziDataConstant.gaoqingmeinv,
				new TypeToken<List<ImageData>>() {
				}.getType());
		String fileName = "";
		String baseUrl = "http://www.youzi4.com/gaoqingmeinv/";
		String url = "";
		String desc = "";
		// category.clear();
		for (ImageData data : category) {
			for (int i = 1; i <= Integer.parseInt(data.totalPage); i++) {
				if (i == 1) {
					url = baseUrl + data.url + ".html";
					desc = executeHttpGet(url);
					youziCategory.add(new YouziCategory(albumPhotoUrl.get(0),
							data.url, desc, data.totalPage));
				} else {
					url = baseUrl + data.url + "_" + i + ".html";
					executeHttpGet(url);
				}

				fileName = data.url;
				System.out.println("fileName: " + fileName + ", Size: "
						+ albumPhotoUrl.size());

				url = "";
				System.out
						.println("----------------------------------------------------------------");
			}
			saveToFile(gson.toJson(albumPhotoUrl), fileName + ".data");
			albumPhotoUrl.clear();
		}

		saveToFile(gson.toJson(youziCategory), "home.data");
		// url = baseUrl + "5018.html";
		// desc = executeHttpGet(url);
		// System.out.print(desc);
	}

	static void saveToFile(String content, String fileName) {
		File file = new File("E:\\Android_Code\\tools\\youzi\\gaoqingmeinv\\"
				+ fileName);

		try {
			FileOutputStream out = new FileOutputStream(file);

			out.write(content.getBytes());

			out.close();
			System.out.println("写入文件：" + file.getAbsolutePath() + "成功。");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String executeHttpGet(String baseUrl) {
		String result = null;
		URL url = null;
		HttpURLConnection connection = null;
		InputStreamReader in = null;
		try {
			url = new URL(baseUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(60000);
			connection.setReadTimeout(60000);
			in = new InputStreamReader(connection.getInputStream(), "GB2312");
			BufferedReader bufferedReader = new BufferedReader(in);

			String line = null;
			boolean flag = false;
			while ((line = bufferedReader.readLine()) != null) {

				if (line.trim().contains("name=\"description\"")) {
					result = new String(line
							.trim()
							.substring(line.indexOf("content=\"") + 9,
									line.lastIndexOf("\"")).getBytes("GB2312"),
							"GB2312");
				}
				if (line.trim().contains("data-original")
						&& line.trim().contains("bigimg")) {
					line = line.substring(
							line.indexOf("data-original=\"") + 15,
							line.lastIndexOf(".jpg") + 4);
					albumPhotoUrl.add(line);
					// System.out.println("line: " + line);
					// strBuffer.append(line).append("\n");
				}

				// if (isSecondPage && line.trim().startsWith("<img src=")
				// && line.trim().endsWith("jpg\" />")) {
				// // System.out.println("line1: " + line);
				// line = line.substring(line.indexOf("h"),
				// line.lastIndexOf(".jpg") + 4);
				// albumPhotoImageUrl.add(line);
				// System.out.println("line: " + line);
				// break;
				// }
			}
			// strBuffer.append("}");
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
}
