'use strict';

function taskFormGenerate(taskid, data, formContainer, formId, callback) {
    $('#' + formContainer).html('<form id="' +
                        formId + '"  class="well"></form>');

    $('#' + formId).jsonForm({
        schema: data.form.schema,
        form: data.form.form,
        onSubmit: function(errors, values) {
            if (!errors) {
                callback(values);
            }
        }});
}

