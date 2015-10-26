package com.goodsquick.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.goodsquick.model.GoodsCompanyInfo;
import com.goodsquick.model.WebUserInfo;


public class WebUserInfoRowMapper implements RowMapper<WebUserInfo>{
    @Override
    public WebUserInfo mapRow(ResultSet rs, int i) throws SQLException {
        WebUserInfo dbUser = new WebUserInfo();
        dbUser.setId(rs.getInt("id"));
        dbUser.setName(rs.getString("name"));
        dbUser.setLoginName(rs.getString("login_name"));
        dbUser.setTelephone(rs.getString("telephone"));
        dbUser.setLevel(rs.getString("level"));
        dbUser.setPassword(rs.getString("password"));
        dbUser.setHasHouse(rs.getString("has_house"));
        dbUser.setHasService(rs.getString("has_service"));
        dbUser.setCreatedate(rs.getDate("createdate"));
        dbUser.setUpdatedate(rs.getDate("updatedate"));
        dbUser.setLastLoginDate(rs.getDate("last_login_time"));
        return dbUser;
    }
    
}