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
/**
 * This package contains the interfaces and data definitions related to the gamification
 * aspects of the LearnPAd platform
 *
 * Here is a sequence diagram (PlantUML syntax) illustrating some common uses of the
 * API. The interactions marked with (*) correspond to gamification API calls.
 *
 * <pre>{@code
 Civil_Servant -> Collaborative_Workspace: make edits
 Collaborative_Workspace -> Core_Platform: store edits count (*)
 Core_Platform -> Core_Platform: check certificates

 Civil_Servant -> Simulator: do simulation session
 Simulator -> Simulator: compute session score
 Simulator -> Core_Platform: store session score (*)

 Core_Platform -> Core_Platform: compute global score
 Core_Platform -> Core_Platform: store global score
 Core_Platform -> Core_Platform: check certificates

 Civil_Servant -> Collaborative_Workspace: open profile
 activate Civil_Servant
 activate Collaborative_Workspace
 group Parallel
 Collaborative_Workspace -> Core_Platform: request scores (*)
 activate Collaborative_Workspace
 Core_Platform --> Collaborative_Workspace: return scores
 deactivate Collaborative_Workspace
 else
 Collaborative_Workspace -> Core_Platform: request certificates (*)
 activate Collaborative_Workspace
 Core_Platform --> Collaborative_Workspace: return certificates
 deactivate Collaborative_Workspace
 end
 Collaborative_Workspace --> Civil_Servant: show profile
 deactivate Civil_Servant
 deactivate Collaborative_Workspace
 * }</pre>
 *
 * (you can use the following online service
 * <a href=http://www.plantuml.com/plantuml>http://www.plantuml.com/plantuml/</a>
 * to generate a graphical version of this sequence diagram)
 */
package eu.learnpad.gm;
