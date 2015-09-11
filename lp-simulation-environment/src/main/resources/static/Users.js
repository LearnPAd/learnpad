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
function users(userid) {

    // TODO: this should be dynamic
    var userInfos = {
        'sally': {
            'name': 'Sally',
            'description': 'Sally Shugar works for the Monti Azzurri Consortium since five years. Since three years she is head of the SUAP office.<p/>She speaks fluently French, Spanish and English; her mother tongue is Italien. She also has profound knowledge in legislation.',
            'img': 'resources/img/SallyShugar.jpg'
        },
        'barnaby': {
            'name': 'Barnaby',
            'description': 'Barnaby joins the Monti Azzurri Consortium recently. His professional background is as a legal assistant.',
            'img': 'resources/img/BarnabyBarnes.jpg'
        }
    };

    var res = {};

    res.setUserList = function(container) {

        // create user subcontainer
        $('#' + container).append(
            '<div class="userui-userinfo"></div>'
        );

        // add user
        $('.userui-userinfo').html(
            '<div id="user-' + userid + '" class="userui-user">' +
                '<a tabindex="0" data-toggle="popover" ' +
                'data-trigger="focus" data-placement="bottom" ' +
                'data-content="' + userInfos[userid].description + '">' +
                '<img src="' + userInfos[userid].img +
                '" alt="' + userInfos[userid].name +
                '" class="img-circle">' +
                '<p>' + userInfos[userid].name + '</p></a></div>'
        );

        // create other users subcontainer
        $('#' + container).append(
            '<div class="panel panel-default">' +
                '<div class="panel-heading">' +
                '<h3 class="panel-title">Other Participants</h3></div>' +
                '<div class="panel-body userui-otherinfo"></div></div>'
        );

        // add other users
        var userInfosKeys = Object.keys(userInfos);
        console.log(userInfosKeys);
        for (var i = 0; i < userInfosKeys.length; i++) {
            var oid = userInfosKeys[i];
            if (oid != userid) {
                $('.userui-otherinfo').append(
                    '<div id="user-' + oid + '" class="userui-user">' +
                        '<a tabindex="0" data-toggle="popover" ' +
                        'data-trigger="focus" data-placement="bottom" ' +
                        'data-content="' + userInfos[oid].description + '">' +
                        '<img src="' + userInfos[oid].img +
                        '" alt="' + userInfos[oid].name +
                        '" class="img-circle">' +
                        '<p>' + userInfos[oid].name +
                        '</p></a></div>'
                );
                // append space to separate users names
                $('.userui-otherinfo').append(' ');
            }
        }

        // outline current user
        var content = $('#user-' + userid + ' p').html();
        $('#user-' + userid + ' img').attr('style', 'border:1px solid black');
        $('#user-' + userid + ' p').html('<b>' + content + '</b>')

        // initialize container popovers
        $('#' + container + ' [data-toggle="popover"]').popover({'html': true});

    };

    return res;
}
