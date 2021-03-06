#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package weixin.cms.service.impl;
import weixin.cms.service.WeixinCmsStyleServiceI;
import ${package}.core.common.service.impl.CommonServiceImpl;
import weixin.cms.entity.WeixinCmsStyleEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("weixinCmsStyleService")
@Transactional
public class WeixinCmsStyleServiceImpl extends CommonServiceImpl implements WeixinCmsStyleServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((WeixinCmsStyleEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((WeixinCmsStyleEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((WeixinCmsStyleEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(WeixinCmsStyleEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(WeixinCmsStyleEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(WeixinCmsStyleEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,WeixinCmsStyleEntity t){
 		sql  = sql.replace("${symbol_pound}{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("${symbol_pound}{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("${symbol_pound}{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("${symbol_pound}{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("${symbol_pound}{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("${symbol_pound}{template_name}",String.valueOf(t.getTemplateName()));
 		sql  = sql.replace("${symbol_pound}{template_url}",String.valueOf(t.getTemplateUrl()));
 		sql  = sql.replace("${symbol_pound}{review_img_url}",String.valueOf(t.getReviewImgUrl()));
 		sql  = sql.replace("${symbol_pound}{accountid}",String.valueOf(t.getAccountid()));
 		sql  = sql.replace("${symbol_pound}{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}