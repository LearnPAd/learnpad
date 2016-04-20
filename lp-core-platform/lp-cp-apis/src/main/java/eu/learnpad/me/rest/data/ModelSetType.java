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
package eu.learnpad.me.rest.data;

/*
 * Originally this definition was planned to support :
 * <ul>
 *  <li> ADOXX : model file in ADOXX XML </li>
 *  <li> MD : model file in MagicDraw XML </li>
 *  <li> LPZIP : From our internal documentation results that an LPZip should include : </li>
 *  <ul> 
 *    <li> Model file (ADOXX XML file or MagicDraw XML file): the name of the file is "<modelsetid>.adoxx" or "<modelsetid>.mdxml" </li>
 *    <li> BPMN files (one for each Business Process): the name of the file is "<modelid>.bpmn2" </li>
 *    <li> Image files (one for each model): the name of the file is "<modelid>.png" </li>
 *    <li> Image map files (one for each image): the name of the file is "<modelid>.map" </li>
 *  </ul>
 *    
 * However, currently this definition does not support LPZip explicitly.
 *
 * The two values of the enumeration should be considered as follow:
 * <ul>
 *  <li> ADOXX : it is actually an LPZip file which among the others it contains 
 *            the model in the XML for exported by ADOXX </li>
 *  <li> MD : it is actually an LPZip file which among the others it contains 
 *            the model in the XML for exported form MD </li>
 * </ul>
 *                       
 * TODO Modify this definition in order to explicitly support LPZip 
 */
public enum ModelSetType {
	ADOXX, MD
}