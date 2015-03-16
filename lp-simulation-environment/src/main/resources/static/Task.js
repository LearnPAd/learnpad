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
function task(address, taskid, user) {

    var newTask = {};
    newTask.closeOnError = false;
    newTask.ws = {};

    newTask.join = function() {
        var location = 'ws://' + address + '/tasks/' + taskid
        this.ws = new WebSocket(location);
        this.ws.onopen = newTask._onopen;
        this.ws.onmessage = newTask._onmessage;
        this.ws.onclose = newTask._onclose;
        this.ws.onerror = newTask._onerror;
    };

    newTask.end = function() {
        $('#taskcontainer' + taskid + ' .collapse').
            filter('.in').collapse('hide');
        $('#taskFormDiv' + taskid).remove();
        $('#taskcontainer' + taskid).addClass('disabled');
        this.ws.onclose('');
    };

    newTask.send = function(data) {
        this.ws.send(JSON.stringify(data));
    };

    newTask._onopen = function() {
        // send subscription msg indicating user
        newTask.send({ 'type' : 'SUBSCRIBE', 'user' : user})
    };

    newTask._onmessage = function(m) {
        var data = JSON.parse(m.data);

        switch (data.type) {

        case 'TASKDESC':

            if (!$('#processcontainer' + data.processid).length) {
                // create new tab for new process
                $('#taskstable').append(
                    '<li role="presentation"><a data-toggle="tab" href="#processcontainer' +
                        data.processid + '">' + data.processname +
                        '</a></li>'
                );
                var tasksDiv = $('#tasks');
                var processDiv = document.createElement('div');
                processDiv.id = 'processcontainer' + data.processid;
                processDiv.className = 'tab-pane';
                tasksDiv.append(processDiv);

                // check if it is the first tab
                // (in this case we make it open by default)
                if ($('#taskstable li').length == 1) {
                    $('#taskstable li a:first').tab('show');
                }

                // add the process diagram
                $(processDiv).append(
                '<div class="panel-group diagram" id="accordion' +
                    data.processid +
                    '" role="tablist" aria-multiselectable="true">' +
                    '<div class="panel panel-default">' +
                    '<div class="panel-heading" role="tab" id="diagramHeading' + taskid +
                    '"><h4 class="panel-title">' +
                    '<a class="collapsed" data-toggle="collapse" data-parent="#accordion' + taskid +
                    '" href="#diagramCollapse' + taskid +
                    '" aria-expanded="true" aria-controls="diagramCollapse' + taskid +
                    '">See Process Diagram' + '</a>' + '</h4>' + '</div>' +
                    '<div id="diagramCollapse' + taskid +
                    '" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="diagramHeading' + taskid +
                    '"><div class="panel-body" style="overflow: scroll;width: 100%;">' +
                    '<img class="diagramImg" src="diagram/processinstance/' +
                    data.processid + '/' + taskid + '"></img>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>'
                );
            }

            var taskDiv = document.createElement('div');
            taskDiv.id = 'taskcontainer' + taskid;
            taskDiv.innerHTML = '<p id="taskdata' + taskid +
                '"></p><div id="taskFormDiv' + taskid + '"></div><hr>';
            $('#accordion' + data.processid).before(taskDiv);

            $('#taskdata' + taskid).html('<h4>' + data.description + '</h4>');

            $('#processcontainer' + data.processid + ' .diagramImg').attr(
                'src',
                'diagram/processinstance/' + data.processid + '/' + taskid
            );

            // yes `this` is the websocket in this context... js FTW!
            var ws = this;
            taskFormGenerate(
                taskid,
                data,
                'taskFormDiv' + taskid,
                'taskForm' + taskid,
                function(values) {
                    newTask.send({'type': 'SUBMIT', 'values': values});
                    $('#taskForm' + taskid).html('');
                }
            );

            $('html, body').animate({
                scrollTop: ($('#taskdata' + taskid).offset().top - 70)
            }, 'fast');
            break;

        case 'VALIDATED':
            $('#tasknotif' + taskid).remove();
            taskDiv = $('#taskFormDiv' + taskid).before(
                '<div id="tasknotif' +
                    taskid +
                    '" class="alert alert-success" role="alert">Great, your submission matched an expected answer.</div>'
            );
            break;

        case 'OTHER_VALIDATED':
            $('#tasknotif' + taskid).remove();
            taskDiv = $('#taskFormDiv' + taskid).before(
                '<div id="tasknotif' +
                    taskid +
                    '" class="alert alert-info" role="alert">Another user completed the task</div>'
            );
            break;

        case 'RESUBMIT':
            $('#tasknotif' + taskid).remove();
            taskDiv = $('#taskFormDiv' + taskid).before(
                '<div id="tasknotif' +
                    taskid +
                    '" class="alert alert-danger" role="alert">Sorry, your submission did not match expected answer(s). Please try again.</div>'
            );

            var ws = this;
            taskFormGenerate(
                taskid,
                data,
                'taskFormDiv' + taskid,
                'taskForm' + taskid,
                function(values) {
                    newTask.send({'type': 'SUBMIT', 'values': values});
                    $('#taskFormDiv' + taskid).html('');
                }
            );
            $('html, body').scrollTop($('#tasknotif' + taskid).offset().top - 70);
            break;

        case 'ERROR':
            $('#tasknotif' + taskid).remove();
            taskDiv = $('#taskFormDiv' + taskid).before(
                '<div id="tasknotif' +
                    taskid +
                    '" class="alert alert-warning" role="alert">Hum... something weird happened, sorry about that</div>'
            );
            break;

        }
    };

    newTask._onclose = function(m) {
        if (this.closeOnError) {
            alert('The following error occurred: ' +
                  m.reason +
                  ' (' + m.code + ')'
                 );
        }
    };

    newTask._onerror = function(e) {
        closeOnError = true;
    };

    return newTask;
}

