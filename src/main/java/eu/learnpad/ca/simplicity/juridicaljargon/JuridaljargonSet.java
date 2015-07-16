package eu.learnpad.ca.simplicity.juridicaljargon;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"juridicaljargon"
})
@XmlRootElement(name = "juridaljargonSet")
public class JuridaljargonSet {

	protected List<Juridicaljargon> juridicaljargon;


	public List<Juridicaljargon> getJuridicaljargon() {
		if (juridicaljargon == null) {
			juridicaljargon = new ArrayList<Juridicaljargon>();
		}
		return this.juridicaljargon;
	}

	

}
