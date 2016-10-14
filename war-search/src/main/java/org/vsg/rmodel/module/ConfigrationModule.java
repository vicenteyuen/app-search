package org.vsg.rmodel.module;

import java.io.File;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class ConfigrationModule extends AbstractModule {

	@Override
	protected void configure() {
		Configuration configration = loadConfigration();
		
		// --- set configration handle ---
		bind(String.class).annotatedWith(Names.named("JDBC.driver")).toInstance(configration.getString("oracle.driverClassName"));
		bind(String.class).annotatedWith(Names.named("JDBC.url")).toInstance(configration.getString("oracle.url"));				
		bind(String.class).annotatedWith(Names.named("JDBC.username")).toInstance(configration.getString("oracle.username"));						
		bind(String.class).annotatedWith(Names.named("JDBC.password")).toInstance(configration.getString("oracle.password"));

	}
	
	
	private static Configuration loadConfigration() {
		Parameters params = new Parameters();
		// Read data from this file
		File propertiesFile = new File("config.properties");

		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
		    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		    .configure(params.fileBased()
		        .setFile(propertiesFile));
		try
		{
		    Configuration config = builder.getConfiguration();
		    return config;

		    // config contains all properties read from the file
		}
		catch(ConfigurationException cex)
		{
		    // loading of the configuration file failed
			cex.printStackTrace();
		}
		return null;
				
	}		

}