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
function heightMonitor() {

    function getDocHeight() {
        return $('#main-container').height();
    }

    var res = {
        enableMonitoring: false,
        receivers: [],
        prevHeight: getDocHeight(),

        // add a receiver to be notified of height changes
        addReceiver: function(receiver) {
            if (!res.enableMonitoring) return;

            res.receivers.push(receiver);
        },

        // if the height have changed, send a notification to receivers
        checkForChangeNotification: function() {
            if (!res.enableMonitoring) return;
            var height = getDocHeight();

            if (res.prevHeight != height) {
                res.prevHeight = height;
                for (var i = 0; i < res.receivers.length; i++) {
                    res.receivers[i].postMessage({'height': height}, '*');
                }
            }
        },

        // use mutation observers to check for elements modifications
        // that could trigger a height change
        monitor: function(element, config) {
            if (!res.enableMonitoring) return;

            if (typeof config == 'undefined') {
                config = { attributes: true,
                           subtree: true,
                           childList: true };
            }
            // add an observer to the element
            var elements = document.querySelectorAll(element);
            for (var i = 0; i < elements.length; i++) {
                new MutationObserver(function(mutations) {
                    res.checkForChangeNotification();
                }).observe(elements[i], config);
            }
        }

    };

    return res;
}

var heightMon = heightMonitor();
heightMon.enableMonitoring = true;

// If we are not the top window
if (window.parent != window) {

    // enable height monitoring
    heightMon.enableMonitoring = true;

    // add window parent as receiver
    heightMon.addReceiver(window.parent);

    // add function to handle height requests from parent window
    $(function() {
        var handleMessage = function handleMessage(e) {
            var height = $('html').height();
            // Must call postMessage on the Window object of the recipient
            window.parent.postMessage({'height': height}, e.origin);
        };
        window.addEventListener('message', handleMessage, false);
    });

}
