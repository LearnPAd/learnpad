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

        // yes `this` is the websocket in this context... js FTW!
        var ws = this;
        taskFormGenerate(taskid, data, '#tasks', function(values) {
            ws.send(JSON.stringify(values));
        });
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

