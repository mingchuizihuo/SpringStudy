package com.baobaotao.dao;

import com.baobaotao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 10238 on 2016/9/19.
 */
@Repository//表示Dao
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getMatchCount(String userName,String password){
        String sqlStr = "SELECT count(*) FORM t_user WHERE user_name = ? and password = ?";
       /* return jdbcTemplate.query(sqlStr, new Object[]{userName, password}, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return resultSet.getInt(1);
            }
        });*/
       return jdbcTemplate.queryForInt(sqlStr,new Object[]{userName, password});
    }
    public User findUserByUserName(final String userName){
        String sqlStr = "SELECT user_id,user_name,credits FROM t_user WHERE user_name = ?";
        final User user = new User();
        jdbcTemplate.query(sqlStr, new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user){
        String sqlStr = "UPDATE t_user SET last_visit = ?,last_ip = ?,credits = ?, WHERE user_id = ?";
        jdbcTemplate.update(sqlStr,new Object[]{user.getLastVisit(),user.getLastIp(),user.getCredits(),user.getUserId()});
    }
}
