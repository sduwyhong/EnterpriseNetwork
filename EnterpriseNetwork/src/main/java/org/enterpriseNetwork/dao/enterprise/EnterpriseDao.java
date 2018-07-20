package org.enterpriseNetwork.dao.enterprise;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.enterpriseNetwork.dao.IntBaseDao;
import org.enterpriseNetwork.model.Enterprise;

/**
 * @author wyhong
 * @date 2018-4-24
 */
public interface EnterpriseDao extends IntBaseDao<Enterprise> {

    @Select("SELECT name FROM enterprise WHERE id = #{enterpriseId}")
	String getName(int enterpriseId);

}
