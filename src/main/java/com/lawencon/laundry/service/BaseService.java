package com.lawencon.laundry.service;

/**
 * @author Dzaky Fadhilla Guci
 */

public abstract class BaseService {

	protected StringBuilder bBuilder(String... datas) {
		StringBuilder b = new StringBuilder();
		for (String d : datas) {
			b.append(d);
		}
		return b;
	}

}
