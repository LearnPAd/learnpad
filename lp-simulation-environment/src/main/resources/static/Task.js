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

    function prettyDateFormat(time) {
        var duration = new Date(time);
        var hh = duration.getUTCHours();
        var mm = duration.getUTCMinutes();
        var ss = duration.getSeconds();
        if (hh < 10) {hh = '0' + hh;}
        if (mm < 10) {mm = '0' + mm;}
        if (ss < 10) {ss = '0' + ss;}
        return hh + ':' + mm + ':' + ss;
    }

    newTask._onmessage = function(m) {
        var data = JSON.parse(m.data);

        switch (data.type) {

        case 'TASKDESC':
            // memorize process id
            this.processid = data.processid;

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

                var processMainDiv = document.createElement('div');
                processMainDiv.id = 'processmain' + data.processid;
                processMainDiv.className = 'col-sm-8';
                processDiv.appendChild(processMainDiv);

                var processSideDiv = document.createElement('div');
                processSideDiv.id = 'processside' + data.processid;
                processSideDiv.className = 'col-sm-4';
                processDiv.appendChild(processSideDiv);

                users(user).setUserList('processside' + data.processid);

                // add gamification panel to side div
                var scoreHelpText = "Your score is calculated based on how well you perform each task.<p>Each successfully completed task gives you points based of your number of attempts and how long did you take regarding the expected time.<p>Faster completion and less mistakes will award more points.";
                $('#processside' + data.processid).append(
                    '<div id="gamification' + data.processid +
                        '" class="game-panel panel panel-default">' +
                        '<div class="panel-body">' +
                        '<div><h4><em id="score' + data.processid +
                        '"></em> <span style="float:right" class="glyphicon glyphicon-question-sign"' +
                        ' data-toggle="popover" data-trigger="focus" tabindex="0" ' +
                        'data-placement="bottom" data-content="' + scoreHelpText +
                        '"></span></h4></div></div></div>'
                );
                // initialize help popover
                $('#gamification' + data.processid + ' [data-toggle="popover"]').popover({'html': true});

                // check if it is the first tab
                // (in this case we make it open by default)
                if ($('#taskstable li').length == 1) {
                    $('#taskstable li a:first').tab('show');
                }

                // add the process diagram
                $('#processmain' + data.processid).append(
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
            taskDiv.innerHTML = '<p id="taskdata' + taskid + '"></p>' +
                '<div id="taskDocsDiv' + taskid + '"></div>' +
                '<div><h4><em id="time' + taskid +
                '">Time on task:</em><p><em id="attempts' + taskid +
                '">Attempts: ' + data.nbattempts + '</em></h4></div>' +
                '</p><div id="taskFormDiv' + taskid + '"></div><hr>';

            $('#accordion' + data.processid).before(taskDiv);

            $('#taskdata' + taskid).html(
                '<h4>' + '<em>' + data.name + '</em></h4>' +
                    '<h4>' + data.description + '</h4>'
            );

            // add documents after the description
            var content =
                    '<div class="panel-group" id="documentsaccordion" role="tablist" aria-multiselectable="true">';
            for (var i = 0; i < data.documents.length; i++) {
                var doc = data.documents[i];

                content += '<div class="panel panel-default"><div class="panel-heading" role="tab" id="' +
                    'heading_' + taskid + '_' + doc.id +
                    '"><h4 class="panel-title">' +
                    '<a role="button" data-toggle="collapse" data-parent="#accordion" href="#' +
                    'collapse_' + taskid + '_' + doc.id +
                    '" aria-expanded="true" aria-controls="' +
                    'collapse_' + taskid + '_' + doc.id +
                    '"><span class="glyphicon glyphicon-file"></span>' +
                    doc.name + '</a></h4></div>';

                content += '<div id="' +
                    'collapse_' + taskid + '_' + doc.id +
                    '" class="panel-collapse collapse" role="tabpanel" aria-labelledby="' +
                    'heading_' + taskid + '_' + doc.id +
                    '"><div class="panel-body">';

                content += '<table class="table">';

                for (var j = 0; j < doc.fields.length; j++) {
                    content += '<tr><td>' + doc.fields[j].name + '</td><td>' +
                        doc.fields[j].value + '</td></tr>';
                }

                content += '</table>';

                content += '</div></div></div>';
            }
            content += '</div>';
            $('#taskDocsDiv' + taskid).html(content);

            // add timer
            newTask.timer = setInterval(function() {
                var currentTime = new Date().getTime() - data.startingtime;
                var style = '';
                if (currentTime > data.expectedtime) {
                   style = ' style="color:red"';
                }
                $('#time' + taskid).html(
                    'Time on task: <span' + style + '>' +
                        prettyDateFormat(currentTime) +
                        '</span> / ' + prettyDateFormat(data.expectedtime));
            }, 1000);

            // update score
            $('#score' + data.processid).html(
                'Score: ' + data.totalscore
            );

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

            // update score
            $('#score' + this.processid).html(
                'Score: ' + data.totalscore
            );

            // stop timer
            clearInterval(newTask.timer);
            break;

        case 'OTHER_VALIDATED':
            $('#tasknotif' + taskid).remove();
            taskDiv = $('#taskFormDiv' + taskid).before(
                '<div id="tasknotif' +
                    taskid +
                    '" class="alert alert-info" role="alert">Another user completed the task</div>'
            );

            // stop timer
            clearInterval(newTask.timer);
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
            $('html, body').scrollTop($('#taskdata' + taskid).offset().top - 70);

            // update nb attempts infos
            $('#attempts' + taskid).html('Attempts: ' + data.nbattempts);
            break;

        case 'ERROR':
            $('#tasknotif' + taskid).remove();
            taskDiv = $('#taskFormDiv' + taskid).before(
                '<div id="tasknotif' +
                    taskid +
                    '" class="alert alert-warning" role="alert">Hum... something weird happened, sorry about that</div>'
            );

            // stop timer
            clearInterval(newTask.timer);
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
        this.closeOnError = true;
    };

    return newTask;
}

