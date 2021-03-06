package org.enterpriseNetwork.dao.admin;

import java.util.List;
import java.util.Vector;

import org.apache.ibatis.annotations.Param;
import org.enterpriseNetwork.VO.CompositionVO;
import org.enterpriseNetwork.VO.Corporation;
import org.enterpriseNetwork.VO.EnterpriseDelegation;
import org.enterpriseNetwork.VO.ProductDelegation;
import org.enterpriseNetwork.VO.ProductVO;
import org.enterpriseNetwork.dao.StringBaseDao;
import org.enterpriseNetwork.model.Admin;
import org.enterpriseNetwork.model.Employee;
import org.enterpriseNetwork.model.Enterprise;
import org.enterpriseNetwork.model.Product;

/**
 * @author wyhong
 * @date 2018-4-24
 */
public interface AdminDao extends StringBaseDao<Admin>{

	Admin getByNo(@Param("admin_no")String admin_no, @Param("enterpriseId")int enterpriseId);

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

	List<Employee> getEmployees(int enterpriseId);
	
	List<ProductVO> getProducts(int enterpriseId);

	List<CompositionVO> getCompositions(int enterpriseId);

	List<EnterpriseDelegation> getEnterpriseDelegations(int enterpriseId);

	List<ProductDelegation> getProductDelegations(int enterpriseId);

	List<Corporation> getCorporations(int enterpriseId);

	List<Enterprise> getEnterprises();

	int deleteAllCompositionRelation(int productId);

	List<Product> getPretentialCompositions(int enterpriseId);

	List<Enterprise> getPretentialCorporations(int enterpriseId);

	Corporation getEmployeeInfoByPartner(@Param("enterpriseId")int enterpriseId, @Param("partnerId")int partnerId);

	String getEmployeeIdByCorporatioin(@Param("enterpriseId")int enterpriseId, @Param("partnerId")int partnerId);

	List<Enterprise> getOtherEnterprises(int enterpriseId);

	Employee getInCharge(@Param("enterpriseId")int enterpriseId, @Param("partnerId")int partnerId);


}
