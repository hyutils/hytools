package com.chaojilaji.hyutils.db.repository.impl;

import com.chaojilaji.hyutils.db.model.DataField;
import com.chaojilaji.hyutils.db.repository.LocalTableManager;
import com.chaojilaji.hyutils.dbcore.utils.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class LocalTableManagerImpImpl implements LocalTableManager {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<String> getAllTableNames() {
        String sql = "select tablename from pg_tables where schemaname='public';\n";
        return namedParameterJdbcTemplate.queryForList(sql, new HashMap<>()).stream().map(a -> {
            return a.getOrDefault("tablename", "").toString();
        }).collect(Collectors.toList());
    }

    @Override
    public List<DataField> getTableDataField(String name) {
        String sql = "SELECT\n" +
                " A.attnum,\n" +
                "\t( SELECT description FROM pg_catalog.pg_description WHERE objoid = A.attrelid AND objsubid = A.attnum ) AS descript,\n" +
                "\tA.attname,\n" +
                "\t( select typname from pg_type where oid = A.atttypid) AS type,\n" +
                "\tA.atttypmod AS data_type\n" +
                "FROM\n" +
                "\tpg_catalog.pg_attribute A\n" +
                "WHERE\n" +
                "\t1 = 1\n" +
                "\tAND A.attrelid = ( SELECT oid FROM pg_class WHERE relname = ':tableName' )\n" +
                "\tAND A.attnum > 0\n" +
                "\tAND NOT A.attisdropped\n" +
                "ORDER BY\n" +
                "\tA.attnum;";
        Map<String, Object> params = new HashMap<>();
        params.put("tableName", name);
        return namedParameterJdbcTemplate.queryForList(sql, params).stream().map(a -> {
            return Json.toObject(Json.toJson(a), DataField.class);
        }).collect(Collectors.toList());
    }
}
