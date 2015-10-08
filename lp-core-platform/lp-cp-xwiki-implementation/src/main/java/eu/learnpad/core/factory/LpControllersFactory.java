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
package eu.learnpad.core.factory;

public abstract class LpControllersFactory {

		private static LpControllersFactory concreteFactory = null;
		
		protected LpControllersFactory(){		
		}
		
		public static synchronized LpControllersFactory getLpControllersFactory(){
			if ( concreteFactory == null ){
				String factoryLabel = System.getProperty("lp-core.factory");

				switch (SupportedConcreteFactories.valueOf(factoryLabel)) {
					case XWIKI :
						concreteFactory = new LpXwikiControllersFactory();
					break;
					default : 
						concreteFactory = new LpXwikiControllersFactory();
					break;
					
				}			
			}
			return concreteFactory;
		}
		
		public abstract eu.learnpad.cw.Controller createControllerCW(boolean isBridgeInterfaceLocal);

		public abstract eu.learnpad.me.Controller createControllerME(boolean isBridgeInterfaceLocal);
		
		public abstract eu.learnpad.qm.Controller createControllerQM(boolean isBridgeInterfaceLocal);
		
		public abstract eu.learnpad.or.Controller createControllerOR(boolean isBridgeInterfaceLocal);
		
		public abstract eu.learnpad.sim.Controller createControllerSIM(boolean isBridgeInterfaceLocal);
	
}
