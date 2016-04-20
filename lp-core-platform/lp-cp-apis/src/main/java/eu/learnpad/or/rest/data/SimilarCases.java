/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.rest.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sandro.emmenegger
 */
@XmlRootElement
public class SimilarCases {

	List<SimilarCase> similarCases = null;

	public List<SimilarCase> getSimilarCases() {
		return similarCases;
	}

	@XmlElement(name = "similarCase")
	public void setSimilarCases(List<SimilarCase> similarCases) {
		this.similarCases = similarCases;
	}

	public void addAllSimilarCases(List<SimilarCase> similarCases) {
		similarCases().addAll(similarCases);
	}

	private List<SimilarCase> similarCases() {
		if (similarCases == null) {
			similarCases = new ArrayList<SimilarCase>();
		}
		return similarCases;
	}
}