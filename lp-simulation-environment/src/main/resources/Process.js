'use strict';

var processFinishOnError = false;

var data = {};

function ProcessReceiver(address) {
    ProcessReceiver.prototype.init = function() {
        var location = 'ws://' + address + '/process';
        processReceiver._ws = new WebSocket(location);
        processReceiver._ws.onopen = this._onopen;
        processReceiver._ws.onmessage = this._onmessage;
        processReceiver._ws.onclose = this._onclose;
        processReceiver._ws.onerror = this._onerror;
    };

    ProcessReceiver.prototype._onopen = function() {
        // nothing to do
    };

    ProcessReceiver.prototype._onmessage = function(m) {
        var msg = JSON.parse(m.data);
        switch (msg.type) {
        case 'DATA':
            data = msg;
            processReceiver.updateProcessList(msg);
            break;
        }
    };

    ProcessReceiver.prototype._onclose = function(m) {
        $('#formPosition').html('');
        $('#processTable').html('');
        $('#processInfo').html('Lost connection with server');

        processReceiver._ws = null;
        if (processFinishOnError) {
            alert('The following error occurred: ' +
                  m.reason +
                  ' (' + m.code + ')');
        }
    };

    ProcessReceiver.prototype._onerror = function(e) {
        processFinishOnError = true;
    };

    ProcessReceiver.prototype.send = function(data) {
        processReceiver._ws.send(data);
    };

    ProcessReceiver.prototype.instanciate = function(processId) {
        var process = data.processes.filter(function(e) {
            return e.id == processId;
        })[0];

        processFormGenerate(process,
                            '#formPosition',
                            function(values) {
                                processReceiver.submitProcessData(
                                    processId,
                                    values
                                );
                                $('#formPosition').html('');
                                $('#processInfo').html('Process ' +
                                                       process.name +
                                                       ' instanciated');
                            });
    };

    ProcessReceiver.prototype.submitProcessData = function(id, values) {
        var data = {};
        data.id = id;
        data.parameters = values;
        data.routes = {
            'Tom' : ['user1'],
            'management' : ['user2'],
            'SUAP' : ['user1'],
            'otherOffice' : ['user2']
        };
        processReceiver.send(JSON.stringify(data));
    };

    ProcessReceiver.prototype.updateProcessList = function(msg) {
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
                    return function() {processReceiver.instanciate(p.id);};
                }(process);
        }
    };

}
