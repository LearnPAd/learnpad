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
function task(address, taskid, user, integratedMode) {
    if (typeof integratedMode == 'undefined') {
        integratedMode = false;
    }

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
            this.sessionid = data.sessionid;

            var taskDiv = document.createElement('div');
            taskDiv.id = 'taskcontainer' + taskid;
            taskDiv.innerHTML = '<p id="taskdata' + taskid + '"></p>' +
                '<div id="taskDocsDiv' + taskid + '"></div>' +
                '<div><h4><em id="time' + taskid +
                '">Time on task:</em><p><em id="attempts' + taskid +
                '">Attempts: ' + data.nbattempts + '</em></h4></div>' +
                '</p><div id="taskFormDiv' + taskid + '"></div><hr>';

            $('#accordion' + data.sessionid).before(taskDiv);

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
            $('#score' + data.sessionid).html(
                'Score: ' + data.totalscore
            );

            $('#processcontainer' + data.sessionid + ' .diagramImg').attr(
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

            // this element can cause page height change, so we need to
            // monitor it
            heightMon.monitor('#taskForm' + taskid);

            $('html, body').animate({
                scrollTop: ($('#taskdata' + taskid).offset().top - 70)
            }, 'fast');
            break;

        case 'VALIDATED':
            $('#tasknotif' + taskid).remove();
            taskDiv = $('#taskFormDiv' + taskid).before(
                '<div id="tasknotif' +
                    taskid +
                    '" class="alert alert-success" role="alert">Great, your submission matched an expected answer (task score ' + data.taskscore +'.)</div>'
            );

            // update score
            $('#score' + this.sessionid).html(
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

            // this element can cause page height change, so we need to
            // monitor it
            heightMon.monitor('#taskForm' + taskid);

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

        // some events can cause height change
        heightMon.checkForChangeNotification();
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

