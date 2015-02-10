function task(address, taskid) {

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
        this.ws.onclose('');
    };

    newTask.send = function(data) {
        this.ws.send(data);
    };

    newTask._onopen = function() {
        // nothing to do
    };

    newTask._onmessage = function(m) {
        var data = JSON.parse(m.data);

        switch (data.type) {

        case 'TASKDESC':

            if (!$('#processcontainer' + data.processid).length) {
                // create new tab for new process
                $('#taskstable').append(
                    '<li role="presentation"><a data-toggle="tab" href="#processcontainer' +
                        data.processid + '">Process ' +
                        data.processid +
                        '</a></li>'
                );
                var tasksDiv = $('#tasks');
                var processDiv = document.createElement('div');
                processDiv.id = 'processcontainer' + data.processid;
                processDiv.className = 'tab-pane';
                tasksDiv.append(processDiv);

            }

            var processDiv = $('#processcontainer' + data.processid);
            var taskDiv = document.createElement('div');
            taskDiv.id = 'taskcontainer' + taskid;
            taskDiv.innerHTML = '<p id="taskdata' + taskid +
                '"></p><div id="taskFormDiv' + taskid + '"></div><hr>';
            processDiv.append(taskDiv);

            $('#taskdata' + taskid).html(data.description);

            // yes `this` is the websocket in this context... js FTW!
            var ws = this;
            taskFormGenerate(
                taskid,
                data,
                'taskFormDiv' + taskid,
                'taskForm' + taskid,
                function(values) {
                    ws.send(JSON.stringify(values));
                    $('#taskForm' + taskid).html('');
                }
            );
            break;

        case 'VALIDATED':
            $('#tasknotif' + taskid).remove();
            taskDiv = $('#taskdata' + taskid).after(
                '<div id="tasknotif' +
                    taskid +
                    '" class="alert alert-success" role="alert">Great, your submission matched an expected answer.</div>'
            );
            break;

        case 'RESUBMIT':
            $('#tasknotif' + taskid).remove();
            taskDiv = $('#taskdata' + taskid).after(
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
                    ws.send(JSON.stringify(values));
                    $('#taskFormDiv' + taskid).html('');
                }
            );
            break;
        }
    };

    newTask._onclose = function(m) {
        $('#taskFormDiv' + taskid).remove();
        $('#taskcontainer' + taskid).addClass('disabled');
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

