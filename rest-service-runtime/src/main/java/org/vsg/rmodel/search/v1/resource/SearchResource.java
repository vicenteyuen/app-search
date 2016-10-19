/**
 * 
 */
package org.vsg.rmodel.search.v1.resource;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
@Consumes({
	MediaType.APPLICATION_JSON
})
public class SearchResource {
	
	private static Logger logger = LoggerFactory.getLogger( SearchResource.class );
	
	
	@GET
	@Produces("application/json")
	@Path("/keyword")
	public void keywordSearch(@Suspended AsyncResponse asyncResponse,@QueryParam("keyword") String keyword) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		
		
		System.out.println("show keyword : " + keyword);
		
		
		
		// --- set time out ---
		asyncResponse.setTimeout(2, TimeUnit.SECONDS);
		
		Map result = new HashMap();
		result.put("ok", "me");
		
		Response jaxrs = Response.ok( result ).type( MediaType.APPLICATION_JSON ).build();

		asyncResponse.resume(jaxrs);


	}		

	
}
