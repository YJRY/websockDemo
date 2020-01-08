package com.example.dao;

import com.example.bean.PurchaseRecord;

import java.util.List;

public interface PurchaseRecordDao {

    int addRecord(PurchaseRecord record);

    void batchAddRecords(List<PurchaseRecord> records);
}
