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

function taskReceiver(address, user, integratedMode, sessionid, platformAddress) {
    if (typeof integratedMode == 'undefined') {
        integratedMode = false;
    }

    var newTaskReceiver = {}

    newTaskReceiver.finishOnError = false;
    newTaskReceiver.activeTasks = {};
    newTaskReceiver.user = user;

    newTaskReceiver.nbTaskBySession = {};


    newTaskReceiver._onopen = function() {
        $('#connect').remove();
        $('.userui').removeClass('hidden');
    };

    newTaskReceiver._onmessage = function(m) {
        var msg = JSON.parse(m.data);
        if(sessionid != null && sessionid != msg.sessionid) {
            // ignore message
        } else {
            switch (msg.type) {

            case 'SESSION_STARTED':

                // create new tab for session
                $('#taskstable').append(
                    '<li role="presentation"><a data-toggle="tab" href="#processcontainer' +
                        msg.sessionid + '">' + msg.sessionname +
                        '</a></li>'
                );
                var tasksDiv = $('#tasks');
                var processDiv = document.createElement('div');
                processDiv.id = 'processcontainer' + msg.sessionid;
                processDiv.className = 'tab-pane';
                tasksDiv.append(processDiv);

                var processMainDiv = document.createElement('div');
                processMainDiv.id = 'processmain' + msg.sessionid;
                processMainDiv.className = 'col-sm-8';
                processDiv.appendChild(processMainDiv);

                var processSideDiv = document.createElement('div');
                processSideDiv.id = 'processside' + msg.sessionid;
                processSideDiv.className = 'col-sm-4';
                processDiv.appendChild(processSideDiv);

                if (!integratedMode) {
                    var processUserInfos = document.createElement('div');
                    processUserInfos.id = 'processuserinfos' +msg.sessionid;
                    processSideDiv.appendChild(processUserInfos);

                    users(address, user, msg.involvedusers, processUserInfos.id);
                }

                // add infobox with links for other users to join
                var infoLinks = '<div class="alert alert-info" role="alert">' +
                        'Other participants can join the session using the following link (when logged in):<p>';
                var url = 'http://' + platformAddress + '/xwiki/bin/view/LPUI/SimulationEnvironment?simulationid=' + msg.sessionid;
                infoLinks += '<a href="' + url + '">' + url + '</a><p>';
                infoLinks += '</div>';
                $('#processmain' + msg.sessionid).append(infoLinks);

                // add initial "no task" message
                $('#processmain' + msg.sessionid).append(
                    '<div id="notasknotif' +
                        msg.sessionid +
                        '" class="alert alert-info" role="alert">Waiting for other users to complete their tasks.</div>'
                );

                // add session chat container
                $('#processside' + msg.sessionid).append(
                    '<div id="sessionchat' + msg.sessionid + '"></div>'
                );
                sessionChat('#sessionchat' + msg.sessionid, address, user, msg.sessionid);

                // add gamification panel to side div
                var scoreHelpText = "Your score is calculated based on how well you perform each task.<p>Each successfully completed task gives you points based of your number of attempts and how long did you take regarding the expected time.<p>Faster completion and less mistakes will award more points.";
                $('#processside' + msg.sessionid).append(
                    '<div id="gamification' + msg.sessionid +
                        '" class="game-panel panel panel-default">' +
                        '<div class="panel-body">' +
                        '<div><h4><em id="score' + msg.sessionid +
                        '"></em> <span style="float:right" class="glyphicon glyphicon-question-sign"' +
                        ' data-toggle="popover" data-trigger="focus" tabindex="0" ' +
                        'data-placement="bottom" data-content="' + scoreHelpText +
                        '"></span></h4></div></div></div>'
                );
                // initialize help popover
                $('#gamification' + msg.sessionid + ' [data-toggle="popover"]').popover({'html': true});

                // this element can cause page height change, so we need to
                // monitor it
                heightMon.monitor('#gamification' + msg.sessionid + ' [data-toggle="popover"]');
                $('#gamification' + msg.sessionid + ' [data-toggle="popover"]').bind(
                    'transitionend', heightMon.checkForChangeNotification);

                // check if it is the first tab
                // (in this case we make it open by default)
                if ($('#taskstable li').length == 1) {
                    $('#taskstable li a:first').tab('show');
                }

                // add the process diagram container
                // We add an image of the initial process of the session,
                // it will be updated when we receive tasks
                $('#processmain' + msg.sessionid).append(
                    '<div class="panel-group diagram" id="accordion' +
                        msg.sessionid +
                        '" role="tablist" aria-multiselectable="true">' +
                        '<div class="panel panel-default">' +
                        '<div class="panel-heading" role="tab" id="diagramHeading' + msg.sessionid +
                        '"><h4 class="panel-title">' +
                        '<a class="collapsed" data-toggle="collapse" data-parent="#accordion' + msg.sessionid +
                        '" href="#diagramCollapse' + msg.sessionid +
                        '" aria-expanded="true" aria-controls="diagramCollapse' + msg.sessionid +
                        '">See Process Diagram' + '</a>' + '</h4>' + '</div>' +
                        '<div id="diagramCollapse' + msg.sessionid +
                        '" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="diagramHeading' + msg.sessionid +
                        '"><div class="panel-body" style="overflow: scroll;width: 100%;">' +
                        '<img class="diagramImg" src="diagram/process/' + msg.initialprocessdefinitionkey + '"></img>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>'
                );

                // this element can cause page height change, so we need to
                // monitor it
                heightMon.monitor('#accordion' + msg.sessionid);
                document.getElementById('accordion' + msg.sessionid).addEventListener(
                    "transitionend",
                    heightMon.checkForChangeNotification);
                break;
            case 'SESSION_FINISHED':
                var containerDiv = $('#processmain' + msg.sessionid);
                var processFinished = document.createElement('p');

                var totalScore = 0;

                var result = '<h4>Congratulations, you successfully completed the simulation</h4>';
                result += '<p>Session Score Summary:</p>';
                result += '<table class="detailed-score table table-striped table-condensed">';
                result += '<tr><th>Task</th><th>Score</th></tr>';
                // add task scores
                for(var aTask in msg.tasknames) {
                    result += '<tr><td>' + msg.tasknames[aTask] + '</td><td>' +
                        msg.taskscores[aTask] +'</td></tr>';

                    totalScore += msg.taskscores[aTask];
                }

                result += '<tr><td><i>Total session score</i></td><td><i>' + totalScore + '</i></td></tr>';
                result += '</table>';

                processFinished.innerHTML = result;

                containerDiv.append(processFinished);

                // remove process diagram
                $('#accordion' + msg.sessionid).remove();

                // remove "no active task" msg (if present)
                $('#notasknotif' + msg.sessionid).remove();

                break;

            case 'ADDTASK':

                if (!newTaskReceiver.nbTaskBySession.hasOwnProperty(msg.sessionid)) {
                    newTaskReceiver.nbTaskBySession[msg.sessionid] = 0;
                }
                newTaskReceiver.nbTaskBySession[msg.sessionid] += 1;

                var newTask = task(address, msg.taskid, user, integratedMode);
                newTaskReceiver.activeTasks[msg.taskid] = newTask;
                newTask.join();

                // remove "no active task" msg (if present)
                $('#notasknotif' + msg.sessionid).remove();

                break;

            case 'DELTASK':
                // may be undefined if task was closed from another ui
                newTaskReceiver.activeTasks[msg.taskid].end();
                delete newTaskReceiver.activeTasks[msg.taskid];

                // if there is no active task, display msg
                newTaskReceiver.nbTaskBySession[msg.sessionid] -= 1;
                if (newTaskReceiver.nbTaskBySession[msg.sessionid] == 0) {
                    $('#processcontainer' + msg.sessionid + ' .diagram').before(
                        '<div id="notasknotif' +
                            msg.sessionid +
                            '" class="alert alert-info" role="alert">Waiting for other users to complete their tasks.</div>'
                    );
                }

                break;
            }

        }
        // some events can cause height change
        heightMon.checkForChangeNotification();
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
