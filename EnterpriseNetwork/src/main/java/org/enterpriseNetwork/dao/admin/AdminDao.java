package org.enterpriseNetwork.dao.admin;

import org.apache.ibatis.annotations.Param;
import org.enterpriseNetwork.VO.Corporation;
import org.enterpriseNetwork.dao.StringBaseDao;
import org.enterpriseNetwork.model.Admin;
import org.enterpriseNetwork.model.Product;

/**
 * @author wyhong
 * @date 2018-4-24
 */
public interface AdminDao extends StringBaseDao<Admin>{

	Admin getByNo(String admin_no);

	int deleteEmployee(String employeeId);

	int addProduct(Product product);

	int deleteProduct(int productId);

	int addComposition(@Param("productId")int productId, @Param("compositionId")int compositionId);

	int deleteComposition(@Param("productId")int productId, @Param("compositionId")int compositionId);

	int delegateEnterprise(@Param("employeeId")String employeeId, @Param("enterpriseId")int enterpriseId);

	int cancelDelegationOfEnterprise(@Param("employeeId")String employeeId, @Param("enterpriseId")int enterpriseId);

	int delegateProduct(@Param("employeeId")String employeeId, @Param("productId")int productId);

	int cancelDelegationOfProduct(@Param("employeeId")String employeeId, @Param("productId")int productId);

	int corporate(Corporation corporation);

	int cancelCorporation(@Param("enterpriseId")int enterpriseId, @Param("partnerId")int partnerId);

}
