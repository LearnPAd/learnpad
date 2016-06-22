package eu.learnpad.simulator.mon.utils;

import java.util.Vector;

import eu.learnpad.simulator.mon.coverage.Activity;
import eu.learnpad.simulator.mon.coverage.Path;

public class ComputeLearnerScore {


	public static float absoluteSession(Activity[] activities) {

		float absoluteSession = 0;
		for (int i=0; i< activities.length; i++) {
			absoluteSession += activities[i].getWeight();			
		}
		return absoluteSession;
	}

	public static float learnerBP(Vector<Float> executedByUser) {
		float bp = 0;
		for (int i = 0; i<executedByUser.size(); i++){
			bp += executedByUser.get(i);
		}
		return bp;
	}
	
	public static float learnerRelativeBP(Vector<Path> executedByUser) {
		float relativeBP = 0;
		for (int i = 0; i<executedByUser.size(); i++) {
			relativeBP += executedByUser.get(i).getAbsoluteSessionScore();
		}
		return relativeBP;
	}
	
	public static float absoluteBP(Vector<Path> thePathOfTheBPMN) {
		float absoluteBP = 0;
		for(int i = 0; i<thePathOfTheBPMN.size(); i++) {
			absoluteBP += thePathOfTheBPMN.get(i).getAbsoluteSessionScore();
		}
		return absoluteBP;
	}
	
	public static float learnerGlobal(Vector<Float> theScoresOfTheBPMNExecutedByUser) {
		float global = 0;
		for (int i = 0; i<theScoresOfTheBPMNExecutedByUser.size(); i++){
			global += theScoresOfTheBPMNExecutedByUser.get(i);
		}
		return global;
	}
	
	public static float relativeGlobal(Vector<Float> relativeBPScoreExecutedByUser) {
		float relativeGlobal = 0;
		for (int i = 0; i<relativeBPScoreExecutedByUser.size(); i++) {
			relativeGlobal += relativeBPScoreExecutedByUser.get(i);
		}
		return relativeGlobal;
	}
	
	public static float learnerAbsoluteGlobal(Vector<Float> BPMNScoresExecutedByUser) {
		float absoluteGlobal = 0;
		for(int i = 0; i<BPMNScoresExecutedByUser.size(); i++) {
			absoluteGlobal += BPMNScoresExecutedByUser.get(i);
		}
		return absoluteGlobal;
	}

	public static float learnerRelativeGlobal(Vector<Float> learnerRelativeBPScores) {
		float relativeGlobal = 0;
		for(int i = 0; i<learnerRelativeBPScores.size(); i++) {
			relativeGlobal += learnerRelativeBPScores.get(i);
		}
		return relativeGlobal;
	}

	public static float BPCoverage(Vector<Path> pathsExecutedByLearner, int pathsCardinality) {
		return (100 * pathsExecutedByLearner.size()) / pathsCardinality; 
	}	
}
