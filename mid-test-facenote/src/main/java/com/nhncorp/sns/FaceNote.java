/*
 * @(#)Post.java $version 2014. 8. 7.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nhncorp.sns;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import com.nhncorp.sns.model.Post;
import com.nhncorp.sns.model.User;

/**
 * @author taeshik.heo
 */
public class FaceNote {

	public static final int FRIENDS_COUNT_LIMIT = 10000;
	public static final String NEW_LINE = "\r\n";

	private File workDirectory = new File(System.getProperty("user.home"));

	public void setWorkDirectory(File workDirectory) {
		this.workDirectory = workDirectory;
	}

	public File getWorkDirectory() {
		return this.workDirectory;
	}

	/**
	 * 사용자아이디를 입력받아 해당사용자의 타임라인목록을 리턴.
	 * @param userId
	 * @return
	 */

	public Collection<Post> getTimeLineList(String userId) {

		ObjectMapper mapper = new ObjectMapper();
		List<Post> posts = new ArrayList<Post>();
		try {
			posts = mapper.readValue(new File(workDirectory + "\\" + userId + ".json"),
				TypeFactory.defaultInstance().constructCollectionType(List.class, Post.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ArrayList<Post> heavyPosts = (ArrayList<Post>)HeavyUserPostRepository.getInstance().getPosts();
		ArrayList<Post> result = new ArrayList<Post>();
		
		int i = 0, j = 0;
		
		while(i < heavyPosts.size() && j < posts.size()) {
			if(heavyPosts.get(i).getDate() < posts.get(j).getDate()) {
				result.add(heavyPosts.get(i++));
			}else {
				result.add(posts.get(j++));
			}
		}
		while(i < heavyPosts.size()) {
			result.add(heavyPosts.get(i++));
		}
		while(j < posts.size()) {
			result.add(posts.get(j++));
		}

		Collections.reverse(result);

		return result;
	}

	/**
	 * @param writer
	 * @param post
	 */
	public void writePost(User writer, Post post) {

		write(writer.getUserId(), post);
		
		if (writer.getFriends().size() >= FRIENDS_COUNT_LIMIT) {
			HeavyUserPostRepository.getInstance().add(post);
			return;
		}

		for (int i = 0; i < writer.getFriends().size(); i++) {
			write(writer.getFriends().get(i), post);
		}

	}

	public void write(String userId, Post post) {
		
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(workDirectory + "\\" + userId + ".json");
		List<Post> posts = new ArrayList<Post>();
		try {
			posts = mapper.readValue(new File(workDirectory + "\\" + userId + ".json"),
				TypeFactory.defaultInstance().constructCollectionType(List.class, Post.class));

		} catch (Exception e) {
			e.printStackTrace();
		}

		posts.add(post);

		try {
			mapper.writeValue(file, posts);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
