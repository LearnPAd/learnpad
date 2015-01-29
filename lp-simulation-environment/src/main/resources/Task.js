function Task(address, taskid) {
	     var closeOnError = false;

	     Task.prototype.join = function() {
		 var location = "ws://" + address + "/tasks/" + taskid
		 activeTasks[taskid]._ws = new WebSocket(location);
		 activeTasks[taskid]._ws.onopen = this._onopen;
		 activeTasks[taskid]._ws.onmessage = this._onmessage;
		 activeTasks[taskid]._ws.onclose = this._onclose;
		 activeTasks[taskid]._ws.onerror = this._onerror;
	     }

	     Task.prototype.end = function() {
		 activeTasks[taskid]._onclose('');
	     }

	     Task.prototype.send = function(data) {
		 activeTasks[taskid]._ws.send(data);
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
			     activeTasks[taskid].send(JSON.stringify(values));
			     return false;
			 }
		     }});
	     }

	     Task.prototype._onclose = function(m) {
		 activeTasks.splice(activeTasks.indexOf(activeTasks[taskid]), 1);

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

	 var activeTasks = [];