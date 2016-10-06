package eu.learnpad.simulator.utils;

/*
 * #%L
 * LearnPAd Simulator
 * %%
 * Copyright (C) 2014 - 2016 Linagora
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * This class provides a singleton for handling simulator properties
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class SimulatorProperties {

	private final static String PROPS_FILE_NAME = "simulator.properties";
	private final static String OPTIONAL_PROPS_PATH = "./" + PROPS_FILE_NAME;

	public final static Properties props = new Properties();

	public final static String PROP_ADDRESS = "address";
	public final static String PROP_GLIMPSE_SERVER = "glimpse_server";
	public final static String PLATFORM_ADDRESS = "platform_address";

	public final static String ROBOT_TYPE = "robot_type";

	public static enum ROBOT_TYPE_VALUE {
		none, simple, markov
	}

	static {

		// load default properties
		try {

			InputStream defaultProps = SimulatorProperties.class
					.getClassLoader().getResourceAsStream(PROPS_FILE_NAME);
			props.load(defaultProps);
			defaultProps.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new RuntimeException("Could not find default properties file");
		}

		// try to load optional properties file

		File f = new File(OPTIONAL_PROPS_PATH);
		if (f.exists() && !f.isDirectory()) {
			try {
				FileInputStream file = new FileInputStream(OPTIONAL_PROPS_PATH);
				props.load(file);
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// ROBOT_TYPE_VALUE are the only valid values for the ROBOT_TYPE property
		try {
			ROBOT_TYPE_VALUE.valueOf(props.getProperty(ROBOT_TYPE));
		} catch (NullPointerException e) {
			throw new RuntimeException(
					"Missing mandatory value for property " + ROBOT_TYPE + " (valid values: "
							+ Arrays.asList(ROBOT_TYPE_VALUE.values())
							+ " )");
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Invalid value for property " + ROBOT_TYPE + ": " + props.getProperty(ROBOT_TYPE)
					+ " (valid values: " + Arrays.asList(ROBOT_TYPE_VALUE.values()) + " )");
		}
	}
}
