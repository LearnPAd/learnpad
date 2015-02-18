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
        var data = {};
        data.id = id;
        data.parameters = values;
        newProcessReceiver.send(JSON.stringify(data));
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
