/*
 * @(#)Post.java $version 2014. 8. 8.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nhncorp.sns.model;

import java.util.Arrays;

/**
 * @author taeshik.heo
 */
public class Post {
	public static String FIELD_SEPERATOR = "~";

	String[] message;
	long date;

	public Post() {
		this.message = new String[1];
		message[0] = "hello";
		date = System.currentTimeMillis();
	}

	public Post(String[] message) {
		this.message = message;
		date = System.currentTimeMillis();

	}
	
	public Post(String param) {
		this.message = param.split(FIELD_SEPERATOR);
		date = System.currentTimeMillis();
	}

	public String[] getMessage() {
		return message;
	}

	public void setMessage(String[] message) {
		this.message = message;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	@Override
	public String toString() {
		
		String result = "";
		for(int i = 0; i< message.length; i++) {
			result += message[i];
		}
		return result;
	}


}