/**
 * 
 */
package org.vsg.rmodel.search.api;

import org.vsg.common.async.AsyncResult;
import org.vsg.common.async.Callback;

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
