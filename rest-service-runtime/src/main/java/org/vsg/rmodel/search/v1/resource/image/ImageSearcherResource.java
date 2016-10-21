package org.vsg.rmodel.search.v1.resource.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vsg.common.fileupload.FileItem;
import org.vsg.common.fileupload.FileUpload;
import org.vsg.common.fileupload.FileUploadException;
import org.vsg.common.fileupload.RequestContext;
import org.vsg.common.fileupload.disk.DiskFileItemFactory;
import org.vsg.common.fileupload.jaxrs.JaxrsRequestContextBuilder;
import org.vsg.common.i18n.I18nMesssageSupport;

import net.semanticmetadata.lire.builders.DocumentBuilder;
import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import net.semanticmetadata.lire.searchers.GenericFastImageSearcher;
import net.semanticmetadata.lire.searchers.ImageSearchHits;
import net.semanticmetadata.lire.searchers.ImageSearcher;

@Path("/search/image")
@Consumes({
	MediaType.MULTIPART_FORM_DATA
})
public class ImageSearcherResource {
	
	@Inject
	private I18nMesssageSupport i18n;

	@Inject	
	@Named("client.image.uploadtmp")	
	private String clientImageUploadPath;
	
	@Inject
	@Named("index.image.path")
	private String indexImagePath;
	
	private Logger logger = LoggerFactory.getLogger( ImageSearcherResource.class );
	
	
	/**
	 * use image search handle
	 * @param asyncResponse
	 * @throws InterruptedException
	 */
	@POST
	public void imageSearch(
			@Context HttpHeaders headers,
			@QueryParam("reqId") String reqId,
			InputStream fileIs ,  @Suspended AsyncResponse asyncResponse ) throws InterruptedException {
		
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
		
		
		// ---- create tmp file ----
		try {

			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository( new File(clientImageUploadPath) );
			
			FileUpload fileUpload = new FileUpload( factory );
			
			RequestContext reqContext = JaxrsRequestContextBuilder.createRequestContext(headers, fileIs);
			List<FileItem>  fileItems =  fileUpload.parseRequest( reqContext );
			
			Map<String, List<FileItem>>  mapping =  fileUpload.parseParameterMap(reqContext);
			
			if (fileItems.size() == 0) {
				// ---- break the item handle ---
			}
			FileItem  fileItem = fileItems.get(0);
			// --- search files record ---
			
			
	        BufferedImage img = ImageIO.read(fileItem.getInputStream());


	        IndexReader ir = DirectoryReader.open(FSDirectory.open(Paths.get(indexImagePath)));
	        ImageSearcher searcher = new GenericFastImageSearcher(30, CEDD.class);

	        // searching with a image file ...
	        ImageSearchHits hits = searcher.search(img, ir);
	        // searching with a Lucene document instance ...
	        for (int i = 0; i < hits.length(); i++) {
	            String fileName = ir.document(hits.documentID(i)).getValues(DocumentBuilder.FIELD_NAME_IDENTIFIER)[0];
	            System.out.println(hits.score(i) + ": \t" + fileName);
	        }
		


		} catch (IOException | FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		
		
		Map result = new HashMap();
		//result.put("ok", msg);
		
		Response jaxrs = Response.ok( result ).type( MediaType.APPLICATION_JSON ).build();

		asyncResponse.resume(jaxrs);


	}			
	
}
