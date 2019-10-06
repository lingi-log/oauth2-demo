package com.oauth2resourceserver.base.util;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LogTailUtil {
	public HashMap<String, Object> runTail(File file, long filePointer) throws Exception {
		HashMap<String, Object> rtMap = new HashMap<String, Object>();

		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "r");
			if (filePointer <= 0 && raf.length() > 2048) {
				filePointer = raf.length() - 2048;
			}
			raf.seek(filePointer);

			StringBuffer sb = new StringBuffer();
			String sLine;
			List<String> l = new ArrayList<>();
			while ((sLine = raf.readLine()) != null) {
				l.add(sLine);
				// sb.append("<li>").append(sLine).append("</li>");
			}
			filePointer = raf.getFilePointer();

			rtMap.put("lines", l);
			rtMap.put("filePointer", filePointer);
			return rtMap;
		} catch (Exception e) {
			throw e;
		} finally {
			if (raf != null) try { raf.close(); } catch (Exception e) { }
		}
	}
}