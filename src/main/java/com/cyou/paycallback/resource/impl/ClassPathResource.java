package com.cyou.paycallback.resource.impl;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.cyou.paycallback.resource.Resource;
import com.cyou.paycallback.util.StringUtils;

public class ClassPathResource implements Resource {

	private String classPath;
	private File classFile;

	public ClassPathResource() {
		// TODO Auto-generated constructor stub
	}

	public ClassPathResource(String classPath) {
		this.classPath = classPath;
	}

	public List<File> getFile() {
		if (StringUtils.isEmpty(classPath))
			throw new NullPointerException("classPath is null");
		classPath = classPath.substring(classPath.indexOf(":") + 1);
		List<File> result = new ArrayList<File>();
		String[] paths = classPath.split(",");
		for (String path : paths) {
			String realPath = Thread.currentThread().getContextClassLoader().getResource(path).getPath();
			classFile = new File(realPath);
			getFiles(classFile, result);
		}
		// String[] paths = classPath.split(",");
		// FileInputStream fileInputStream = null;
		// FileChannel outChannel = null;
		// try {
		// for (int i = 0; i < paths.length; i++) {
		// String path =
		// Thread.currentThread().getContextClassLoader().getResource(paths[i]).getPath();
		// File file = new File(path);
		// if (i == 0) {
		// classFile = new File(path);
		// outChannel = new FileOutputStream(classFile).getChannel();
		// } else {
		// fileInputStream = new FileInputStream(file);
		// FileChannel fc = fileInputStream.getChannel();
		// ByteBuffer bb = ByteBuffer.allocate(BUFFIZE);
		// while (fc.read(bb) != -1) {
		// bb.flip();
		// outChannel.write(bb);
		// bb.clear();
		// }
		// fc.close();
		// }
		// }
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } finally {
		// try {
		// if (outChannel != null) {
		// outChannel.close();
		// }
		// } catch (IOException ignore) {
		// }
		// }
		return result;
	}

	private List<File> getFiles(File file, List<File> fileList) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File f : files) {
					getFiles(f, fileList);
				}
			}
		} else {
			fileList.add(file);
		}
		return fileList;
	}

	@Override
	public InputStream getInputStream() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	@Override
	public boolean exists() {
		return classFile == null ? false : classFile.exists();
	}

}
