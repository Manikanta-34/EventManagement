package com.enfec.bookstatus.service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enfec.bookstatus.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtProvider {
	
	final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	@Autowired
	private User client;
	private PublicKey publicKey;
	private PrivateKey privateKey;

	@PostConstruct
	public void testJWTWithRsa() throws NoSuchAlgorithmException {
		

		KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
		keyGenerator.initialize(1024);

		KeyPair kp = keyGenerator.genKeyPair();
		publicKey = (PublicKey) kp.getPublic();
		privateKey = (PrivateKey) kp.getPrivate();

		String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
	//	String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
		System.out.println("Public Key:");
		System.out.println(convertToPublicKey(encodedPublicKey));
//		System.out.println("Private Key:");
//		System.out.println(convertToPrivateKey(encodedPrivateKey));

	}

	public String generateJwtToken(User pk) {
		
		Map<String, Object> claims = new HashMap<>();// newly added by me
		String token = Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 10))
				.setSubject(client.getPublicKey()).signWith(SignatureAlgorithm.RS256, getPrivateKey()).compact();
		return token;
	}

	private PrivateKey getPrivateKey() {
		try {
			return privateKey;
		} catch (Exception e) {
			return null;
		}
	}

	public Claims geClaimFromJWT(String authorization) {

		return Jwts.parser().setSigningKey(getPrivateKey()).parseClaimsJws(authorization).getBody();

		// return claims.getSubject();
	}

	// Add BEGIN and END comments
	private String convertToPublicKey(String key) {
		StringBuilder result = new StringBuilder();
		result.append("-----BEGIN PUBLIC KEY-----\n");
		result.append(key);
		result.append("\n-----END PUBLIC KEY-----");
		return result.toString();
	}

//	private String convertToPrivateKey(String encodedPrivateKey) {
//		StringBuilder result = new StringBuilder();
//		result.append("-----BEGIN PUBLIC KEY-----\n");
//		result.append(encodedPrivateKey);
//		result.append("\n-----END PUBLIC KEY-----");
//		return result.toString();
//	}

//	 private PublicKey getPublicKey() {
//    try {
//		return publicKey;
//    } catch (Exception e) {
//        return null;
//    }
//}
//
////Print structure of JWT
//public void printStructure(String token, PublicKey publicKey) {
//Jws parseClaimsJws = Jwts.parser().setSigningKey(publicKey)
//		.parseClaimsJws(token);
//
//System.out.println("Header     : " + parseClaimsJws.getHeader());
//System.out.println("Body       : " + parseClaimsJws.getBody());
//System.out.println("Signature  : " + parseClaimsJws.getSignature());
//}
}
