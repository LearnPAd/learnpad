'use strict';

function taskReceiver(address, user) {

    var newTaskReceiver = {}

    newTaskReceiver.finishOnError = false;
    newTaskReceiver.activeTasks = {};
    newTaskReceiver.user = user;


    newTaskReceiver._onopen = function() {
        $('#connect').remove();
        $('.userui').removeClass('hidden');
    };

    newTaskReceiver._onmessage = function(m) {
        var msg = JSON.parse(m.data);
        switch (msg.type) {

        case 'FINISHED':
            var containerDiv = $('#processcontainer' + msg.processid);
            var processFinished = document.createElement('p');
            processFinished.innerHTML = '<p>Process ' +
                msg.processid + ' finished.</p><hr>'
            containerDiv.append(processFinished);
            break;

        case 'ADDTASK':
            var newTask = task(address, msg.taskid, user);
            newTaskReceiver.activeTasks[msg.taskid] = newTask;
            newTask.join();
            break;

        case 'DELTASK':
            // may be undefined if task was closed from another ui
            newTaskReceiver.activeTasks[msg.taskid].end();
            delete newTaskReceiver.activeTasks[msg.taskid];
            break;
        }
    };

    newTaskReceiver._onclose = function(m) {
        Object.keys(newTaskReceiver.activeTasks).forEach(
            function(taskid) {
                newTaskReceiver.activeTasks[taskid].end();
                delete newTaskReceiver.activeTasks[taskid];
        });
        delete taskReceiver._ws;
        if (newTaskReceiver.finishOnError) {
            alert(
                'The following error occurred: ' +
                    m.reason +
                    ' (' + m.code + ')'
            );
        }
    };

    newTaskReceiver._onerror = function(e) {
        newTaskReceiver.finishOnError = true;
    };

    var location = 'ws://' + address + '/ui/' + user;
    newTaskReceiver._ws = new WebSocket(location);
    newTaskReceiver._ws.onopen = newTaskReceiver._onopen;
    newTaskReceiver._ws.onmessage = newTaskReceiver._onmessage;
    newTaskReceiver._ws.onclose = newTaskReceiver._onclose;
    newTaskReceiver._ws.onerror = newTaskReceiver._onerror;

    return newTaskReceiver;
}
