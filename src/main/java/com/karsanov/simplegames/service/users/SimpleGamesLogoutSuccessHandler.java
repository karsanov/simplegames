package com.karsanov.simplegames.service.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class SimpleGamesLogoutSuccessHandler implements LogoutSuccessHandler {

	@Autowired
	private UserPoolService userPoolService;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		userPoolService.deleteUserFromPool(authentication.getName());
		
		response.sendRedirect(request.getContextPath());
	}

}
