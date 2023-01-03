package com.chaojilaji.hy.developutils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    public static String getPassword(HttpServletRequest servletRequest){
        String password = servletRequest.getHeader("password");
        return password.replace("\"","");
    }

    public static String getUserName(HttpServletRequest servletRequest){
        String password = servletRequest.getHeader("username");
        return password.replace("\"","");
    }
}
