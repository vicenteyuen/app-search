/**
 * 
 */
package org.vsg.rmodel.search.v1.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

import org.vsg.rest.plugins.ext.AbstractGuiceInjectApplication;

import com.google.inject.Injector;

/**
 * @author ruanweibiao
 *
 */
@ApplicationPath("v1")
public class ApplicationV1_x extends AbstractGuiceInjectApplication {
	
	
	@Override
	public Set<Object> getSingletons() {
		Set<Object> objects = new HashSet<Object>();
		

		//objects.add( injector.getInstance( ArticlesResource.class ) );

		return objects;
	}	

}
