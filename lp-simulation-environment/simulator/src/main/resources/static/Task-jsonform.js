/*
 * #%L
 * LearnPAd Simulator
 * %%
 * Copyright (C) 2014 - 2015 Linagora
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
'use strict';

function taskFormGenerate(taskid, data, formContainer, formId, callback) {
    // check if we stored a previous submission for the task
    // if yes, use it for default form values
    var values = retrieveFormContent(taskid);

    $('#' + formContainer).html('<form id="' +
                        formId + '"  class="well"></form>');

    $('#' + formId).jsonForm({
        schema: JSON.parse(data.form).schema,
        form: JSON.parse(data.form).form,
        value: values,
        onSubmit: function(errors, values) {
            if (!errors) {
                // store submission to restore values in other tries
                saveFormContent(taskid, values);
                callback(JSON.stringify(values));
            }
        }});
}

function saveFormContent(taskid, values) {
    try {
        if (window.sessionStorage) {
            sessionStorage.setItem(taskid, JSON.stringify(values));
        }
    } catch (exception) {
        // nothing more we can do
    }
}

function retrieveFormContent(taskid) {
    try {
        if (window.sessionStorage) {
            if (window.sessionStorage && window.sessionStorage.getItem(taskid)) {
                return JSON.parse(sessionStorage.getItem(taskid));
            }
        }
    } catch (exception) {
        // do nothing, we will return default value
    }
    // default return value in case of null or failure
    return null;
}
