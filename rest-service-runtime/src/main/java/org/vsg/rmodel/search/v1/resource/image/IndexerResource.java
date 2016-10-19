package org.vsg.rmodel.search.v1.resource.image;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vsg.common.i18n.I18nMesssageSupport;
import org.vsg.vo.model.json.ResponseObj;

@Path("/index/image")
@Consumes({
	MediaType.APPLICATION_JSON
})
public class IndexerResource {

	@Inject
	private I18nMesssageSupport i18n;

	
	private Logger logger = LoggerFactory.getLogger( ImageSearcherResource.class );	
	
	/**
	 * create and define index
	 * @param asyncResponse
	 * @throws InterruptedException
	 */
	@GET
	public void imageIndex(@Suspended AsyncResponse asyncResponse) throws InterruptedException {
		long startTime = System.currentTimeMillis();

		// --- set time out ---
		asyncResponse.setTimeout(5, TimeUnit.SECONDS);
		
		
		
		
		// --- call index build research index ---

		
		ResponseObj result = new ResponseObj();
		
		
		Response jaxrs = Response.ok( result ).type( MediaType.APPLICATION_JSON ).build();

		asyncResponse.resume(jaxrs);		
	}
	
}
