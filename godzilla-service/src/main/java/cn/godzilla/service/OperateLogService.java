package cn.godzilla.service;

import java.util.List;

import cn.godzilla.common.Constant;
import cn.godzilla.common.response.ResponseBodyJson;
import cn.godzilla.model.OperateLog;

public interface OperateLogService extends Constant {

	public List<OperateLog> queryList(String projectCode,String profile);
	
	public List<OperateLog> queryAll(Long id);

	public int addOperateLog(String username, String realname,String projectCode, String profile, String operation, String operateCode, String executeResult, String resultInfo) ;

	public OperateLog queryLogById(String logid);

	public int addOperateLog(String mvnlog, String jarlog);

	public int updateOperateLog(int logid, String username, String realname,String projectCode, String profile, String operation, String operateCode, String executeResult, String resultInfo) ;

}
