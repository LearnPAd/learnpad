var menu = [
		{
			text : 'View Selection',
			xtype : 'splitbutton',
			id : 'dashboard-view',
			toggleID : 'dashboard-view',
			enableToggle : true,
			pressed : false,
			iconCls : 'icon-view',
			scale : 'large',
			handler : function() {
				Ext.getCmp('dashboard-view').showMenu();
			},
			listeners : {
				render : toggleButtonListener
			},
			url : 'index.htm',
			menu : new Ext.menu.Menu({
				items : [
				// these items will render as dropdown menu items when the arrow
				// is clicked:
				{
					iconCls : 'icon-view-small',
					toggleID : 'dashboard-view-indicators',
					text : 'Indicators View',
					handler : siteChangeHandler,
					url : 'index.htm',
					scale : 'large'
				}, {
					iconCls : 'icon-view-small',
					toggleID : 'dashboard-view-measures',
					text : 'Measures View',
					handler : siteChangeHandler,
					url : 'index.htm',
					scale : 'large'
				} ]
			}),
			scope : this
		},
		{
			text : 'Chart Selection',
			xtype : 'splitbutton',
			id : 'chart-view',
			toggleID : 'chart-view',
			enableToggle : true,
			pressed : false,
			iconCls : 'icon-run',
			scale : 'large',
			handler : function() {
				Ext.getCmp('chart-view').showMenu();
			},
			listeners : {
				render : toggleButtonListener
			},
			url : 'index.htm',
			menu : new Ext.menu.Menu({
				items : [
				// these items will render as dropdown menu items when the arrow
				// is clicked:
				{
					iconCls : 'icon-run-small',
					toggleID : 'chart-view-spider',
					text : 'Spider Chart',
					handler : siteChangeHandler,
					url : 'index.htm',
					scale : 'large'
				}, {
					iconCls : 'icon-run-small',
					toggleID : 'chart-view-bar',
					text : 'Bar Chart',
					handler : siteChangeHandler,
					url : 'index.htm',
					scale : 'large'
				}, {
					iconCls : 'icon-run-small',
					toggleID : 'chart-view-graph',
					text : 'Graph Chart',
					handler : siteChangeHandler,
					url : 'index.htm',
					scale : 'large'
				} ]
			}),
			scope : this
		},
		'->',
		{
			xtype : 'label',
			html : '<div style="margin-right:30px;"><b><font size="5">LearnPAd KPI Dashboard</font></b></div>'
		} ];

function siteChangeHandler(component) {
	var modelspanel = Ext.getCmp('models-panel');
	var tab = modelspanel.getComponent(component.toggleID);
	if (tab) {
		modelspanel.setActiveTab(tab);
	} else {
		modelspanel.add({
			title : 'Dashboard - ' + component.text,
			html : 'This view is not implemented yet.',
			id : component.toggleID,
			closable : true
		}).show();
	}
};

function toggleButtonListener(component) {
	if (window.location.href.indexOf(component.toggleID) != -1) {
		component.toggle();
	}
}