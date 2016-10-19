package org.vsg.rmodel.module;

import org.vsg.rmodel.search.v1.resource.SearchResource;
import org.vsg.rmodel.search.v1.resource.image.ImageSearcherResource;
import org.vsg.rmodel.search.v1.resource.image.IndexerResource;

import com.google.inject.AbstractModule;

public class ResourceModule extends AbstractModule {

	@Override
	protected void configure() {
		
		this.binder().bind( SearchResource.class);
		this.binder().bind( ImageSearcherResource.class );
		this.binder().bind( IndexerResource.class );

	}

}
