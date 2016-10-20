package org.vsg.rmodel.search.api;

/**
 * call back handle
 * @author ruanweibiao
 *
 * @param <T>
 */
@FunctionalInterface
public interface Callback<T> {

	void invoke(T result) throws Exception;	
	
}
