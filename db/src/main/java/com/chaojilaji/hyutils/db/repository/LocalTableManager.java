package com.chaojilaji.hyutils.db.repository;

import com.chaojilaji.hyutils.db.model.DataField;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface LocalTableManager {
    List<String> getAllTableNames();
    List<DataField> getTableDataField(String name);
}
