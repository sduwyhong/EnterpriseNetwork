package org.enterpriseNetwork.dao;

import java.util.List;

/**
 * @author wyhong
 * @date 2018-4-24
 */
public interface StringBaseDao<T> {

	void insert(T t);
	
	int delete(String id);
	
	int update(T t);
	
	int get(String id);
	
	List<T> getAll();
}
