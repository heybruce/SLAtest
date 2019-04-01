package utils;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.concurrent.TimeUnit;

public class RedisManager {

    public static String getValue(String key) {

//        RedisClient redisClient = RedisClient.create("redis://10.29.25.98:6379");
        RedisClient redisClient = RedisClient.create(RedisURI.create("10.29.25.98", 6379));
        redisClient.setDefaultTimeout(20, TimeUnit.SECONDS);
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        System.out.println("Connected to Redis");

        RedisCommands<String, String> syncCommands = connection.sync();
        String value = syncCommands.get(key);

        connection.close();
        redisClient.shutdown();

        return value;
    }

    public static void setValue(String key, String value) {

//        RedisClient redisClient = RedisClient.create("redis://10.29.25.98:6379");
        RedisClient redisClient = RedisClient.create(RedisURI.create("10.29.25.98", 6379));
        redisClient.setDefaultTimeout(20, TimeUnit.SECONDS);
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        System.out.println("Connected to Redis");

        RedisCommands<String, String> syncCommands = connection.sync();
        syncCommands.set(key, value);

        connection.close();
        redisClient.shutdown();
    }
}
