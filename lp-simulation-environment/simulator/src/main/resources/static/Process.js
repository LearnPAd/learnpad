/*
 * #%L
 * LearnPAd Simulator
 * %%
 * Copyright (C) 2014 - 2015 Linagora
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
'use strict';

function processReceiver(address) {

    var newProcessReceiver = {};

    newProcessReceiver.processFinishOnError = false;
    newProcessReceiver.processData = {};

    var location = 'ws://' + address + '/process';

    newProcessReceiver._onopen = function() {
        // nothing to do
    };

    newProcessReceiver._onmessage = function(m) {
        var msg = JSON.parse(m.data);
        switch (msg.type) {
        case 'DATA':
            newProcessReceiver.processData = msg;
            newProcessReceiver.updateProcessList(msg);
            break;
        }
    };

    newProcessReceiver._onclose = function(m) {
        $('#formPosition').html('');
        $('#processTable').html('');
        $('#processInfo').html('Lost connection with server');

        newProcessReceiver.ws = null;
        if (newProcessReceiver.processFinishOnError) {
            alert('The following error occurred: ' +
                  m.reason +
                  ' (' + m.code + ')');
        }
    };

    newProcessReceiver._onerror = function(e) {
        newProcessReceiver.processFinishOnError = true;
    };

    newProcessReceiver.send = function(data) {
        newProcessReceiver.ws.send(data);
    };

    newProcessReceiver.instanciate = function(processId) {
        var process = newProcessReceiver.processData.processes.filter(
            function(e) {
                return e.id == processId;
            })[0];

        processFormGenerate(process,
                            '#formPosition',
                            function(values) {
                                newProcessReceiver.submitProcessData(
                                    processId,
                                    values
                                );
                                $('#formPosition').html('');
                                $('#processInfo').html('Process ' +
                                                       process.name +
                                                       ' instanciated');
                            });
    };

    newProcessReceiver.submitProcessData = function(id, values) {

        // SPECIAL MODIFICATIONS FOR DEMO PURPOSE ONLY
        if(id == 'mod.27772' || id == 'mod.21093' || id == 'mod.21262') {

            // un-stringify
            values = JSON.parse(values);

            if(values['case'] === '829-2015') {
                values['applicantName'] = 'Guiseppe Cappelletti';
                values['applicationCity'] = 'lpd:Ancona';
                values['applicationZone'] = 'lpd:Beach_Area_At_The_Sea';
                values['applicationPublicAdministration'] = 'lpd:SUAPSenigallia';
                values['applicationSubType'] = 'lpd:Restructuring';
                values['applicationSector'] = 'lpd:Building_Sector';
                values['applicationBusinessActivity'] = 'lpd:Receptive_Toursim_Activity';
                values['applicationDescription'] = 'Realization of a chalet on a beach area of Senigallia';
                values['applicationATECOCategory'] = 'lpd:MarineAndMountaineSummerCamps';
            } else {
                values['applicantName'] = 'Ottavio Nandi';
                values['applicationCity'] = 'lpd:San_Ginesio';
                values['applicationZone'] = 'lpd:Regional_Protected_Area_Unione_Montana_Monti_Azzurri';
                values['applicationPublicAdministration'] = 'lpd:SUAPMontiAzzurri';
                values['applicationSubType'] = 'lpd:Reactivation';
                values['applicationSector'] = 'lpd:Waste_Sector';
                values['applicationBusinessActivity'] = 'lpd:Industrial_Activitiy';
                values['applicationDescription'] = 'request for reneval of authorization of industrial waste water discharge in sewer - coffee machines factory';
                values['applicationATECOCategory'] = 'lpd:InstallationOfElectricalSystems';
            }

            // some default values
            values['off1Choice'] = 'off1Ok';
            values['off2Choice'] = 'off2Ok';

            values['off1FeedbackOpinionStatus'] = 's5';
            values['off2FeedbackOpinionStatus'] = 's5';

            values['off1FeedbackMotivation'] = '';
            values['off2FeedbackMotivation'] = '';

            values['off1FeedbackCond1'] = '';
            values['off1FeedbackCond2'] = '';
            values['off1FeedbackCond3'] = '';
            values['off2FeedbackCond1'] = '';
            values['off2FeedbackCond2'] = '';
            values['off2FeedbackCond3'] = '';
            values['off1FeedbackIntegration1'] = '';
            values['off1FeedbackIntegration2'] = '';
            values['off1FeedbackIntegration3'] = '';
            values['off2FeedbackIntegration1'] = '';
            values['off2FeedbackIntegration2'] = '';
            values['off2FeedbackIntegration3'] = '';

            values['answerOffEdiuzia'] = '';
            values['answerOffPorto'] = '';
            values['answerOffAmb'] = '';
            values['answerOffAtt'] = '';
            values['answerOffPolizia'] = '';
            values['answerOffTrib'] = '';
            values['answerOffPat'] = '';
            values['answerOffSoprint'] = '';
            values['answerOffDog'] = '';
            values['answerOffCap'] = '';

            if(values['case'] === '829-2015') {
                values['answerOffEdiuzia'] = 'parere favorevole condizionato dello Sportello Unico per l\'Edilizia - ammissibilità dell\'intervento su richiesta di permesso di costruire';
                values['answerOffPorto'] = 'parere positivo scritto e precedentemente depositato in ordine alla modifica del muretto';
                values['answerOffAmb'] = 'parere di compatibilità paesaggistica ai sensi dell\'art. 146 DLgs 42/2004 e smi';
                values['answerOffAtt'] = 'autorizzazione ai sensi dell\'art. 24 Reg. C.N. - parere favorevole in quanto la concessione demaniale rimane inalterata';
                values['answerOffPolizia'] = 'parere di conformità al codice della strada - nulla osta per quanto di competenza';
                values['answerOffTrib'] = 'precedentemente presentato conteggio oneri imposta pubblicitaria con cui si esenta l\'esercizio - avendo superficie < 5 mq - con la prescrizione che al termine dei lavori dovrà essere presentata nuova dichiarazione superfici ai fini della tassa rifiuti ';
                values['answerOffSoprint'] = 'parere ai sensi dell\'art. 146 DLgs 42/2004 e smi e art. 14-ter comma 3bis L. 241/1990 - silenzio assenso';
                values['answerOffDog'] = 'autorizzazione doganale ai sensi dell\'art. 19 DLgs 374/1990 precedentemente rilasciata - parere favorevole già espresso: non c\'è bisogno di ulteriori risposte né di intervenire nella conferenza di servizi';
                values['answerOffCap'] = 'informato solo per conoscenza - silenzio assenso';
            }

            if(values['case'] === '1118-2015') {
                values['answerOffEdiuzia'] = 'rilascio parere favorevole su presentazione SCIA';
                values['answerOffAmb'] = 'l\'intervento proposto è compatibile a livello paesaggistico ai sensi dell\'art. 146 DLgs 42/2004';
                values['answerOffAtt'] = 'espletata attività istruttoria - ok sui controlli formali e di merito';
                values['answerOffPat'] = 'NULLA OSTA quale Ente proprietario dell\'area - parere favorevole ai sensi dell\'art. 6 par. 2 contratto rep 28 27/12/2001 (scadenza 31/12/2019)';
                values['answerOffSoprint'] = 'parere ai sensi dell\'art. 146 DLgs 42/2004 e smi e art. 14-ter comma 3bis L. 241/1990 - silenzio assenso';
            }

            // re-stringify
            values = JSON.stringify(values);
        }

        newProcessReceiver.send(JSON.stringify(
            {
                type: 'INSTANCIATE',
                id: id,
                parameters: values
            }
        ));
    };

    newProcessReceiver.updateProcessList = function(msg) {
        for (var i = 0; i < msg.processes.length; i++) {
            var process = msg.processes[i];
            var content = '';
            content += '<tr id=\'process' + process.id + '\'>';
            content += '<td>' + process.name + '</td>';
            content += '<td>' + process.description + '</td>';
            content += '</tr>';
            $('#processTable tbody').append(content);

            // add event listener
            // (due to the way closures work in js (ie they dont)
            // we need to create a function which will be called
            // each time and explicitely pass the process)
            document.getElementById('process' + process.id).
                onclick = function(p) {
                    return function() {
                        newProcessReceiver.instanciate(p.id);
                    };
                }(process);
        }
    };

    newProcessReceiver.ws = new WebSocket(location);
    newProcessReceiver.ws.onopen = newProcessReceiver._onopen;
    newProcessReceiver.ws.onmessage = newProcessReceiver._onmessage;
    newProcessReceiver.ws.onclose = newProcessReceiver._onclose;
    newProcessReceiver.ws.onerror = newProcessReceiver._onerror;

    return newProcessReceiver;
}
