package com.deer.token;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.deer.model.UserEntity;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * JWT:Json web token
 * 三段信息组成; header(JWT头部信息) 、playload(JWT内容信息) 、signature 签证
 */
public class JWTUtil {

    public static String createTokenHMAC256(UserEntity userEntity){

        try{
            // 选择算法
            Algorithm algorithm = Algorithm.HMAC256(userEntity.getAccount());
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MINUTE, 30); // 目前時間加3小時
            String token = JWT.create()
                    .withSubject(userEntity.getName())          /*当前用户*/
                    .withAudience("web")            /*接受者*/
                    .withIssuer("system")            /*签发者*/
                    .withIssuedAt(new Date())       /*当前签发时间*/
                    .withExpiresAt(c.getTime())              /*过期时间 必须比当前签发时间大*/
                    .sign(algorithm);               /*签名*/
            return token;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        String key = "111";

        // 选择算法
        Algorithm algorithm = Algorithm.HMAC256(key);
        String token = JWT.create()
                .withSubject("user1")          /*当前用户*/
//                .withExpiresAt()              /*过期时间 必须比当前签发时间大*/
//                .withNotBefore()              /*在多久时间期之前不能使用*/
                .withAudience("web")            /*接受者*/
                .withIssuer("system")            /*签发者*/
                .withIssuedAt(new Date())       /*当前签发时间*/
                .withClaim("name","用户名")
                .sign(algorithm);               /*签名*/

        System.out.println(token);

        String token2 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImF1ZCI6IndlYiIsImlzcyI6InN5c3RlbSIsIm5hbWUiOiLnlKjmiLflkI0iLCJpYXQiOjE1MjEzNDU1NzZ9.bqUHAdH6dAt8B0BOpQbBJLxpDebTazQ9Z0AtYtgvkFE";


        //验证
        verifyToken(token2, key);

        //解密
//        DecodedJWT jwt = JWT.decode(token);
//        System.out.println();
    }



    public static Map<String, Claim> verifyToken(String token, String key){
        JWTVerifier verifier = null;
        try {
            verifier = JWT.require(Algorithm.HMAC256(key))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();
        return claims;
    }


}
