package com.bigguy.dao;

import com.bigguy.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author bigguy_hc
 * @create 2019-06-03 22:52
 */
@Repository
public class PermissionDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public boolean save(Permission permission){
        String sql = "insert into permission(name, resource) values(?, ?)";

        int num = jdbcTemplate.update(sql, permission.getName(), permission.getResource());

        return num>0 ? true : false;


    }


}
