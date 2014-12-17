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

import com.childhood.picture.spiders.data.Album;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DoubanHttp {

	public DoubanHttp() {
		// TODO Auto-generated constructor stub
	}

	public static final String BASE_URL = "http://www.douban.com/photos/album/139226242/";
	public static final String URL1 = "小清新";

	static List<Album> albumlst = new ArrayList<Album>();
	static List<String> albumPhotoUrl = new ArrayList<String>();
	static List<String> albumPhotoImageUrl = new ArrayList<String>();

	static final String albumjson = "["
			+ "{\"albumUrl\":\"http://www.douban.com/photos/album/139226635/\",\"albumName\":\"n_photo\"},"
			+ "{\"albumUrl\":\"http://www.douban.com/photos/album/139226581/\",\"albumName\":\"m_photo\"},"
			+ "{\"albumUrl\":\"http://www.douban.com/photos/album/139226542/\",\"albumName\":\"k_photo\"},"
			+ "{\"albumUrl\":\"http://www.douban.com/photos/album/139226467/\",\"albumName\":\"h_photo\"},"
			+ "{\"albumUrl\":\"http://www.douban.com/photos/album/139226363/\",\"albumName\":\"f_photo\"},"
			+ "{\"albumUrl\":\"http://www.douban.com/photos/album/139226299/\",\"albumName\":\"e_photo\"},"
			+ "{\"albumUrl\":\"http://www.douban.com/photos/album/139226242/\",\"albumName\":\"d_photo\"},"
			+ "{\"albumUrl\":\"http://www.douban.com/photos/album/139226225/\",\"albumName\":\"c_photo\"},"
			+ "{\"albumUrl\":\"http://www.douban.com/photos/album/139226134/\",\"albumName\":\"b_photo\"},"
			+ "{\"albumUrl\":\"http://www.douban.com/photos/album/139226061/\",\"albumName\":\"a_photo\"}"
			+ "]";

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

		albumlst = gson.fromJson(albumjson, new TypeToken<List<Album>>() {
		}.getType());
		String fileName = "";
		for (int i = 0; i < albumlst.size(); i++) {
			executeHttpGet(albumlst.get(i).albumUrl, false);
			fileName = albumlst.get(i).albumName;
			System.out.println("fileName: " + fileName + ", Size: "
					+ albumPhotoImageUrl.size());
			for (String url : albumPhotoUrl) {
				executeHttpGet(url, true);
			}
			saveToFile(gson.toJson(albumPhotoImageUrl), fileName + ".data");
			albumPhotoUrl.clear();
			albumPhotoImageUrl.clear();

			System.out
					.println("----------------------------------------------------------------");
		}
	}

	static void saveToFile(String content, String fileName) {
		File file = new File("E:\\Android_Code\\tools\\stratch\\" + fileName);

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

	public static String executeHttpGet(String baseUrl, boolean isSecondPage) {
		String result = null;
		URL url = null;
		HttpURLConnection connection = null;
		InputStreamReader in = null;
		try {
			url = new URL(baseUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(60000);
			connection.setReadTimeout(60000);
			in = new InputStreamReader(connection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(in);
			StringBuffer strBuffer = new StringBuffer();
			String line = null;
			boolean flag = false;
			while ((line = bufferedReader.readLine()) != null) {

				if (!isSecondPage && line.trim().contains("photolst_photo")) {
					line = line.substring(line.indexOf("\"") + 1,
							line.lastIndexOf("/"));
					albumPhotoUrl.add(line);
					// System.out.println("line: " + line);
					// strBuffer.append(line).append("\n");
				}

				if (isSecondPage && line.trim().startsWith("<img src=")
						&& line.trim().endsWith("jpg\" />")) {
					// System.out.println("line1: " + line);
					line = line.substring(line.indexOf("h"),
							line.lastIndexOf(".jpg") + 4);
					albumPhotoImageUrl.add(line);
					System.out.println("line: " + line);
					break;
				}
			}
			// strBuffer.append("}");
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
}
