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
function chat(container, websocketadress, user) {

    $('#' + container).html(
        '<div id="chat_msgs" class="well well-lg" style="height:30%;overflow:auto;"></div>' +
            '<input type="text" class="form-control"' +
            'id="chat_msg" placeholder="Write your message and press enter"/>'
    );

    // attach slim scrollbar to chat_msgs
    $(function() {
        $('#chat_msgs').slimScroll({
            height: '250px'
        });
    });

    var chatResult = {};

    var ws = new WebSocket('ws://' + websocketadress + '/chat');

    var write = function(msg) {
        var pre = document.createElement('p');
        pre.style.wordWrap = 'break-word';
        pre.innerHTML = msg;

        var chat_msgs = $('#chat_msgs');
        chat_msgs.append(pre);
        chat_msgs.slimScroll({scrollTo: chat_msgs[0].scrollHeight});
    };

    var send = function(msg) {
        write(msg);
        ws.send(msg);
    };

    ws.onopen = function(e) {
        write('<span style="color: grey;">' + user +
             ' connected</span>');
    };

    ws.onclose = function(e) {
        write('<span style="color: grey;">' + user +
             ' left the discussion</span>');
    };

    ws.onmessage = function(e) {
        write('expert : ' + e.data);
    };

    ws.onerror = function(e) {
        write('<span style="color: red;">ERROR:</span> ' + e.data);
    };

    chatResult._ws = ws;

    $('#chat_msg').keypress(function(event) {
        if (event.which == 13) {
            // 'Enter' key pressed
            send(user + ' : ' + $('#chat_msg').val());
            $('#chat_msg').val('');
        }
    });

    return chatResult;
}
