package com.example.controller;

import com.example.service.PurchaseRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PurchaseController {

    @Resource
    private PurchaseRecordService purchaseRecordService;

    @PostMapping("/purchase")
    public Result purchase(Long userId, Long productId, Integer quantity) {
        boolean success = purchaseRecordService.purchase(userId, productId, quantity);
        String msg = success ? "抢购成功" : "抢购失败";
        return new Result(success, msg);
    }

    private static class Result {
        private boolean success = false;
        private String msg = null;

        public Result() {
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Result(boolean success, String msg) {
            this.success = success;
            this.msg = msg;
        }
    }
}
