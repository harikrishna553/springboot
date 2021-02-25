package com.sample.app.service;

import com.sample.app.exception.BaseException;

public class ETagService {

	public static String getETag(Object obj) throws BaseException {
		return "\"" +  obj.hashCode()  +"\"";
	
	}

}
