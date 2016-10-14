/**
 * 
 */
package org.vsg.rmodel.search.v1.resource;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ruanweibiao
 *
 */
@Path("/search")
@Produces("application/json")
@Consumes({
	MediaType.APPLICATION_JSON
})
public class SearchResource {
	
	private static Logger logger = LoggerFactory.getLogger( SearchResource.class );
	
	
	@GET
	@Path("/keyword/{keyword}")
	public void getOne(@Suspended AsyncResponse asyncResponse, @PathParam("keyword") String articleId) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		
		// --- set time out ---
		asyncResponse.setTimeout(2, TimeUnit.SECONDS);


	}		

}
