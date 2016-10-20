package org.vsg.rmodel.module;

import javax.inject.Singleton;

import org.vsg.rmodel.search.api.IndexerProcessor;
import org.vsg.rmodel.search.service.impl.LocalIndexerProcessor;
import org.vsg.rmodel.search.v1.resource.SearchResource;
import org.vsg.rmodel.search.v1.resource.image.ImageSearcherResource;
import org.vsg.rmodel.search.v1.resource.image.IndexerResource;

import com.google.inject.AbstractModule;

public class ResourceModule extends AbstractModule {

	@Override
	protected void configure() {
		
		
		// --- bind service ---
		this.binder().bind(IndexerProcessor.class ).to(LocalIndexerProcessor.class).in( Singleton.class );
		
		
		
		this.binder().bind( SearchResource.class);
		this.binder().bind( ImageSearcherResource.class );
		this.binder().bind( IndexerResource.class );

	}

}
