Ext.onReady(function () {
    Ext.QuickTips.init();
// Apply a set of config properties to the singleton
Ext.apply(Ext.QuickTips.getQuickTip(), {
    maxWidth: 200,
    minWidth: 100,
    showDelay: 50,
    trackMouse: true
});

// Manually register a quick tip for a specific element
Ext.QuickTips.register({
    target: 'my-div',
    title: 'My Tooltip',
    text: 'This tooltip was added in code',
    width: 100,
    dismissDelay: 10000 // Hide after 10 seconds hover
});
    var params = Ext.urlDecode(location.search.substring(1));

    var indicatorViewPanel = new Ext.ux.tree.TreeGrid({
        title: 'Dashboard - KPI Overview',
        enableDD: true,
        id: 'dashboard-view-indicators',
        autoScroll: true,
        closable: false,
        tbar: ['->',
            {
                iconCls: 'icon-expand-all',
                tooltip: 'Expand All',
                handler: function () {
                    indicatorViewPanel.root.expand(true);
                },
                scope: this
            }, {
                iconCls: 'icon-collapse-all',
                tooltip: 'Collapse all',
                handler: function () {
                    indicatorViewPanel.root.collapse(true);
                },
                scope: this
            }, {
                iconCls: 'icon-expand-selected',
                tooltip: 'Expand selected',
                handler: function () {
                    if (indicatorViewPanel.selModel.selNode != null) {
                        indicatorViewPanel.selModel.selNode.expand(true);
                    }

                },
                scope: this
            }, {
                iconCls: 'icon-collapse-selected',
                tooltip: 'Collapse selected',
                handler: function () {
                    if (indicatorViewPanel.selModel.selNode != null) {
                        indicatorViewPanel.selModel.selNode.collapse(true);
                    }
                },
                scope: this
            }, {
                tooltip: 'Reload',
                iconCls: 'icon-reload',
                handler: function () {
                    indicatorViewPanel.root.reload();
                }
            }],
        columns: [
            {
                header: 'Details',
                dataIndex: 'dashname',
                width: 430,
            },
            {
                header: 'Status',
                width: 50,
                dataIndex: 'status',
                align: 'center',
                tpl: new Ext.XTemplate('{status:this.showIcon}', {
                    showIcon: function (v) {
                        if (v != '') {
                            return '<span style=\"display:block\"><img src=\"icons/tree-state-' + v + '.gif\"></span>';
                        } else {
                            return '';
                        }

                    }
                })

            }, {
                header: 'Recommendation',
                dataIndex: 'recommendation',
                width: 200,
                tpl: new Ext.XTemplate('{recommendation:this.renderWithTooltip}', {
                    renderWithTooltip: function (v) {
                        if (v != null && v!= '') {
                            return '<span ext:qtitle="Recommendation:" ext:qwidth="300" ext:qtip="'+v+'">'+v+'</span>';
                        } else {
                            return '';
                        }
                    }
                })
            }, {
                header: 'Trend',
                dataIndex: 'trend',
                width: 50,
                align: 'center',
                tpl: new Ext.XTemplate('{trend:this.showTrendIcon}', {
                    showTrendIcon: function (trend) {
                        switch (trend) {
                            case '1':
                                return '<span style=\"display:block\"><img src=\"icons/trend-up.png\"></span>';
                                break;
                            case '-1':
                                return '<span style=\"display:block\"><img src=\"icons/trend-down.png\"></span>';
                                break;
                            case '0':
                                return '<span style=\"display:block\"><img src=\"icons/trend-neutral.png\"></span>';
                                break;
                            default:
                                return '';
                                break;
                        }


                    }
                })
            }, {
                header: 'Unit',
                dataIndex: 'unit',
                width: 100
            }, {
                header: 'Target',
                dataIndex: 'target',
                width: 100
            }, {
                header: 'Is Value',
                dataIndex: 'value',
                width: 100
            }, {
                header: 'Previous Period',
                dataIndex: 'prevperiod',
                width: 100
            }, ],
        loader: new Ext.ux.tree.TreeGridLoader({
            dataUrl: 'data?businessActorId=' + params.businessActorId,
            requestMethod: "GET"
        })
    });
    var modelsPanel = new Ext.TabPanel({
        id: 'models-panel',
        region: 'center',
        anchor: '100%, 75%',
        enableTabScroll: true,
        margins: '5 5 5 5',
        cmargins: '5 5 5 5',
        items: [indicatorViewPanel],
        activeTab: 0,
        listeners: {
            render: function (component) {
                component.loadMask = new Ext.LoadMask(component.el, {
                    msg: "Loading ...."
                });
                // NOT NEEDED; since nothing there initially - FIXME
                // setTimeout(function() { component.loadMask.show(); }, 100);
            }
        }
    });

    var viewport = new Ext.Viewport({
        layout: 'border',
        items: [{
                region: 'north',
                border: false,
                tbar: menu
            }, modelsPanel]
    });

});