package org.vsg.rmodel.search.v1.resource.image;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vsg.common.i18n.I18nMesssageSupport;
import org.vsg.rmodel.base.MessageCodeConstant;
import org.vsg.rmodel.search.api.IndexerProcessor;
import org.vsg.vo.model.json.ResponseObj;

@Path("/index/image")
@Consumes({
	MediaType.APPLICATION_JSON
})
public class IndexerResource {

	@Inject
	private I18nMesssageSupport i18n;
	
	@Inject
	private IndexerProcessor indexProcessor;

	
	private Logger logger = LoggerFactory.getLogger( ImageSearcherResource.class );	
	
	/**
	 * create and define index
	 * @param asyncResponse
	 * @throws InterruptedException
	 */
	@GET
	public void imageIndex(@Suspended AsyncResponse asyncResponse) throws InterruptedException {
		
		long startTime = System.currentTimeMillis();
		
		
		// --- handle time out ---
		asyncResponse.setTimeoutHandler( new TimeoutHandler() {

			@Override
			public void handleTimeout(AsyncResponse asyResponse) {
				logger.info("reached timeout");
				asyResponse.resume( Response.ok().build() );
			}
			
		});

		// --- set time out ---
		asyncResponse.setTimeout(5, TimeUnit.SECONDS);
		
		
		
		
		// --- call index last handle ---
		indexProcessor.callIndex((a) -> {
			
			if (a.succeeded()) {
				
				// --- run the code ---
			}
			
			else {
				System.out.println(a.cause());
			}
			

		});
		
		// --- call index build research index ---
		ResponseObj result = new ResponseObj();
		result.setMessage( i18n.getMessage(MessageCodeConstant.S100_0001, null) );
		result.setCode( MessageCodeConstant.S100_0001 );
		result.setLang( Locale.getDefault().toString() );
		
		
		
		Response jaxrs = Response.ok( result ).type( MediaType.APPLICATION_JSON ).build();

		asyncResponse.resume(jaxrs);		
	}
	
}
