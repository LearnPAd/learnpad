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
