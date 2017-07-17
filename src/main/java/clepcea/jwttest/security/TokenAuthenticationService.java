package clepcea.jwttest.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {
	static final String SECRET = "secret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_PREFIX = "Authorization";
	
	public static void addAuthentication(HttpServletResponse res,String username){
		String JWT = Jwts.builder()
				.setSubject(username)
				.signWith(SignatureAlgorithm.HS256, SECRET).setIssuedAt(new Date())
				.compact();
		res.addHeader(HEADER_PREFIX, TOKEN_PREFIX+" "+JWT);		
	}
	
	public static Authentication getAuthentication(HttpServletRequest req){
		String token = req.getHeader(HEADER_PREFIX);
		
				
		if(token==null){
			return null;
		}
		String user = Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
				.getBody()
				.getSubject();
		if(user.equals("user")){
			return new UsernamePasswordAuthenticationToken(user,null, new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
		}else if(user.equals("admin")){			
			return new UsernamePasswordAuthenticationToken(user,null, new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
		} 
		return user!=null?
				new UsernamePasswordAuthenticationToken(user,null, (Collection<? extends GrantedAuthority>) new ArrayList<GrantedAuthority>()):
				null;
	}
	
}
