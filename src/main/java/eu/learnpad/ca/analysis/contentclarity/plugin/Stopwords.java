package eu.learnpad.ca.analysis.contentclarity.plugin;

import java.util.ArrayList;
import java.util.List;


public class Stopwords {
	private List<String> stopwordsList;

	public boolean isStopword(String term){
		if (stopwordsList.contains(term)){
			return true;
		} else {
			return false;
		}
	}
	
	public List<String> getStopwords(){
		return stopwordsList;
	}
	
	public Stopwords(){
		stopwordsList = new ArrayList<String>();
		stopwordsList.add("ever");
		stopwordsList.add("every");
		stopwordsList.add("for");
		stopwordsList.add("from");
		stopwordsList.add("get");
		stopwordsList.add("got");
		stopwordsList.add("had");
		stopwordsList.add("has");
		stopwordsList.add("have");
		stopwordsList.add("he");
		stopwordsList.add("her");
		stopwordsList.add("hers");
		stopwordsList.add("him");
		stopwordsList.add("his");
		stopwordsList.add("how");
		stopwordsList.add("however");
		stopwordsList.add("i");
		stopwordsList.add("if");
		stopwordsList.add("in");
		stopwordsList.add("into");
		stopwordsList.add("is");
		stopwordsList.add("it");
		stopwordsList.add("its");
		stopwordsList.add("just");
		stopwordsList.add("least");
		stopwordsList.add("let");
		stopwordsList.add("like");
		stopwordsList.add("likely");
		stopwordsList.add("may");
		stopwordsList.add("me");
		stopwordsList.add("might");
		stopwordsList.add("most");
		stopwordsList.add("must");
		stopwordsList.add("my");
		stopwordsList.add("neither");
		stopwordsList.add("no");
		stopwordsList.add("nor");
		stopwordsList.add("not");
		stopwordsList.add("of");
		stopwordsList.add("off");
		stopwordsList.add("often");
		stopwordsList.add("on");
		stopwordsList.add("only");
		stopwordsList.add("or");
		stopwordsList.add("other");
		stopwordsList.add("our");
		stopwordsList.add("own");
		stopwordsList.add("rather");
		stopwordsList.add("said");
		stopwordsList.add("say");
		stopwordsList.add("says");
		stopwordsList.add("she");
		stopwordsList.add("should");
		stopwordsList.add("since");
		stopwordsList.add("so");
		stopwordsList.add("some");
		stopwordsList.add("than");
		stopwordsList.add("that");
		stopwordsList.add("the");
		stopwordsList.add("their");
		stopwordsList.add("them");
		stopwordsList.add("then");
		stopwordsList.add("there");
		stopwordsList.add("these");
		stopwordsList.add("they");
		stopwordsList.add("this");
		stopwordsList.add("tis");
		stopwordsList.add("to");
		stopwordsList.add("too");
		stopwordsList.add("twas");
		stopwordsList.add("us");
		stopwordsList.add("wants");
		stopwordsList.add("was");
		stopwordsList.add("we");
		stopwordsList.add("were");
		stopwordsList.add("what");
		stopwordsList.add("when");
		stopwordsList.add("where");
		stopwordsList.add("which");
		stopwordsList.add("while");
		stopwordsList.add("who");
		stopwordsList.add("whom");
		stopwordsList.add("why");
		stopwordsList.add("will");
		stopwordsList.add("with");
		stopwordsList.add("would");
		stopwordsList.add("yet");
		stopwordsList.add("you");
		stopwordsList.add("your");
		stopwordsList.add("a");
		stopwordsList.add("able" );
		stopwordsList.add("about" );
		stopwordsList.add("across" );
		stopwordsList.add("after");
		stopwordsList.add( "all" );
		stopwordsList.add("almost" );
		stopwordsList.add("also" );
		stopwordsList.add("am" );
		stopwordsList.add("among" );
		stopwordsList.add( "an" );
		stopwordsList.add("and" );
		stopwordsList.add("any" );
		stopwordsList.add("are" );
		stopwordsList.add("as" );
		stopwordsList.add( "at" );
		stopwordsList.add("be" );
		stopwordsList.add("because" );
		stopwordsList.add("been" );
		stopwordsList.add("but" );
		stopwordsList.add( "by" );
		stopwordsList.add("can" );
		stopwordsList.add("cannot" );
		stopwordsList.add("could" );
		stopwordsList.add("dear");
		stopwordsList.add("did" );
		stopwordsList.add("do" );
		stopwordsList.add("does" );
		stopwordsList.add("either" );
		stopwordsList.add("else");
	}

}
