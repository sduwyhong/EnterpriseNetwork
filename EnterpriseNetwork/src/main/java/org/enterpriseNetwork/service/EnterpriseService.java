package org.enterpriseNetwork.service;

import org.enterpriseNetwork.model.Enterprise;

/**
 * @author wyhong
 * @date 2018-4-24
 */
public interface EnterpriseService {

	String register(Enterprise enterprise);

	String getName(int enterpriseId);

	String getAll();
}
