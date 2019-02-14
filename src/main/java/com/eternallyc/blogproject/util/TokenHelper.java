package com.eternallyc.blogproject.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


public class TokenHelper {

    private Claims claims;

    public TokenHelper(String token)  {
        token = token.substring(7);
        this.claims = Jwts.parser().setSigningKey(Const.JWT_SECRET_KEY).parseClaimsJws(token).getBody();;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    public boolean tokenExpIsFailed() {
        String nowDate = DateHelpler.getDateNow();//获取现在时间戳
        String tokenExpDate = DateHelpler.getTokenExpirationDate(claims.getExpiration());
        if(nowDate.compareTo(tokenExpDate) > 0 ) {//如果现在时间戳大于token过期时间戳
            return true;//表示已经过期
        }
        return false;
    }

    public String getTokenUser() {
        return claims.getSubject();
    }
}
