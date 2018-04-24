package org.enterpriseNetwork.dao;

import java.util.List;

/**
 * @author wyhong
 * @date 2018-4-24
 */
public interface IntBaseDao<T>{

void insert(T t);
	
	int delete(int id);
	
	int update(T t);
	
	int get(int id);
	
	List<T> getAll();
}
