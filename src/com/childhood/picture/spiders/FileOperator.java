package com.childhood.picture.spiders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * File name: FileTest.java 
 *
 * Version information: 1.0.0
 *
 * Date: 2014-3-31 上午10:31:01
 *
 * Copyright 2014 Autonavi Software Co. Ltd. All Rights Reserved.
 *
 */

/**
 * <p>
 * Title: FileTest.java
 * </p>
 * <p>
 * Description:（说明用中文）
 * </p>
 * 
 * @author: yi.kang
 * @date: 2014-3-31 上午10:31:01
 */
public class FileOperator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// writeFile();
		// System.out.println(UUID.randomUUID().toString());
		// System.out.println(256 << 20);

		// readFileByLine(new File("E:\\Android_Code\\汉语900句.txt"));
		// getHtmlImg(new File("E:\\Android_Code\\tools\\img.html"));

		getCategory(new File("E:\\Android_Code\\tools\\category.txt"));
	}

	static void writeFile() {
		File file = new File("D:\\kangyi.txt");
		String str = "TEST001";
		try {
			FileOutputStream out = new FileOutputStream(file);

			out.write(str.getBytes());

			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void fun1() {
		List<String> list = new ArrayList<String>();

		List<File> flist = new ArrayList<File>();
		File dir = new File("D:\\jieji_icon");
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			flist.add(files[i]);
			// System.out.println((files[i].isDirectory()?"目录：":"文件：")+files[i].getName());
			// System.out.println((files[i].isDirectory()?"目录：":"文件：")+files[i].getName());
			list.add(files[i].getName());
		}
		// Collections.sort(list);

		// Collections.sort(list);

		for (String s : list) {
			// System.out.println(s);
		}

		String filePath = "com.autonavi.minimap/autonavi/";
		System.out.println(filePath.substring(0, filePath.length() - 1));
	}

	/**
	 * Read file line by line
	 * 
	 * @author: Kang, Leo
	 * @date: 2014年7月31日 下午8:06:20
	 * @param file
	 */
	public static void readFileByLine(File file) {
		try {
			// read file content from file
			// StringBuffer sb = new StringBuffer("");
			String fileName = file.getAbsolutePath();
			FileReader reader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(reader);

			String str = null;

			int line = 0;

			String[] array = null;
			while ((str = br.readLine()) != null) {
				line++;
				System.out.println(str.substring(str.indexOf(" ") + 1));
				/*
				 * array = str.split("\\|"); if (array != null && array.length >
				 * 0) { System.out .println(
				 * "----------------------------------------------------------------------"
				 * ); for (String s : array) {
				 * System.out.println(s.trim().replaceAll(" \\.", ".")); } }
				 */
			}
			br.close();
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getHtmlImg(File file) {
		try {
			// read file content from file
			// StringBuffer sb = new StringBuffer("");
			String fileName = file.getAbsolutePath();
			FileReader reader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(reader);

			String str = null;

			int line = 0;

			String[] array = null;
			while ((str = br.readLine()) != null) {
				line++;
				if (str.startsWith("<img") && str.contains("data-photo="))
					System.out
							.println(str.substring(str.indexOf("data-photo=") + 1));
				/*
				 * array = str.split("\\|"); if (array != null && array.length >
				 * 0) { System.out .println(
				 * "----------------------------------------------------------------------"
				 * ); for (String s : array) {
				 * System.out.println(s.trim().replaceAll(" \\.", ".")); } }
				 */
			}
			br.close();
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getCategory(File file) {
		try {
			// read file content from file
			// StringBuffer sb = new StringBuffer("");
			String fileName = file.getAbsolutePath();
			FileReader reader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(reader);

			String str = null;

			int line = 0;

			String[] array = null;
			String temp = null;
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			while ((str = br.readLine()) != null) {
				line++;
				if (str.startsWith("<a") && str.contains("title=\"美女>")) {
					temp = str.substring(str.indexOf("title=\"美女>") + 10)
							.replaceAll("\"", "");
					temp = temp.substring(0, temp.indexOf("h"));
					// System.out.println("categoryList.add(\"" + temp.trim()
					// + "\");");

					System.out.println("\"" + temp.trim() + "\",");
				} else {
					continue;
				}

			}
			sb.append("}");

			System.out.println(sb.toString());
			br.close();
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
