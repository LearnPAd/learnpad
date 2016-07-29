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
package eu.learnpad.core.impl.tests;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author gulyx
 */
public abstract class AbstractUnitTest {
    
	private SecureRandom random;
	
	protected AbstractUnitTest() {
	  this.random = new SecureRandom();
	}
	
	protected String randomId() {
		    return new BigInteger(130, random).toString(32);
	}
	
	protected int randomInt() {
	    return new BigInteger(10, random).intValue();
	}

	protected SecureRandom getRandomGenerator() {
	    return this.random;
	}

}