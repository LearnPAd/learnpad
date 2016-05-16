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

// helper function to get the parameters from the URL
//
// see http://stackoverflow.com/questions/831030/how-to-get-get-request-parameters-in-javascript
function get(name) {
   if (name = (new RegExp('[?&]' + encodeURIComponent(name) +
                          '=([^&]*)')).exec(location.search))
      return decodeURIComponent(name[1]);
}

function processInstanciator(address) {

    var newProcessReceiver = {};

    var location = 'ws://' + address + '/process';

    newProcessReceiver._onopen = function() {
        // nothing to do
    };

    newProcessReceiver._onmessage = function(m) {
        var msg = JSON.parse(m.data);
        switch (msg.type) {
        case 'DATA':
            // get process id from url
            var processId = get('processid');
            // get corresponding process
            var process = msg.processes.filter(
                function(e) {
                    return e.id == processId;
                })[0];
            // create instanciate form
            processFormGenerate(process,
                                '#formPosition',
                                function(values) {
                                    newProcessReceiver.submitProcessData(
                                        processId,
                                        values
                                    );
                                    $('#formPosition').html('');
                                    window.location.href = 'http://' + address +
                                        '/ui?userid=' + get('userid');
                                });
            break;
        }
    };

    newProcessReceiver._onclose = function(m) {
        $('#formPosition').html('');
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

    newProcessReceiver.submitProcessData = function(id, values) {

        // SPECIAL MODIFICATIONS FOR DEMO PURPOSE ONLY
        if(id == 'mod.27772') {

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

    newProcessReceiver.ws = new WebSocket(location);
    newProcessReceiver.ws.onopen = newProcessReceiver._onopen;
    newProcessReceiver.ws.onmessage = newProcessReceiver._onmessage;
    newProcessReceiver.ws.onclose = newProcessReceiver._onclose;
    newProcessReceiver.ws.onerror = newProcessReceiver._onerror;

    return newProcessReceiver;
}
