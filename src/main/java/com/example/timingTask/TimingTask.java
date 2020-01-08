package com.example.timingTask;

import com.example.bean.PurchaseRecord;
import com.example.service.PurchaseRecordService;
import com.example.service.RedisService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@EnableScheduling
@Component
public class TimingTask {

    @Resource
    private RedisService redisService;
    @Resource
    private PurchaseRecordService purchaseRecordService;

    @Scheduled(cron = "*/30 * * * * *")
    public void savePurchaseRecord() {
        System.out.println("保存购买记录。。。保存时间：" + LocalDateTime.now());
        List<PurchaseRecord> list = redisService.range("purchaseRecord", 0, 99, PurchaseRecord.class);
        if (list.size() > 0) {
            purchaseRecordService.batchAddRecords(list);
            redisService.trim("purchaseRecord", list.size(), -1);
        }
    }

}
