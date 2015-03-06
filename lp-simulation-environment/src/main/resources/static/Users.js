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

    var res = {};

    res.setUserList = function(container) {

        // add user list to container
        $('#' + container).html(
            '<div class="panel panel-default userui-parent">' +
                '<div id="user-sally" class="userlist-user">' +
                '<a tabindex="0" data-toggle="popover" ' +
                'data-trigger="focus" data-placement="bottom" ' +
                'data-content="<ul><li>Sally Shugar works for the Monti Azzurri Consortium since five years. Since three years she is head of the SUAP office.</li><li>She speaks fluently French, Spanish and English; her mother tongue is Italien. She also has profound knowledge in legislation.</li><li> She is responsible for the SUAP process and familiar with process modelling.</li><li>She set up the goals for the organisational unit and for the process and defined the KPIs to be met. She further defined the roles and competencies needed to perform the process, respectively its tasks.</li></ul>"><img src="resources/img/SallyShugar.jpg" alt="Sally" class="img-circle" /><p align="center">Sally</p></a></div>' +
                '<div id="user-barnaby" class="userlist-user">' +
                '<a tabindex="0" data-toggle="popover" ' +
                'data-trigger="focus" data-placement="bottom" ' +
                'data-content="<ul><li>Barnaby joins the Monti Azzurri Consortium recently. His professional background is as a legal assistant.</li><li>The daily activity of Barnaby are:<ul><li> Provide some information to the entrepreneurs that aim to open an activity;</li><li>He receives the SCIA COMMERCIALE BP request from the entrepreneur, this can be done by mail or by web form. He checks if the requests instance is admissible. He ask for integration to the entrepreneur. He forwards the request to the involved municipality offices and to third parties. He communicate the final decision about the instance to the entrepreneurs.</li><li>He cares about all the other BPs involved in the SUAP office.</li><li>He is use to do inspection to check if declaration are fitting with the reality.</li></ul></ul>"><img src="resources/img/BarnabyBarnes.jpg" alt="Barnaby" class="img-circle"><p align="center">Barnaby</p></a></div>'+
                '</div>'
        );

        // outline current user
        var content = $('#user-' + userid + ' p').html();
        $('#user-' + userid + ' img').attr('style', 'border:1px solid black');
        $('#user-' + userid + ' p').html('<b>' + content + '</b>')

        // initialize container popovers
        $('#' + container + ' [data-toggle="popover"]').popover({'html': true});

    };

    return res;
}
