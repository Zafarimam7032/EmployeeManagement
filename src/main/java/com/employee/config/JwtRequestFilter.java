package com.employee.config;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.hql.internal.ast.tree.DisplayableNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.employee.common.SecurityConstant;
import com.employee.exception.BussinessException;
import com.employee.service.impl.CustomUserDetailsService;
import com.employee.util.JwtUtil;



@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwtToken = null;
		String username = null;
		String jwtTokenWithBearor = request.getHeader(SecurityConstant.AUTHORIZATION);
		if (Objects.nonNull(jwtTokenWithBearor) && jwtTokenWithBearor.startsWith("Bearer ")) {
			jwtToken = jwtTokenWithBearor.substring(7);
			try {
				username = jwtUtil.getUsernameFromToken(jwtToken);
			} catch (Exception e) {
				throw new BussinessException("unable to find user from jwt token");
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						username, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		} else {
			logger.error("jwt token not found");
		}
		filterChain.doFilter(request, response);
		
	}

}
