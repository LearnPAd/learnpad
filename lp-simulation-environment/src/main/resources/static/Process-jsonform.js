'use strict';

function processFormGenerate(process, formElement, callback) {
    $(formElement).html('');
    $(formElement).html('<form></form>');
    $(formElement + ' > form').jsonForm({
        schema: JSON.parse(process.form).schema,
        form: JSON.parse(process.form).form,
        onSubmit: function(errors, values) {
            if (!errors) {
                callback(JSON.stringify(values));
            }
        }});
}
