package com.fh.redis;

public interface RedisService {
    void setStringKeyAndValue(String key, Object value);

    Boolean isExistKey(String categoryAll);

    Object getStringValueByKey(String categoryAll);
}
