/**
 * 
 */
package org.vsg.rmodel.search.service.impl;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

import org.vsg.common.async.AsyncResult;
import org.vsg.common.async.Callback;
import org.vsg.rmodel.search.api.IndexerProcessor;

import net.semanticmetadata.lire.imageanalysis.features.global.AutoColorCorrelogram;
import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import net.semanticmetadata.lire.imageanalysis.features.global.FCTH;
import net.semanticmetadata.lire.indexers.parallel.ParallelIndexer;

/**
 * @author ruanweibiao
 *
 */
public class LocalIndexerProcessor implements IndexerProcessor {
	
	@Inject
	@Named("index.image.path")
	private String indexFolder;

	@Inject	
	@Named("image.orgin.path")	
	private String orginImgsPath;
	
	
	

	/* (non-Javadoc)
	 * @see org.vsg.rmodel.search.api.IndexerProcessor#callIndex(org.vsg.rmodel.search.api.Callback)
	 */
	@Override
	public void callIndex(Callback<AsyncResult<Void>> callback) {
		
		int numberOfThreads = 6;
		
		
		VoidAsyncResult var = new VoidAsyncResult();


		
        try {
			ParallelIndexer indexer = new ParallelIndexer(numberOfThreads, indexFolder, orginImgsPath);
			// use this to add you preferred builders. For now we go for CEDD, FCTH and AutoColorCorrelogram
			indexer.addExtractor(CEDD.class);
			indexer.addExtractor(FCTH.class);
			indexer.addExtractor(AutoColorCorrelogram.class);
			indexer.run();
	        
	        var.setSucceed(true);
	        
        } catch (Exception e1) {
        	
        	var.setExceptionCause(e1);

		} finally {
	        
			try {
				if (callback != null) {
					callback.invoke(var);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

       
		
		


	}

}
