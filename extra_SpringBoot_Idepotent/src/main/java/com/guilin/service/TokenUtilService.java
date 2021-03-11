package com.guilin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TokenUtilService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * prefix to be stored in redis
     */
    private static final String IDEMPOTENT_TOKEN_PREFIX = "idempotent_token";

    /**
     * Using UUID to create Token string, set "idempotent_token:" + "Token" as the key, adn user info as the value
     *  store k-v into Redis
     */
    public String generateToken(String value) {
        // Instantiate the UUID tool object
        String token = UUID.randomUUID().toString();
        // set the key which should be stored into Redis
        String key = IDEMPOTENT_TOKEN_PREFIX + token;
        // Store Token into Redis, the expiration time is 5 mins
        redisTemplate.opsForValue().set(key, value, 5, TimeUnit.MINUTES);

        return token;
    }

    /**
     *  Validate Token: parameter is the Token string, then concatenate with prefix, and then pass the value
     *  then execute Lua expression to make atomic, to find the corresponding key, and return resust;
     *  if result is not null or 0, validation is true, otherwise is false
     */
    public boolean validateToken(String token, String value) {
        // Lua script, KEYS[1] is the key, KEYS[2] is the value
        String script = "if redis.call('get', KEYS[1]) == KEYS[2] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);

        String key = IDEMPOTENT_TOKEN_PREFIX + token;

        // execute Lua script
        Long result = redisTemplate.execute(redisScript, Arrays.asList(key, value));

        if (result != null && result != 0L) {
            log.info("Validate token={},key={},value={} Successful", token, key, value);
            return true;
        }
        log.info("Validate token={},key={},value={} Failed", token, key, value);
        return false;
    }
}
