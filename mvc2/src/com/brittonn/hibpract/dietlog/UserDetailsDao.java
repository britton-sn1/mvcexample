package com.brittonn.hibpract.dietlog;

import com.brittonn.hibpract.dietlog.beans.UserDetails;

public interface UserDetailsDao {
	
	UserDetails getUserDetails(String userName);

	void updateUserDetails(UserDetails userDetails); 
	
	void branchChange2();
}
