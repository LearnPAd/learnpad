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
function users(address, userid, involvedusers, container) {

    // create user subcontainer
    $('#' + container).append(
        '<div id="' + container + 'userinfo" class="userui-userinfo"></div>'
    );

    // create other users subcontainer
    $('#' + container).append(
        '<div class="panel panel-default">' +
            '<div class="panel-heading">' +
            '<h3 class="panel-title">Other Participants</h3></div>' +
            '<div id="' +
            container + 'otherinfo" class="panel-body userui-otherinfo"></div></div>'
    );

    var userInfos = {};

    for (var i = 0; i < involvedusers.length; i++) {
        requestUserInfos(involvedusers[i]);
    }

    function requestUserInfos(userId) {
        var req = new XMLHttpRequest();
        req.open('GET',
                 'http://' + address + '/learnpad/sim/users/' + userId,
                 true);

        req.onreadystatechange = function(e) {
            if(req.readyState == 4 && req.status == 200) {
                registerUserInfos(userId, JSON.parse(req.responseText));
            }
        };
        req.send(null);
    }

    function registerUserInfos(user, infos) {
        userInfos[user] = infos;
        if(user === userid) {
            // add current user infos
            $('#' + container + 'userinfo').html(
                '<div id="user-' + userid + '" class="userui-user">' +
                    '<a tabindex="0" data-toggle="popover" ' +
                    'data-trigger="focus" data-placement="bottom" ' +
                    'data-content="' + userInfos[userid].bio + '">' +
                    '<img src="' + userInfos[userid].pictureURL +
                    '" alt="' + userInfos[userid].firstName +
                    ' ' + userInfos[userid].lastName +
                    '" class="img-circle">' +
                    '<p>' + userInfos[userid].firstName + ' ' +
                    userInfos[userid].lastName + '</p></a></div>'
            );

            // outline current user
            var content = $('#user-' + userid + ' p').html();
            $('#user-' + userid + ' img').attr('style', 'border:1px solid black');
            $('#user-' + userid + ' p').html('<b>' + content + '</b>');

        } else {
            // add other user infos
            var userInfosKeys = Object.keys(userInfos);
            for (var i = 0; i < userInfosKeys.length; i++) {
                var oid = userInfosKeys[i];
                if (oid != userid) {
                    $('#' + container + 'otherinfo').append(
                        '<div id="user-' + oid + '" class="userui-user">' +
                            '<a tabindex="0" data-toggle="popover" ' +
                            'data-trigger="focus" data-placement="bottom" ' +
                            'data-content="' + userInfos[oid].bio + '">' +
                            '<img src="' + userInfos[oid].pictureURL +
                            '" alt="' + userInfos[oid].firstName + ' ' +
                            userInfos[oid].lastName +
                            '" class="img-circle">' +
                            '<p>' + userInfos[oid].firstName + ' ' +
                            userInfos[oid].lastName +
                            '</p></a></div>'
                    );
                    // append space to separate users names
                    $('#' + container + 'otherinfo').append(' ');
                }
            }

            // (re)initialize container popovers
            $('#' + container + ' [data-toggle="popover"]').popover(
                {'html': true}
            );
        }
    }
}
