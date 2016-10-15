package org.vsg.rmodel.module;

import org.vsg.rmodel.search.v1.resource.SearchResource;

import com.google.inject.AbstractModule;

public class ResourceModule extends AbstractModule {

	@Override
	protected void configure() {
		
		this.binder().bind(SearchResource.class);

	}

}
