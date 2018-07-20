package dao;

import org.enterpriseNetwork.model.Enterprise;

/**
 * @author wyhong
 * @date 2018-5-8
 */
public interface Dao {

	boolean insert(Enterprise enterprise) throws Exception;

	Enterprise get(int id) throws Exception;

}
