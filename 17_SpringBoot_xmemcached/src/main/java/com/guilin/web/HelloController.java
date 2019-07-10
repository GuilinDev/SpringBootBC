package com.guilin.web;


import net.rubyeye.xmemcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemcachedClient memCachedClient;

    @ResponseBody
    @RequestMapping("/get")
    public String get() {
        try {
            memCachedClient.set("access_token", 3600, "john");
            String access_token = memCachedClient.get("access_token");
            logger.info("从memcached当中获取到的值是：" + access_token);
        } catch (Exception e) {
            logger.info("发生了异常，异常信息是：" + e.getMessage());
        }
        return "memcached connection success";
    }
}