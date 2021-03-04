package com.example.demo.dao.Impl;

import com.example.demo.dao.SysLogDao;
import com.example.demo.domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Objects;


/**
 * @author Nicole
 */
@Repository
public class SysLogDaoImpl implements SysLogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveSysLog(SysLog syslog) {
        StringBuffer sql = new StringBuffer("insert into sys_log ");
        sql.append("(id,username,operation,time,method,params,ip,createTime) ");
        sql.append("values(id,:username,:operation,:time,:method,");
        sql.append(":params,:ip,:createTime)");

        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(
                Objects.requireNonNull(this.jdbcTemplate.getDataSource()));

        npjt.update(sql.toString(), new BeanPropertySqlParameterSource(syslog));
    }

}
