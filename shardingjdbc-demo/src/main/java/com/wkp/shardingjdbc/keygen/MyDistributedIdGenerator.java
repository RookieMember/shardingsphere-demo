package com.wkp.shardingjdbc.keygen;

import lombok.Getter;
import lombok.Setter;
import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


/**
 * @author wangkp
 * @description 自定义分布式主键生成策略
 * @create 2021-05-08 14:55
 */
@Component
public class MyDistributedIdGenerator implements ShardingKeyGenerator {

    /**
     * 日期格式:yyMMddHHmmssSSS
     **/
    public static final String dateFormat = "yyMMddHHmmssSSS";
    private static final SecureRandom random = new SecureRandom();

    @Getter
    @Setter
    private Properties properties = new Properties();

    /**
     * Get algorithm type.
     *
     * @return type
     */
    @Override
    public String getType() {
        return "MYIDGEN";
    }

    /**
     * Generate key.
     *
     * @return generated key
     */
    @Override
    public Comparable<?> generateKey() {
        //这里举个简单的例子，可以根据自己的需求进行实现
        String time = parseStringFromDate(new Date());
        int suffix = random.nextInt(5000) + random.nextInt(5000);
        String genNo=time + suffix;
        return Long.valueOf(genNo);
    }

    private String parseStringFromDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }
}
