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

        var tasksDiv = $('#tasks');

        var taskDiv = document.createElement('div');
        taskDiv.id = 'taskcontainer' + taskid;
        taskDiv.innerHTML = '<p id="taskdata' +
            taskid + '"></p><form id="taskform' +
            taskid + '"  class="well"></form><hr>';

        tasksDiv.append(taskDiv);
    };

    newTask._onmessage = function(m) {
        var data = JSON.parse(m.data);

        // yes `this` is the websocket in this context... js FTW!
        var ws = this;

        $('#taskdata' + taskid).html(data.description);
        $('#taskform' + taskid).jsonForm({
            schema: data.form.schema,
            form: data.form.form,
            onSubmit: function(errors, values) {
                if (!errors) {
                    ws.send(JSON.stringify(values));
                    return false;
                }
            }});
    };

    newTask._onclose = function(m) {
        $('#taskform' + taskid).remove();
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

