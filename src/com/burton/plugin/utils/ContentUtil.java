package com.burton.plugin.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/*********************************
 * <p> 文件名称: ContentUtil
 * <p> 系统名称：交易银行系统V1.0
 * <p> 模块名称：com.burton.plugin.utils
 * <p> 功能说明: 启动idea每日鸡汤
 * <p> 开发人员：jiangjun25372
 * <p> 开发时间：2020/8/22
 * <p> 修改记录：程序版本   修改日期    修改人员   修改单号   修改说明
 **********************************/
public class ContentUtil {
    private static int i = 0;
    public static String getContent() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> forEntity = restTemplate.getForEntity("https://apiv3.shanbay.com/weapps/dailyquote/quote/", Map.class);
            HttpStatus statusCode = forEntity.getStatusCode();
            Map body = forEntity.getBody();
            String note = (String)body.get("content");
            return note;
        } catch (RestClientException e) {
            //return "每日鸡汤获取失败";
        }
        return "获取鸡汤失败" + (++i);

    }
}
