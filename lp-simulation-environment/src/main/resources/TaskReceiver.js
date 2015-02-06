function TasksReceiver(address) {

    var finishOnError = false;
    var activeTasks = {};

    TasksReceiver.prototype.init = function(uiid) {

	var location = "ws://" + address + "/ui/" + uiid;
	taskReceiver._ws = new WebSocket(location);
	taskReceiver._ws.onopen = this._onopen;
	taskReceiver._ws.onmessage = this._onmessage;
	taskReceiver._ws.onclose = this._onclose;
	taskReceiver._ws.onerror = this._onerror;
    }

    TasksReceiver.prototype.finish = function() {
	$('#finished').removeClass('hidden');
    }

    TasksReceiver.prototype._onopen = function() {
	$('#connect').remove();
    }

    TasksReceiver.prototype._onmessage = function(m) {
	var msg = JSON.parse(m.data);
	switch(msg.type) {

	    case "FINISHED":
		taskReceiver.finish();
		break;

	    case "ADDTASK":
		var newTask = new Task(address, msg.taskid);
		activeTasks[msg.taskid] = newTask;
		newTask.join();
		break;

	    case "DELTASK":
		// may be undefined if task was closed from another ui
		activeTasks[msg.taskid].end();
                delete activeTasks[msg.taskid];
		break;
	}
    }

    TasksReceiver.prototype._onclose = function(m) {
	delete taskReceiver._ws;
	if(finishOnError){
	    alert("The following error occurred: " + m.reason + " (" + m.code +")");
	}
    }

    TasksReceiver.prototype._onerror = function(e) {
	finishOnError = true;
    }
}
