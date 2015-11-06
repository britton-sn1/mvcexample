package com.brittonn.mvcpract.mock;

import org.springframework.stereotype.Component;

import com.brittonn.hibpract.dietlog.UserDetailsDao;
import com.brittonn.hibpract.dietlog.beans.UserDetails;

@Component
public class MockUserDetailsDao implements UserDetailsDao {

	@Override
	public UserDetails getUserDetails(String userName) {
		UserDetails userDetails = null;
		if ("admin".equals(userName)) {
			userDetails = new UserDetails();
			userDetails.setUsername(userName);
			userDetails.setPwhash(
					"1000:68effc7a428978ded19aceb8c55023e2560a87272c8927df:c9eacaf4bbce7129f17aa25c3cde536b5eebb24892158af7");
			userDetails.setEmailaddr("a@b.c");
		}

		return userDetails;
	}

	@Override
	public void updateUserDetails(UserDetails userDetails) {
		// TODO include in juint test
		
	}

}
