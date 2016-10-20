/**
 * 
 */
package org.vsg.rmodel.search.api;

/**
 * @author ruanweibiao
 *
 */
public interface IndexerProcessor {
	
	/**
	 * define call boejct
	 * @param callback
	 */
	void callIndex(Callback<AsyncResult<Void>> callback);

}
