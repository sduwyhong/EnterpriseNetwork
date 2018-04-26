package org.enterpriseNetwork.dao.enterprise;

import org.enterpriseNetwork.dao.IntBaseDao;
import org.enterpriseNetwork.model.Enterprise;

/**
 * @author wyhong
 * @date 2018-4-24
 */
public interface EnterpriseDao extends IntBaseDao<Enterprise> {

	String getName(int enterpriseId);

}
