'use strict';

function taskFormGenerate(taskid, data, formContainer, formId, callback) {
    $('#' + formContainer).html('<form id="' +
                        formId + '"  class="well"></form>');

    $('#' + formId).jsonForm({
        schema: JSON.parse(data.form).schema,
        form: JSON.parse(data.form).form,
        onSubmit: function(errors, values) {
            if (!errors) {
                callback(JSON.stringify(values));
            }
        }});
}

