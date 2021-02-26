package com.lawencon.laundry.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Dzaky Fadhilla Guci
 * @param <T>
 */

public class BaseDao {

	@PersistenceContext
	protected EntityManager em;

	protected StringBuilder bBuilder(String... datas) {
		StringBuilder b = new StringBuilder();
		for (String d : datas) {
			b.append(d);
		}
		return b;
	}

	protected <T> T getResultModel(List<T> obj) {
		return obj.size() > 0 ? obj.get(0) : null;
	}


}
