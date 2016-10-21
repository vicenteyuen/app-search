package org.vsg.rmodel.module;

import java.io.File;
import java.util.Locale;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.vsg.common.i18n.ClasspathI18nMessageProvider;
import org.vsg.common.i18n.I18nMesssageSupport;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

public class ConfigrationModule extends AbstractModule {

	@Override
	protected void configure() {

	}
	
	/**
	 * config i18n handle
	 */
	@Provides
	private I18nMesssageSupport configI18n() {
		ClasspathI18nMessageProvider provider = new ClasspathI18nMessageProvider();
		provider.setBasenames(new String[]
			{"i18n/message","i18n/error"}
		);

		
		return provider;
	}

}
