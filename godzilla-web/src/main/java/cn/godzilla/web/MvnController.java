package cn.godzilla.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.godzilla.common.ReturnCodeEnum;
import cn.godzilla.common.StringUtil;
import cn.godzilla.service.ClientConfigService;
import cn.godzilla.service.MvnService;
import cn.godzilla.service.OperateLogService;

@Component
@RequestMapping("/mvn")
public class MvnController extends GodzillaApplication{
	
	private final Logger logger = LogManager.getLogger(MvnController.class);
	
	@Autowired
	private ClientConfigService clientConfigService;
	@Autowired
	private MvnService mvnService;
	@Autowired
	private OperateLogService operateLogService;
	/**
	 * 部署
	 * 源码路径/项目名/环境类型/
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/{sid}/{projectCode}/{profile}/deploy", method=RequestMethod.POST)
	@ResponseBody
	public Object deploy(@PathVariable String sid,@PathVariable String projectCode,@PathVariable String profile,HttpServletRequest request, HttpServletResponse response) {

		logger.debug("*****MvnController.deploy*****");
		String srcUrl = StringUtil.getReqPrameter(request, "srcUrl");
		String parentVersion = StringUtil.getReqPrameter(request, "parentVersion", "");
		
		ReturnCodeEnum deployReturn = mvnService.doDeploy(srcUrl, projectCode, profile, parentVersion);
		
		if(deployReturn == ReturnCodeEnum.OK_MVNDEPLOY) {
			operateLogService.addOperateLog(super.getUser().getUserName(), projectCode, profile, DEPLOY, SUCCESS, ReturnCodeEnum.OK_MVNDEPLOY.getReturnMsg());
			return SUCCESS;
		} else if(deployReturn == ReturnCodeEnum.NO_MVNDEPLOY) {
			operateLogService.addOperateLog(super.getUser().getUserName(), projectCode, profile, DEPLOY, FAILURE, ReturnCodeEnum.NO_MVNDEPLOY.getReturnMsg());
			return FAILURE;
		} else if(deployReturn == ReturnCodeEnum.NO_CHANGEPOM) {
			operateLogService.addOperateLog(super.getUser().getUserName(), projectCode, profile, DEPLOY, FAILURE, ReturnCodeEnum.NO_CHANGEPOM.getReturnMsg());
			return FAILURE;
		} else {
			operateLogService.addOperateLog(super.getUser().getUserName(), projectCode, profile, DEPLOY, FAILURE, FAILURE);
			return FAILURE;
		}
	}
	
	@RequestMapping(value="/{sid}/{projectCode}/{profile}/process", method=RequestMethod.POST)
	@ResponseBody
	public Object process(@PathVariable String sid, @PathVariable String projectCode, @PathVariable String profile, HttpServletRequest request, HttpServletResponse response) {
		
		logger.debug("*****MvnController.process*****");
		
		String processPercent = mvnService.getProcessPercent(sid, projectCode, profile);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("returncode", OK_AJAX);
		resultMap.put("returnmsg", SUCCESS);
		resultMap.put("processPercent", processPercent);		
		return resultMap;
	}
	
}
