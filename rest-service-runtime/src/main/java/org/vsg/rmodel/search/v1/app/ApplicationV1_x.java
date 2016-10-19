/**
 * 
 */
package org.vsg.rmodel.search.v1.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

import org.vsg.rest.plugins.ext.AbstractGuiceInjectApplication;
import org.vsg.rmodel.search.v1.resource.SearchResource;
import org.vsg.rmodel.search.v1.resource.image.ImageSearcherResource;

import com.google.inject.Injector;

/**
 * @author ruanweibiao
 *
 */
@ApplicationPath("v1")
public class ApplicationV1_x extends AbstractGuiceInjectApplication {

	public ApplicationV1_x(Injector injector) {
		super(injector);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Set<Object> getSingletons() {
		Set<Object> objects = new HashSet<Object>();
		
		Injector injector = this.getInjector();
		

		objects.add( injector.getInstance( SearchResource.class ) );
		objects.add( injector.getInstance( ImageSearcherResource.class ) );

		return objects;
	}	

}
