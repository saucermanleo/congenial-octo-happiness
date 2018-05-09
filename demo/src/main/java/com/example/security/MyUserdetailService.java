package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyUserdetailService implements UserDetailsService , SocialUserDetailsService{
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String s =  passwordEncoder.encode("123456");
		User u = new User(username,s,AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
		return u;
	}
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		String s =  passwordEncoder.encode("123456");
		SocialUser u = new SocialUser(userId,s,AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
		return u;
	}

}
