package com.chaojilaji.hy.developutils.controller;

import com.chaojilaji.hyutils.dbcore.utils.DatetimeUtil;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Controller
public class GxbMockController {


    public static String getToken(HttpServletRequest servletRequest) {
//        try {
        String token = servletRequest.getHeader("Authorization");
        return token;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
    }

    public static String getBody(HttpServletRequest servletRequest) {
        try {
            String body = servletRequest.getReader().lines().collect(Collectors.joining());
            return body;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Map<String, Object> getBodyByRequest(HttpServletRequest servletRequest) {
        return Json.toMap(getBody(servletRequest));
    }

    private Map<String, Long> cache = new ConcurrentHashMap<>();

    @PostMapping("/isoc-api/api/province/vul/provisionOrder")
    @ResponseBody
    public String provisionOrder(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Map<String, Object> body = getBodyByRequest(httpServletRequest);
        String publicKey = body.getOrDefault("public_key", "").toString();
        if (StringUtils.hasText(publicKey)) {
            cache.put("rU7lolFjRzuaVZgsyevidQ",123L);
            return "{\"access_token\":\"rU7lolFjRzuaVZgsyevidQ\",\"refresh_token\":\"KNYLAASFRTmfhUP4GMxUFQ\",\"expires_in\":3600}";
        }
        String token = getToken(httpServletRequest);
        int tokenFlag = 0;
        if (StringUtils.hasText(token)) {
//            if (cache.containsKey(token)) {
//                Long expireTime = cache.get(token);
//                if (expireTime >= DatetimeUtil.getTimestampOfDatetime(LocalDateTime.now())) {
//                    tokenFlag = 1;
//                } else {
//                    cache.remove(token);
//                }
//            }
            tokenFlag = 1;
        }
        if (tokenFlag == 0) {
            httpServletResponse.setStatus(401);
            httpServletResponse.setHeader("Location", "http://136.25.60.144/provisionOrder");
            return Json.toJson(new HashMap<String, Object>() {
                {
                    put("msg", "Token无效");
                    put("code", 201);
                    put("status", "error");
                }
            });
        }
        String body123 = getBody(httpServletRequest);
        System.out.println(body123);

        return "{\"timeStamp\":1667807179,\"orderID\":\"561154ff-3873-4e75-89fe-450a875e848a\",\"dataSubType\":305,\"dataType\":2,\"statusText\":\"everything is ok\",\"sign\":\"cbfeefeceaca858d1f92e650c26b81bc094d8ef9965b57740b83135df9e6055b\",\"statusCode\":0}";
    }


    @PostMapping("/isoc-api/api/province/vul/deviceManagement")
    @ResponseBody
    public String deviceManagement(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String token = getToken(httpServletRequest);
        int tokenFlag = 0;
        if (StringUtils.hasText(token)) {
//            if (cache.containsKey(token)) {
//                Long expireTime = cache.get(token);
//                if (expireTime >= DatetimeUtil.getTimestampOfDatetime(LocalDateTime.now())) {
//                    tokenFlag = 1;
//                } else {
//                    cache.remove(token);
//                }
//            }
            tokenFlag = 1;
        }
        if (tokenFlag == 0) {
            httpServletResponse.setStatus(401);
            httpServletResponse.setHeader("Location", "http://136.25.60.144/provisionOrder");
            return Json.toJson(new HashMap<String, Object>() {
                {
                    put("msg", "Token无效");
                    put("code", 201);
                    put("status", "error");
                }
            });
        }
        String body = getBody(httpServletRequest);
        System.out.println(body);
        return "{\"timeStamp\":1667807179,\"orderID\":\"561154ff-3873-4e75-89fe-450a875e848a\",\"dataSubType\":305,\"dataType\":2,\"statusText\":\"everything is ok\",\"sign\":\"cbfeefeceaca858d1f92e650c26b81bc094d8ef9965b57740b83135df9e6055b\",\"statusCode\":0}";
    }


}
