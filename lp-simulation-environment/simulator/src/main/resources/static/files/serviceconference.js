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
var names = {
    "offEdiuzia":"UFFICIO EDILIZIA",
    "offPorto":"UFFICIO PORTO",
    "offAmb":"Ufficio AMBIANTE",
    "offAtt":"UFFICIO ATTIVITA' PRODDUTTIVE",
    "offPolizia":"POLIZIA MUNICIPALE",
    "offTrib":"UFFICIO TRIBUTI",
    "offPat":"UFFICIO PATRIMONIO",
    "offSind":"SINDACO - autorità di pubblica sicurezza",
    "offARPAM":"ARPAM",
    "offServIgiene":"SERVIZIO IGIENE E SANITA' PUBBLICA",
    "offDepIgiene":"DEPARTIMENTO IGIENE ALIMENTI E NUTRIZIONE",
    "offPrev":"DIPARTIMENTO DI PREVENZIONE E SICUREZZA NEI LUOGHI DI LAVORO",
    "offVVFF":"VVFF - DIPARTIMENTO DI PREVENZIONE INCENDI",
    "offAreaAmb":"AREA AMBIENTE",
    "offAreaEco":"AREA ECOLOGIA -&gt; emissioni in atmosfera",
    "offVal":"REGIONE - PF VALUTAZIONI ED AUTORIZZAZIONI AMBIENTALI",
    "offGest":"ENTE GESTORE RETE FOGNARIA (ES. MULTISERVIZI SPA)",
    "offSoprint":"SOPRINTENDENZA per i beni artisici",
    "offFerrov":"RETE FERROVIARIA ITALIANA SPA (ex FF.SS)",
    "offANAS":"ANAS",
    "offQUEST":"QUESTURA",
    "offPREF":"PREFETTURA",
    "offISP":"ISPESL",
    "offDog":"UFFICIO DELLE DOGANE",
    "offCap":"CAPITANERIA DI PORTO",
};

for(var i = 0; i < 3; i++) {
    if(off1FeedbackCond[i] != "") {
        answers["${off1Role}"] = answers["${off1Role}"] + "<br/>Condition " + i + " : " + off1FeedbackCond[i];
    }
    if(off2FeedbackCond[i] != "") {
        answers["${off2Role}"] = answers["${off2Role}"] + "<br/>Condition " + i + " : " + off2FeedbackCond[i];
    }
}

for(i = 0; i < 3; i++) {
    if(off1FeedbackIntegration[i] != "") {
        answers["${off1Role}"] = answers["${off1Role}"] + "<br/>Required integration " + i + " : " + off1FeedbackIntegration[i];
    }
    if(off2FeedbackIntegration[i] != "") {
        answers["${off2Role}"] = answers["${off2Role}"] + "<br/>Required integration " + i + " : " + off2FeedbackIntegration[i];
    }
}

for (var prop in answers) {
    if (answers.hasOwnProperty(prop)) {
        if(answers[prop] && answers[prop].length){
            $("._answers").append("<p><b>" + names[prop] + "</b>:<br/> " + answers[prop] + "</p>");
        }
    }
}
