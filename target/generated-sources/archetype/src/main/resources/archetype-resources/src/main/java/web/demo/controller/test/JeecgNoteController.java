#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.demo.controller.test;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ${package}.core.common.controller.BaseController;
import ${package}.core.common.hibernate.qbc.CriteriaQuery;
import ${package}.core.common.model.json.AjaxJson;
import ${package}.core.common.model.json.DataGrid;
import ${package}.core.constant.Globals;
import ${package}.core.util.StringUtil;
import ${package}.tag.core.easyui.TagUtil;
import ${package}.web.system.pojo.base.TSDepart;
import ${package}.web.system.service.SystemService;

import ${package}.web.demo.entity.test.JeecgNoteEntity;
import ${package}.web.demo.service.test.JeecgNoteServiceI;

/**   
 * @Title: Controller
 * @Description: 公告
 * @author 张代浩
 * @date 2013-03-12 14:06:34
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/jeecgNoteController")
public class JeecgNoteController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JeecgNoteController.class);

	@Autowired
	private JeecgNoteServiceI jeecgNoteService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 公告列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "jeecgNote")
	public ModelAndView jeecgNote(HttpServletRequest request) {
		return new ModelAndView("jeecg/demo/test/jeecgNoteList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(JeecgNoteEntity jeecgNote,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(JeecgNoteEntity.class, dataGrid);
		//查询条件组装器
		${package}.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jeecgNote);
		this.jeecgNoteService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除公告
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(JeecgNoteEntity jeecgNote, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		jeecgNote = systemService.getEntity(JeecgNoteEntity.class, jeecgNote.getId());
		message = "删除成功";
		jeecgNoteService.delete(jeecgNote);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加公告
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(JeecgNoteEntity jeecgNote, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(jeecgNote.getId())) {
			message = "更新成功";
			jeecgNoteService.saveOrUpdate(jeecgNote);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			jeecgNoteService.save(jeecgNote);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * 公告列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(JeecgNoteEntity jeecgNote, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jeecgNote.getId())) {
			jeecgNote = jeecgNoteService.getEntity(JeecgNoteEntity.class, jeecgNote.getId());
			req.setAttribute("jeecgNotePage", jeecgNote);
		}
		return new ModelAndView("jeecg/demo/test/jeecgNote");
	}
}