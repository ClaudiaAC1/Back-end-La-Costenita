/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Security;

import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author claua
 */
@Component
public class JwtService {

    @Value("${app.jwt-secretKey}")
    private String SECRET_KEY;// = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    public String generateToken(String userName){
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*5)) //8 horas de acceso (System.currentTimeMillis()+1000*60*480)
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    
//    /**
//     * Metodo que validar el token 
//     *
//     * @param authentication
//     * @return  boolean
//     */  
//    public boolean validarToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true;
//        } catch (SignatureException ex) {
//            throw new BlogAppException(HttpStatus.UNAUTHORIZED, "Firma JWT no valida");
//        } catch (MalformedJwtException ex) {
//            throw new BlogAppException(HttpStatus.UNAUTHORIZED, "Token JWT no valida");
//        } catch (ExpiredJwtException ex) {
//            throw new BlogAppException(HttpStatus.UNAUTHORIZED, "Token JWT caducado");
//        } catch (UnsupportedJwtException ex) {
//            throw new BlogAppException(HttpStatus.UNAUTHORIZED, "Token JWT no compatible");
//        } catch (IllegalArgumentException ex) {
//            throw new BlogAppException(HttpStatus.UNAUTHORIZED, "La cadena claims JWT esta vacia");
//        }
//    }

    /**
     * Metodo que validar el token 
     *
     * @param token
     * @param authentication
     * @return  customResponse
     */
    public CustomResponse validarToken(String token) {
        CustomResponse customResponse = new CustomResponse();

        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            
            customResponse.setHttpCode(HttpStatus.OK.value()); // o HTTPS: 100 O 200
            customResponse.setData(true);
           
        } catch (SignatureException ex) {
            throw new BlogAppException(HttpStatus.UNAUTHORIZED, "Firma JWT no valida");
            
        } catch (MalformedJwtException ex) {
            throw new BlogAppException(HttpStatus.UNAUTHORIZED, "Firma JWT no valida");

        } catch (ExpiredJwtException ex) {
            throw new BlogAppException(HttpStatus.UNAUTHORIZED, "Token JWT caducado");

        } catch (UnsupportedJwtException ex) {
            throw new BlogAppException(HttpStatus.UNAUTHORIZED, "Token JWT caducado");
			
        } catch (IllegalArgumentException ex) {
            throw new BlogAppException(HttpStatus.UNAUTHORIZED, "La cadena claims JWT esta vacia");

        }

       return customResponse;
    }

}
