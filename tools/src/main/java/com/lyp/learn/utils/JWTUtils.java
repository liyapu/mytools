package com.lyp.learn.utils;

import com.lyp.learn.bean.RequestField;
import com.lyp.learn.bean.UniToken;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class JWTUtils {
    private static Logger logger = LoggerFactory.getLogger(JWTUtils.class);
    private static final String JWT_SECERT = "control-secert-key";

    /**
     * 生成token并写入到redis
     *
     * @param uniToken
     * @param expireTime
     * @return
     */
    public static String createToken(UniToken uniToken, long expireTime) {
        // 密钥生成
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey(JWT_SECERT);
        // 时间生成
        long nowTimeMillis = System.currentTimeMillis();
        Date nowDate = new Date(nowTimeMillis);
        // 唯一标识生成
        String id = UUID.randomUUID().toString();
        // jwt生成
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .claim(RequestField.USERID, uniToken.getUserId())
                .claim(RequestField.USER_ID_SHOW,uniToken.getUserIdShow())
                .claim(RequestField.USER_NAME, uniToken.getUserName())
                .claim(RequestField.ACCOUNT_NAME, uniToken.getAccountName())
                .claim(RequestField.ROLE, uniToken.getRole())
                .claim(RequestField.ORGAN_ID, uniToken.getOrganId())
                .claim(RequestField.ORGAN_ID_SHOW,uniToken.getOrganIdShow())
                .claim(RequestField.ORGAN_CODE,uniToken.getOrganCode())
                .claim(RequestField.ORGAN_NAME, uniToken.getOrganName())
//                .claim(RequestField.PROJECT_ID, uniToken.getProjectId())
                .setIssuedAt(nowDate)
                .signWith(signatureAlgorithm, secretKey);
        // 添加过期时间
        if (expireTime > 0) {
            long expireTimeMillis = nowTimeMillis + expireTime * 1000;
            builder.setExpiration(new Date(expireTimeMillis))
                    .setNotBefore(nowDate);
            uniToken.setExpireTime(expireTimeMillis);
        }
        String token = builder.compact();

//        String redisKey = RedisConstants.getUserTokenKey(uniToken.getUserId());
//        if (RedisUtils.set(redisKey, token, expireTime)) {
//            uniToken.setToken(token);
//            return token;
//        }
        return null;
    }

    /**
     * 校验token
     *
     * @param request
     * @return
     */
    public static UniToken validateToken(HttpServletRequest request) {
        String token = request.getHeader(RequestField.TOKEN);

        return validateToken(token,request);
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    private static UniToken validateToken(String token,HttpServletRequest request) {
        SecretKey secretKey = generalKey(JWT_SECERT);
        String ip = "";
        Claims claims = null;
        UniToken uniToken = null;
        try {
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//            String redisKey = RedisConstants.getUserTokenKey(Long.parseLong(claims.get(RequestField.USERID).toString()));
//            Object redisToken = RedisUtils.get(redisKey);
            String redisToken = "";
            if(null == redisToken){
                logger.error("token check token is expired ip:{},token:{}",ip,token);
                throw new ExpiredJwtException(null, null, null);
            }
            if(!redisToken.toString().equals(token)){
                logger.error("token check token is not latest  ip:{},token:{}",ip,token);
                throw new ExpiredJwtException(null, null, null);
            }

            uniToken = new UniToken()
                    .setUserId(MapUtils.getLong(claims,RequestField.USERID))
                    .setUserIdShow(MapUtils.getString(claims,RequestField.USER_ID_SHOW,""))
                    .setUserName(MapUtils.getString(claims,RequestField.USER_NAME,""))
                    .setAccountName(MapUtils.getString(claims,RequestField.ACCOUNT_NAME,""))
                    .setOrganId(MapUtils.getLong(claims,RequestField.ORGAN_ID))
                    .setOrganIdShow(MapUtils.getString(claims,RequestField.ORGAN_ID_SHOW,""))
                    .setOrganCode(MapUtils.getString(claims,RequestField.ORGAN_CODE,""))
                    .setOrganName(MapUtils.getString(claims,RequestField.ORGAN_NAME,""))
            ;
            List<Integer> rolesList = (List<Integer>) claims.get(RequestField.ROLE);
            if (uniToken.getUserId() == null || StringUtils.isEmpty(uniToken.getUserName())
                    || CollectionUtils.isEmpty(rolesList) || uniToken.getOrganId() == null ) {
                logger.error("token check token is illegal ip:{},token:{}",ip,token);
                throw new IllegalArgumentException();
            }
            // 处理角色强转失败实际为List<Integer>的问题
            List<Long> roles = new ArrayList<>();
            for (Integer role:rolesList) {
                roles.add(role.longValue());
            }
            uniToken.setRole(roles);
            uniToken.setToken(token);
            request.setAttribute(RequestField.UNITOKEN,uniToken);
        } catch (ExpiredJwtException exp) {
            logger.error("validateToken ExpiredJwtException err.ip:{},token:{},",ip,token,exp);
            throw new IllegalArgumentException(" 登录超时 token失效，请重新登录！");
        } catch (Exception exp) {
            logger.error("validateToken Exception err.ip:{},token:{},",ip,token,exp);
            throw new IllegalArgumentException(" 登录超时  用户未登录或角色有误！");
        }
        logger.info("token check token is success. ip:{},token:{}",ip,token);
        return uniToken;
    }

   

    /**
     * 注销token(jwt无状态，无法注销，通过移除redis来实现)
     *
     * @param userId
     * @return
     */
    public static boolean removeUserToken(Long userId) {
        SecretKey secretKey = generalKey(JWT_SECERT);
        Claims claims = null;
        try {
//            String redisKeys = RedisConstants.getUserTokenKeyPattern(userId);
//            RedisUtils.removePattern(redisKeys);
            logger.info("remove userId:{} token success",userId);
            return true;
        } catch (ExpiredJwtException exp) {
            logger.error("remove userId:{} token success.token is expired.",userId,exp);
            return true;
        } catch (Exception exp) {
            logger.error("remove userId:{} token err.",userId,exp);
            return false;
        }
    }

    /**
     * 生成秘钥
     *
     * @return
     */
    private static SecretKey generalKey(String key) {
        byte[] encodedKey = Base64.decodeBase64(key.getBytes());
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    // 手动校验token
    private static UniToken validateToken(String token) {
        SecretKey secretKey = generalKey(JWT_SECERT);
        String ip = "";
        Claims claims = null;
        UniToken uniToken = null;
        try {
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

            uniToken = new UniToken()
                    .setUserId(MapUtils.getLong(claims,RequestField.USERID))
                    .setUserIdShow(MapUtils.getString(claims,RequestField.USER_ID_SHOW,""))
                    .setUserName(MapUtils.getString(claims,RequestField.USER_NAME,""))
                    .setAccountName(MapUtils.getString(claims,RequestField.ACCOUNT_NAME,""))
                    .setOrganId(MapUtils.getLong(claims,RequestField.ORGAN_ID))
                    .setOrganIdShow(MapUtils.getString(claims,RequestField.ORGAN_ID_SHOW,""))
                    .setOrganCode(MapUtils.getString(claims,RequestField.ORGAN_CODE,""))
                    .setOrganName(MapUtils.getString(claims,RequestField.ORGAN_NAME,""))
            ;
            List<Integer> rolesList = (List<Integer>) claims.get(RequestField.ROLE);
            if (uniToken.getUserId() == null || StringUtils.isEmpty(uniToken.getUserName())
                    || CollectionUtils.isEmpty(rolesList) || uniToken.getOrganId() == null ) {
                logger.error("token check token is illegal ip:{},token:{}",ip,token);
                throw new IllegalArgumentException();
            }
            // 处理角色强转失败实际为List<Integer>的问题
            List<Long> roles = new ArrayList<>();
            for (Integer role:rolesList) {
                roles.add(role.longValue());
            }
            uniToken.setRole(roles);
            uniToken.setToken(token);
        } catch (ExpiredJwtException exp) {
            logger.error("validateToken ExpiredJwtException err.ip:{},token:{},",ip,token,exp);
            throw new IllegalArgumentException("登录超时 token失效，请重新登录！");
        } catch (Exception exp) {
            logger.error("validateToken Exception err.ip:{},token:{},",ip,token,exp);
            throw new IllegalArgumentException("登录超时 用户未登录或角色有误！");
        }
        logger.info("token check token is success. ip:{},token:{}",ip,token);
        return uniToken;
    }

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzN2EwZTY1OC0xMGJlLTRlOTMtOTIwOS1jNmI1YjdhOWMxMmEiLCJ1c2VySWQiOjg2NiwidXNlcklkU2hvdyI6IjEwNjUwMTIwMDIiLCJ1c2VyTmFtZSI6IuadqOiTk-iVviIsInJvbGUiOlsxMzldLCJvcmdhbklkIjoyNzIsIm9yZ2FuSWRTaG93IjoiMTA2NTAxMiIsIm9yZ2FuQ29kZSI6IjEyNjUyMzAwNDU3NzUxOTQ3WCIsIm9yZ2FuTmFtZSI6IuaYjOWQieW3nuS6uuawkeWMu-mZoiIsImlhdCI6MTU4OTAyMzA3OCwiZXhwIjoxNTg5MTA5NDc4LCJuYmYiOjE1ODkwMjMwNzh9.dKc82VMIze4ZswnLV4Dxp-FyzGN4qwv3yF7DJdzgA4Y";

        UniToken uniToken = validateToken(token);
        System.out.println(uniToken);
    }
}
