package com.baobaotao.dao;

import com.baobaotao.domain.LoginLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by 10238 on 2016/9/19.
 */
@Repository
public class LoginLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertLoginLog(LoginLog loginLog){
        String sqlStr = "INSERT INTO t_login_log(user_id,ip,login_datetime) VALUES(?,?,?)";
        Object[] args = {loginLog.getUserId(),loginLog.getIp(),loginLog.getLoginDate()};
        jdbcTemplate.update(sqlStr,args);

    }
}
