package com.iteachcoding.web.util;

import javax.servlet.http.*;

public class CookieUtility {

    public static String getCookieValue(final Cookie[] cookies, final String cookieName) {
        
        String cookieValue = "";
        if (cookies != null) {
            for (final Cookie cookie: cookies) {
                if (cookieName.equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                }
            }
        }
        return cookieValue;
    }
}
