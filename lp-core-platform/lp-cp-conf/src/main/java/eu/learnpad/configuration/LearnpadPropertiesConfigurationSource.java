/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package eu.learnpad.configuration;

import java.net.URL;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.configuration.internal.CommonsConfigurationSource;
import org.xwiki.environment.Environment;

@Component
@Named("learnpadproperties")
@Singleton
public class LearnpadPropertiesConfigurationSource extends CommonsConfigurationSource implements Initializable {
	/**
	 * The file where standard filesystem properties are located.
	 */
	private static final String LEARNPAD_PROPERTIES_FILE = "/WEB-INF/learnpad.properties";

	private enum Component {
		CW, CA, MT, MV, SIM, DASH
	};

	/**
	 * the Environment from where to get the XWiki properties file.
	 */
	@Inject
	private Environment environment;

	/**
	 * The logger to log.
	 */
	@Inject
	private Logger logger;

	@Override
	public void initialize() throws InitializationException {
		// Register the Commons Properties Configuration, looking for a
		// xwiki.properties file
		// in the XWiki path somewhere.
		URL xwikiPropertiesUrl = null;
		try {
			xwikiPropertiesUrl = this.environment.getResource(LEARNPAD_PROPERTIES_FILE);
			if (xwikiPropertiesUrl != null) {
				setConfiguration(new PropertiesConfiguration(xwikiPropertiesUrl));
			} else {
				// We use a debug logging level here since we consider it's ok
				// that there's no XWIKI_PROPERTIES_FILE
				// available, in which case default values are used.
				this.logger.debug("No configuration file [{}] found. Using default configuration values.",
						LEARNPAD_PROPERTIES_FILE);
			}
		} catch (Exception e) {
			// Note: if we cannot read the configuration file for any reason we
			// log a warning but continue since XWiki
			// will use default values for all configurable elements.
			this.logger.warn("Failed to load configuration file [{}]. Using default configuration values. "
					+ "Internal error [{}]", LEARNPAD_PROPERTIES_FILE, e.getMessage());
		}

		// If no Commons Properties Configuration has been set, use a default
		// empty Commons Configuration
		// implementation.
		if (xwikiPropertiesUrl == null) {
			setConfiguration(new BaseConfiguration());
		}
	}

	private String getRestPrefix(Component component) {
		String protocolKey = String.format("component.%s.protocol", component.toString().toLowerCase());
		String protocol = this.getProperty(protocolKey);
		String serverKey = String.format("component.%s.ip", component.toString().toLowerCase());
		String server = this.getProperty(serverKey);
		String portKey = String.format("component.%s.port", component.toString().toLowerCase());
		String port = this.getProperty(portKey);
		String prefixKey = String.format("component.%s.prefix", component.toString().toLowerCase());
		String prefix = this.getProperty(prefixKey);
		String restPrefix = String.format("%s://%s:%s%s", protocol, server, port, prefix);
		return restPrefix;
	}

	public String getRestPrefix(String component) {
		return this.getRestPrefix(Component.valueOf(component));
	}
}