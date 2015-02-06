function Task(address, taskid) {
    var closeOnError = false;
    var ws;

    Task.prototype.join = function() {
	var location = "ws://" + address + "/tasks/" + taskid
	ws = new WebSocket(location);
	ws.onopen = this._onopen;
	ws.onmessage = this._onmessage;
	ws.onclose = this._onclose;
	ws.onerror = this._onerror;
    }

    Task.prototype.end = function() {
	ws.onclose('');
    }

    Task.prototype.send = function(data) {
	ws.send(data);
    }

    Task.prototype._onopen = function(){

	var tasksDiv = $('#tasks');

	var taskDiv = document.createElement('div');
	taskDiv.id = 'taskcontainer'+taskid;
	taskDiv.innerHTML = '<p id="taskdata' + taskid + '"></p>\
	<form id="taskform'+ taskid + '"  class="well"></form>\
	<hr>';

	tasksDiv.append(taskDiv);
    }

    Task.prototype._onmessage = function(m) {
	var data = JSON.parse(m.data);

	$('#taskdata' + taskid).html(data.description);
	$('#taskform' + taskid).jsonForm({
	    schema: data.form.schema,
	    form: data.form.form,
	    onSubmit: function (errors, values) {
		if (!errors) {
		    ws.send(JSON.stringify(values));
		    return false;
		}
	    }});
    }

    Task.prototype._onclose = function(m) {
	$('#taskform' + taskid).remove();
	$('#taskcontainer' + taskid).addClass('disabled');

	if(closeOnError){
	    alert("The following error occurred: " + m.reason + " (" + m.code +")");
	}
    }

    Task.prototype._onerror = function(e) {
	closeOnError = true;
    }
}

