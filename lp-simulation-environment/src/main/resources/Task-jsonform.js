'use strict';

function taskFormGenerate(taskid, data, formElement, callback) {
    var tasksDiv = $(formElement);
    var taskDiv = document.createElement('div');
    taskDiv.id = 'taskcontainer' + taskid;
    taskDiv.innerHTML = '<p id="taskdata' +
        taskid + '"></p><form id="taskform' +
        taskid + '"  class="well"></form><hr>';

    tasksDiv.append(taskDiv);

    $('#taskdata' + taskid).html(data.description);
    $('#taskform' + taskid).jsonForm({
        schema: data.form.schema,
        form: data.form.form,
        onSubmit: function(errors, values) {
            if (!errors) {
                callback(values);
            }
        }});
}

