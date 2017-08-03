/*
 * @(#)HeavyUserMessages.java $version 2014. 8. 7.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nhncorp.sns;

import java.util.ArrayList;
import java.util.List;

import com.nhncorp.sns.model.Post;

/**
 * @author taeshik.heo
 */
public class HeavyUserPostRepository {

	/**
	 * @return
	 */

	private static List<Post> postList;
	private static HeavyUserPostRepository instance;

	public static HeavyUserPostRepository getInstance() {
		if (instance == null) {
			instance = new HeavyUserPostRepository();
		}
		return instance;
	}

	private HeavyUserPostRepository() {
		postList = new ArrayList<Post>(); //file load 	

	}

	public static List<Post> getPosts() {
		return postList;
	}

	public void add(Post post) {
		postList.add(post);
	}

}
