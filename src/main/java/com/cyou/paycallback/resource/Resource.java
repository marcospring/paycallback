package com.cyou.paycallback.resource;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface Resource {
	public List<File> getFile();

	public InputStream getInputStream();

	public boolean exists();

}
