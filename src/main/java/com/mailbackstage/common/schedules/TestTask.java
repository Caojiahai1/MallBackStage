package com.mailbackstage.common.schedules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yan liang
 * @create 2019/6/7
 * @since 1.0.0
 */
@Component
public class TestTask {
    private Logger logger = LoggerFactory.getLogger(TestTask.class);

    @Scheduled(cron = "0 * * ? * ?")
    private void test() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间：" + format.format(new Date()));
    }
}