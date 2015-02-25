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
