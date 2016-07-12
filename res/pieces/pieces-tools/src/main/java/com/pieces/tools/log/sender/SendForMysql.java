package com.pieces.tools.log.sender;

import com.pieces.tools.log.pojo.LogInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by kevin1 on 7/11/16.
 */
public class SendForMysql {
    private static Logger log = LoggerFactory.getLogger(SendForMysql.class);
    private static final Integer BATCH_DEAL_NUM = Integer.valueOf(1000);
    private JdbcTemplate jdbcTemplate;

    public SendForMysql() {
    }

    public boolean send(LogInfo logInfo) {

//        String bizSql = "insert into bizlog(ACTION_CLASSNAME,ACTION_METHOD,APP_CODE,APP_HOST,UNIQREQ_ID,LOCAL_LAYER,BIZ_DESC,BIZ_TYPE,LOG_TIME,LOG_TYPE,SUCCESSED,USER_ID,USER_NAME,AREA_ID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        String calSql = "insert into callog(ACTION_CLASSNAME,ACTION_METHOD,APP_CODE,APP_HOST,BIZ_DESC,BIZ_TYPE,COST_TIME,EXCEPTION_CLASS_NAME,EXCEPTION_DESC,EXT_INFO,IN_PARAM,LOG_TIME,LOG_TYPE,OUT_PARAM,REQUEST_INFO,REQUEST_IP,REQUEST_URL,SUCCESSED,UNIQREQ_ID,USER_ID,USER_NAME) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        // this.calJdbcTemplate.batchUpdate(bizSql, new CalSenderForMysql.BizBatchPreparedStatementSetter(bizLogList));

                try {
                    this.jdbcTemplate.update(calSql, new SendForMysql.LogPreparedStatementSetter(logInfo));
                } catch (Exception var9) {
                    log.error("CalSenderForMysql.send.callog", var9);
                }

        return true;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private class LogPreparedStatementSetter implements PreparedStatementSetter {

        private LogInfo logInfo;


        public LogPreparedStatementSetter(LogInfo logInfo) {
            this.logInfo = logInfo;
        }

        public void setValues(PreparedStatement ps) throws SQLException {

            if(logInfo.getActionClassname() == null) {
                ps.setNull(1, 12);
            } else {
                ps.setString(1, logInfo.getActionClassname());
            }

            if(logInfo.getActionMethod() == null) {
                ps.setNull(2, 12);
            } else {
                ps.setString(2, logInfo.getActionMethod());
            }

            if(logInfo.getAppCode() == null) {
                ps.setNull(3, 12);
            } else {
                ps.setString(3, logInfo.getAppCode());
            }

            if(logInfo.getAppHost() == null) {
                ps.setNull(4, 12);
            } else {
                ps.setString(4, logInfo.getAppHost());
            }

            if(logInfo.getBizDesc() == null) {
                ps.setNull(5, 12);
            } else {
                ps.setString(5, logInfo.getBizDesc());
            }

            if(logInfo.getBizType() == null) {
                ps.setNull(6, 12);
            } else {
                ps.setString(6, logInfo.getBizType());
            }

            if(logInfo.getCostTime() == null) {
                ps.setNull(7, 3);
            } else {
                ps.setLong(7, (long)logInfo.getCostTime().intValue());
            }

            if(logInfo.getExceptionClassname() == null) {
                ps.setNull(8, 12);
            } else {
                ps.setString(8, logInfo.getExceptionClassname());
            }

            if(logInfo.getExceptionDesc() == null) {
                ps.setNull(9, 12);
            } else {
                ps.setString(9, logInfo.getExceptionDesc());
            }

            if(logInfo.getExtInfo() == null) {
                ps.setNull(10, 12);
            } else {
                ps.setString(10, logInfo.getExtInfo());
            }

            if(logInfo.getInParam() == null) {
                ps.setNull(11, 12);
            } else {
                ps.setString(11, logInfo.getInParam().length() > 2000?logInfo.getInParam().substring(0, 2000):logInfo.getInParam());
            }

            if(logInfo.getLogTime() == null) {
                ps.setNull(12, 91);
            } else {
                ps.setTimestamp(12, new Timestamp(logInfo.getLogTime().getTime()));
            }

            if(logInfo.getLogType() == null) {
                ps.setNull(13, 12);
            } else {
                ps.setString(13, logInfo.getLogType());
            }

            if(logInfo.getOutParam() == null) {
                ps.setNull(14, 12);
            } else {
                ps.setString(14, logInfo.getOutParam());
            }

            if(logInfo.getRequestInfo() == null) {
                ps.setNull(15, 12);
            } else {
                ps.setString(15, logInfo.getRequestInfo().length() > 2000?logInfo.getRequestInfo().substring(0, 2000):logInfo.getRequestInfo());
            }

            if(logInfo.getRequestIp() == null) {
                ps.setNull(16, 12);
            } else {
                ps.setString(16, logInfo.getRequestIp());
            }

            if(logInfo.getRequestUrl() == null) {
                ps.setNull(17, 12);
            } else {
                ps.setString(17, logInfo.getRequestUrl());
            }

            if(logInfo.getSuccessed() == null) {
                ps.setNull(18, 4);
            } else {
                ps.setInt(18, logInfo.getSuccessed().intValue());
            }

            if(logInfo.getUserId() == null) {
                ps.setNull(19, 3);
            } else {
                ps.setLong(19, logInfo.getUserId().longValue());
            }

            if(logInfo.getUserName() == null) {
                ps.setNull(20, 12);
            } else {
                ps.setString(20, logInfo.getUserName());
            }
        }


    }
}
