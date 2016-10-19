package org.vsg.rmodel.search.v1.resource.image;

import java.util.HashMap;
import java.util.Map;
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

@Path("/search/image")
@Consumes({
	MediaType.APPLICATION_JSON
})
public class ImageSearcherResource {
	
	@Inject
	private I18nMesssageSupport i18n;
	
	
	
	
	private Logger logger = LoggerFactory.getLogger( ImageSearcherResource.class );
	
	
	/**
	 * use image search handle
	 * @param asyncResponse
	 * @throws InterruptedException
	 */
	@GET
	public void imageSearch(@Suspended AsyncResponse asyncResponse) throws InterruptedException {
		
		long startTime = System.currentTimeMillis();
		
		System.out.println(i18n);
		

		// --- set time out ---
		asyncResponse.setTimeout(2, TimeUnit.SECONDS);
		
		
		String msg = i18n.getMessage("label.title", null);
		
		System.out.println(msg);
		
		Map result = new HashMap();
		result.put("ok", msg);
		
		Response jaxrs = Response.ok( result ).type( MediaType.APPLICATION_JSON ).build();

		asyncResponse.resume(jaxrs);


	}			
	
}
