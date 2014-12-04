package ch.fhnw.wiki;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Connector {

	/**
	 * 
	 * @param connector list of the Nodes to compare with the nameFrom
	 * @param taskNameRequest name of the task or gateway 
	 * @return The list of the links connected to the object
	 */
	public static String nextLinkCreator(NodeList connector, String taskNameRequest, String spaceName){

		//String nameClass = "instance=\"" + nameFromRequest + "\"";
		int arrayLength = connector.getLength();
		//String[] array = new String[arrayLength];

		// create xwiki pages for every file cool !
		//String classe;


		Element from;
		Element to;
		String pagina;
		String nextLink = "";
		//String previousLink ="";
		String nameFromFile;
		//String test ="";
		//boolean bool = true;
		for (int i=0; i<arrayLength;i++){


			//get the info from the other file
			Element elementConnector = (Element) connector.item(i);


			if (elementConnector.getAttributes().getNamedItem("class").toString().replaceAll("\\?", "").replaceAll("|","").replaceAll(":","").replaceAll("<", "").replaceAll(">", "").replaceAll("/", " ").equals("class=\"Subsequent\"")){

				//TODO it miss the *
				//	classe = connector.getAttributes().getNamedItem("class").toString().replaceFirst("class=\"", "").replaceAll("\"", "").replaceAll("\\?", "").replaceAll("|","").replaceAll(":","").replaceAll("<", "").replaceAll(">", "").replaceAll("/", " ");
				//array[i] = classe;

				//for(int h=1; h<connector.getChildNodes().getLength(); h++){
				//if (h==1){

				// item #1 represent the FROM tag name, item #3 represents the TO tag name
				from = (Element) elementConnector.getChildNodes().item(1);
				to = (Element) elementConnector.getChildNodes().item(3);
				nameFromFile = from.getAttributes().getNamedItem("instance").toString().replaceFirst("instance=\"", "").replaceAll("\"", "").replaceAll("\\?", "").replaceAll("|","").replaceAll(":","").replaceAll("<", "").replaceAll(">", "").replaceAll("/", " ");
				// item #35 represents the denomination of the arrow
				Element textAnswer = (Element) elementConnector.getChildNodes().item(35);

				String linkMail="";

				if(taskNameRequest.replaceAll("\\?", "").equals(nameFromFile)){

					// if we are in a gateway the possible choices will be shown here
					if(textAnswer.getAttributes().getNamedItem("name").toString().equals("name=\"Denomination\"") && from.getAttributes().getNamedItem("class").toString().equals("class=\"Exclusive Gateway\"")){

						nextLink= nextLink + ""
								+ "" //this was the missing |
								+ "<br/>" ;
					}
					pagina = to.getAttributes().getNamedItem("instance").toString().replaceFirst("instance=\"", "").replaceAll("\"", "").replaceAll("\\?", "").replaceAll("|","").replaceAll(":","").replaceAll("<", "").replaceAll(">", "").replaceAll("/", " ");

					linkMail = Connector.userChanger(connector, taskNameRequest, pagina, spaceName, textAnswer);

					if(linkMail.equals("")){

						nextLink = nextLink + linkMail + ""
								+ "" //this was the missing |
								//	+ textAnswer.getTextContent() + " : [[" + pagina + ">>doc:" + pagina + "||title=\"Next Step\"]]" + "<br/>"; //TODO change in \n
								+ textAnswer.getTextContent().toString() + ": <button type=\"button\"><a href=\"" + pagina + "\">Next Activity ></a></button><br/>";
						// <button type="button"><a href="Details to be checked">Next Activity ></a></button>


						//System.out.println(linkMail + "| [[" + pagina + ">>doc:" + pagina + "||title=\"Next Step\"]]" );
					}else if(!linkMail.equals("")){

						nextLink = nextLink + ""
								+ "" //this was the missing |
								+ " " + linkMail + "\n";

						//System.out.println("HONEYPOT");
					}


				}


				/*
				//TODO BIG ISSUE /////////////////////
				prova = to.getAttributes().getNamedItem("instance").toString().replaceFirst("instance=\"", "").replaceAll("\"", "").replaceAll("\\?", "").replaceAll("|","").replaceAll(":","").replaceAll("<", "").replaceAll(">", "").replaceAll("/", " ");

				if(nameFrom.equals(prova)){
				pagina = from.getAttributes().getNamedItem("instance").toString().replaceFirst("instance=\"", "").replaceAll("\"", "").replaceAll("\\?", "").replaceAll("|","").replaceAll(":","").replaceAll("<", "").replaceAll(">", "").replaceAll("/", " ");
				System.out.println("Link to the Previous Step :\n[[" + pagina + ">>doc:" + pagina + "||title=\"Previous Step\"]]");
				previousLink = previousLink + "Link to the Previous Step :\n[[" + pagina + ">>doc:" + pagina + "||title=\"Previous Step\"]]" + "\n\n";
				}
				 */
				//	h++;
				//}
				//else {
				//	h++;	
				//}
				//}
			}
		}


		return nextLink /* + previousLink */ ;
	}



	public static String userChanger(NodeList connector, String taskNameRequest, String pagina, String spaceName, Element textAnswer){

		String taskNameClass = "instance=\"" + taskNameRequest + "\"";
		//int arrayLength = connector.getLength();

		Element elemento1;
		Element from1;
		Element to1;

		Element elemento2;
		Element from2;
		Element to2;
		Element elemento3;
		Element from3;
		Element to3;
		int l=0;
		String newUser ="";

		int connectorLength = connector.getLength();

		for(int j=0; j<connectorLength; j++){
			for(int f=0; f<connectorLength; f++){
				for(int t=0; t<connectorLength; t++){



					elemento1 = (Element) connector.item(j);
					from1 = (Element) elemento1.getChildNodes().item(1);
					to1 = (Element) elemento1.getChildNodes().item(3);

					//nameFromFile = from1.getAttributes().getNamedItem("instance").toString().replaceFirst("instance=\"", "").replaceAll("\"", "").replaceAll("\\?", "").replaceAll("|","").replaceAll(":","").replaceAll("<", "").replaceAll(">", "").replaceAll("/", " ");

					elemento2 = (Element) connector.item(f);
					from2 = (Element) elemento2.getChildNodes().item(1);
					to2 = (Element) elemento2.getChildNodes().item(3);

					elemento3 = (Element) connector.item(t);
					from3 = (Element) elemento3.getChildNodes().item(1);
					to3 = (Element) elemento3.getChildNodes().item(3);
					//description3= (Element) elemento3.getChildNodes().item(5);

					// insert text after this IF when you want to change user after a task
					if (from1.getAttributes().getNamedItem("instance").toString().equals(taskNameClass)
							&& to2.getAttributes().getNamedItem("instance").toString().equals("instance=\"" + pagina + "\"")
							&& from3.getAttributes().getNamedItem("instance").toString().equals("instance=\"" + pagina + "\"")
							&& !from3.getAttributes().getNamedItem("instance").toString().equals(taskNameClass)
							&& from1.getAttributes().getNamedItem("instance").toString().equals(from2.getAttributes().getNamedItem("instance").toString())
							&& from3.getAttributes().getNamedItem("instance").toString().equals(to2.getAttributes().getNamedItem("instance").toString())
							&& to1.getAttributes().getNamedItem("class").toString().equals("class=\"Lane\"")
							&& to3.getAttributes().getNamedItem("class").toString().equals("class=\"Lane\"")
							&& elemento2.getAttributes().getNamedItem("class").toString().equals("class=\"Subsequent\"")
							&& elemento1.getAttributes().getNamedItem("class").toString().equals("class=\"Is inside\"")
							&& elemento3.getAttributes().getNamedItem("class").toString().equals("class=\"Is inside\"")
							&& !to1.getAttributes().getNamedItem("instance").toString().equals(to3.getAttributes().getNamedItem("instance").toString())

							//&& to2.getAttributes().getNamedItem("class").toString().equals("class=\"Task\"")
							//&& from1.getAttributes().getNamedItem("instance").toString().equals(from2.getAttributes().getNamedItem("instance").toString())
							//&& from3.getAttributes().getNamedItem("instance").toString().equals(from2.getAttributes().getNamedItem("instance").toString())
							){


						if (l==0){
							// if l==0 it means that this is the first time 
							//test = "mailto: " + to3.getAttributes().getNamedItem("instance").toString().replaceAll("instance=\"", "").replaceAll("\"", "");
							newUser = "{{/html}}\n" +

									"#if(\"$!{request.action}\" == 'send_mail' && \"$!{request.lastname}\" == '') \n"+
									"## Check submission and honey pot.\n"+
									"#set($users = $xwiki.rightsmanager.usersApi.allUsersNames)\n"+ 
									"#foreach($user in $users)\n"+ 
									"#if($xwiki.getUser($user).isUserInGroup(\"XWiki." + to3.getAttributes().getNamedItem("instance").toString().replaceAll("instance=\"", "").replaceAll("\"", "") + "\"))\n"+ 
									"#set($email = $xwiki.getUser($user).getEmail())\n"+
									"#set($result = $xwiki.mailsender.sendTextMessage(\"no-reply@xwiki.com\", $email, \"IT'S YOUR TIME TO DO THE JOB\", \"Take care of this task: http://localhost:8080/xwiki/bin/view/" + spaceName + "/" + from3.getAttributes().getNamedItem("instance").toString().replaceAll("instance=\"", "").replaceAll("\"", "").replaceAll(" ", "+") +"\nBest Regards.\"))\n"+
									"#if($result == -1)\n"+
									"{{error}}A problem occurred while sending the mail.{{/error}}\n"+
									"#else\n"+
									"{{info}}Everything went OK.{{/info}}\n"+
									"#end\n"+
									"#end\n"+
									"#end\n"+

									"#end\n\n"+

									"{{html wiki=true}}\n"+

									"<form action=\"\" method=\"post\">\n"+
									"<input name=\"action\" value=\"send_mail\" type=\"hidden\" />\n"+
									"## this is a honey-pot hidden text field hat hopefully robots will not leave empty\n"+
									"<div class=\"hidden\">\n"+
									"<input name=\"lastname\" type=\"text\" />\n"+ 
									"</div>\n"+
									"<fieldset>\n"+
									"</fieldset>\n"+
									"<div>\n"+
									textAnswer.getTextContent() + ": <span class=\"buttonwrapper\"><input type=\"submit\" class=\"button\" value=\"" + pagina + "\" /></span>\n"+
									"</div>\n"+
									"</form>\n" +

									"\n#if($xwiki.hasAccessLevel('admin')) " +
									"\nFOR ADMIN ONLY:" + textAnswer.getTextContent() + " : [[" + pagina + ">>doc:" + pagina + "||title=\"Next Step\"]]" +
									"\n#end" +

									"\n{{/html}}\n\n";


							;


						}else{
							//test = test + "\n" + "mailto:" + to3.getAttributes().getNamedItem("instance").toString().replaceAll("instance=\"", "").replaceAll("\"", "");
							newUser = newUser + "{{/html}}\n" +

									"#if(\"$!{request.action}\" == 'send_mail' && \"$!{request.lastname}\" == '') \n"+
									"## Check submission and honey pot.\n"+
									"#set($users = $xwiki.rightsmanager.usersApi.allUsersNames)\n"+ 
									"#foreach($user in $users)\n"+ 
									"#if($xwiki.getUser($user).isUserInGroup(\"XWiki." + to3.getAttributes().getNamedItem("instance").toString().replaceAll("instance=\"", "").replaceAll("\"", "") + "\"))\n"+ 
									"#set($email = $xwiki.getUser($user).getEmail())\n"+
									"#set($result = $xwiki.mailsender.sendTextMessage(\"no-reply@xwiki.com\", $email, \"IT'S YOUR TIME TO DO THE JOB\", \"Take care of this task: " + from3.getAttributes().getNamedItem("instance").toString().replaceAll("instance=\"", "").replaceAll("\"", "") +"\nBest Regards.\"))\n"+
									"#if($result == -1)\n"+
									"{{error}}A problem occurred while sending the mail.{{/error}}\n"+
									"#else\n"+
									"{{info}}Everything went OK.{{/info}}\n"+
									"#end\n"+
									"#end\n"+
									"#end\n"+

									"#end\n\n"+

									"{{html wiki=true}}\n"+

									"<form action=\"\" method=\"post\">\n"+
									"<input name=\"action\" value=\"send_mail\" type=\"hidden\" />\n"+
									"## this is a honey-pot hidden text field hat hopefully robots will not leave empty\n"+
									"<div class=\"hidden\">\n"+
									"<input name=\"lastname\" type=\"text\" />\n"+ 
									"</div>\n"+
									"<fieldset>\n"+
									"</fieldset>\n"+
									"<div>\n"+
									"<span class=\"buttonwrapper\"><input type=\"submit\" class=\"button\" value=\"" + pagina + "\" /></span>\n"+
									"</div>\n"+
									"</form>\n" +

									"\n#if($xwiki.hasAccessLevel('admin')) " +
									"\nFOR ADMIN ONLY:" + textAnswer.getTextContent() + " : [[" + pagina + ">>doc:" + pagina + "||title=\"Next Step\"]]" +
									"\n#end" +

									"\n{{/html}}\n\n"  ;
						}
						//it only prints at the end of the loop
						//System.out.println(test);
						l++;
						//j=f=t=connector.getLength()-1;
					}
				}
			}
		}
		//System.out.println("---" + test + "---");
		return newUser ;
	}



	/**
	public static String previousLinkCreator(NodeList instances, String nameFrom){


		int arrayLength = instances.getLength();
		String[] array = new String[arrayLength];

		// create xwiki pages for every file cool !
		String classe;


		Element from;
		Element to;
		String pagina;
		String previousLink ="";
		String prova;
		for (int i=0; i<arrayLength;i++){


			//get the info from the other file
			Element connector = (Element) instances.item(i);


			if (connector.getAttributes().getNamedItem("class").toString().replaceFirst("class=\"", "").replaceAll("\"", "").replaceAll("\\?", "").replaceAll("|","").replaceAll(":","").replaceAll("<", "").replaceAll(">", "").replaceAll("/", " ").equals("Subsequent")){
				//TODO it miss the *
				classe = connector.getAttributes().getNamedItem("class").toString().replaceFirst("class=\"", "").replaceAll("\"", "").replaceAll("\\?", "").replaceAll("|","").replaceAll(":","").replaceAll("<", "").replaceAll(">", "").replaceAll("/", " ");
				array[i] = classe;

				for(int h=1; h<connector.getChildNodes().getLength(); h++){
					if (h==1){

						from = (Element) connector.getChildNodes().item(h);
						to = (Element) connector.getChildNodes().item(h+2);

						//TODO BIG ISSUE /////////////////////
						prova = to.getAttributes().getNamedItem("instance").toString().replaceFirst("instance=\"", "").replaceAll("\"", "").replaceAll("\\?", "").replaceAll("|","").replaceAll(":","").replaceAll("<", "").replaceAll(">", "").replaceAll("/", " ");

						if(nameFrom.equals(prova)){
							pagina = from.getAttributes().getNamedItem("instance").toString().replaceFirst("instance=\"", "").replaceAll("\"", "").replaceAll("\\?", "").replaceAll("|","").replaceAll(":","").replaceAll("<", "").replaceAll(">", "").replaceAll("/", " ");
							System.out.println("[[" + pagina + ">>doc:" + pagina + "||title=\"Previous Step\"]]");
							previousLink = previousLink + "[[" + pagina + ">>doc:" + pagina + "||title=\"Previous Step\"]]" + "\n";
						}
						h++;
					}
					else {
						h++;	
					}
				}
			}
		}
		return previousLink;
	}

	 */



}
