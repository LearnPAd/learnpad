function processFormGenerate(process, formElement, callback) {
    $(formElement).html('');
    $(formElement).html('<form></form>');
    $(formElement + ' > form').jsonForm({
        schema: process.form.schema,
        form: process.form.form,
        onSubmit: function(errors, values) {
            if (!errors) {
                callback(values);
            }
        }});
}
