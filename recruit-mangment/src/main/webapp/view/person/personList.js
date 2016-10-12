/*!
 * 进货入库
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	var v_start=0, v_limit=20;
	
//	var SpxxObj = [
//		{ name:'spid', type:'string'},
//		{ name:'spname', type:'string'},
//		{ name:'xinghao', type:'string'},
//		{ name:'dw', type:'string'},
//		{ name:'jhprice', type:'string'},
//		{ name:'chprice', type:'string'},
//		{ name:'minnum', type:'string'},
//		{ name:'csname', type:'string'},
//		{ name:'bz', type:'string'},
//		{ name:'lbid', type:'int'},
//		{ name:'lbname', type:'string'}
//	];
	
	//商品数据
	var userStore = new Ext.data.JsonStore({
	    url: 'spxx_findPageSpxx.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: userObj,
	    listeners:{beforeload:function(a){a.baseParams={start:v_start, limit:v_limit};}}
	});
	
	//用户列表
    var userGrid = new Ext.grid.GridPanel({
    	id:'userGrid',
        store: userStore,
        cm: new Ext.grid.ColumnModel({
			defaults: {	menuDisabled : true},//禁止表头菜单
			columns:[new Ext.grid.RowNumberer(),
				{header: '用户名称', width: 100, sortable:true, dataIndex: 'userName'},
	            {header: '昵称', width: 200, sortable:true, dataIndex: 'nikeName'},
	            {header: '邮箱', width: 150, sortable:true, dataIndex: 'email'},
	            {header: '电话', width: 100, sortable:true, dataIndex: 'phone'},
	            {header: '注册时间', width: 100, sortable:true, align:'right', renderer:zhMoney, dataIndex: 'cbj'},
	            {header: '最后修改时间', width: 100, sortable:true, align:'center', dataIndex: 'sl'},
	            {header: '登录IP', width: 100, sortable:true, align:'right', renderer:zhMoney, dataIndex: 'zj'}]
        }),
        stripeRows: true, 	//行分隔符
        columnLines : true, //列分隔符
        margins:'20',
        style:'border:1px solid',
		region:'center',
        iconCls:'',
        
        tbar:[{
        	text:'添加',
        	iconCls:'btn-add',
        	handler: function(){
        		addUserWindow.show();
        		uForm.getForm().reset();
        		uForm.getForm().findField("userId").setDisabled(false);
        	}
        },'-',{
        	text:'修改',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= userGrid.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要修改的商品');
				}else{
	        		addJhWindow.show();
	        		addJhWindow.buttons[0].setVisible(false);
	        		record.set("update","true");
					addJhForm.getForm().loadRecord(record);
				}
        	}
        },'-',{
        	text:'删除',
        	iconCls:'btn-remove',
        	handler: function(){
        		var record= userGrid.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要删除的商品');  
				}else{
					Ext.MessageBox.confirm('删除提示', '是否删除该记录？', function(c) {
						var userForm = Ext.getCmp("userForm").getForm();
						var yfje = userForm.findField("yfje").getValue();
						var sfje = userForm.findField("sfje").getValue();
						userForm.findField("yfje").setValue(yfje-record.get("zj"));
						userForm.findField("sfje").setValue(sfje-record.get("zj"));
						userStore.remove(record);
					});
				}
        	}
        }],
        
        listeners:{
        	rowdblclick:function(){
        		var record= userGrid.getSelectionModel().getSelected(); 
				if(record){
	        		addJhWindow.show();
	        		addJhWindow.buttons[0].setVisible(false);
	        		record.set("update","true");
					addJhForm.getForm().loadRecord(record);
				}
        	}
        }
    });
    
    var uForm = new Ext.FormPanel({
		layout : 'form',
		baseCls:'x-plain',
		labelWidth:60,
		border : false,
		padding : '15 10 0 8',
		defaults : {
			anchor : '100%',
			xtype : 'textfield'
		},
		items:[{
				name:'userName',
				fieldLabel:'客户账号',
				maxLength :20,
				allowBlank : false
			},{
				inputType: 'password',
				name:'password',
				fieldLabel:'密码',
				maxLength :20,
				value:'888888',
				allowBlank : false
			},{
				xtype:'textarea',
				name:'desc',
				fieldLabel:'说明',
				height:80,
				maxLength :200
			},{
				xtype : 'hidden',
			    name : 'userId'
			}]
	});
    
    var addUserWindow = new Ext.Window({
		title : '添加用户窗口',
		width:400,
		height:300,
		closeAction:'hide',
		modal : true,
		layout : 'fit',
		buttonAlign : 'center',
		items : [uForm],
		buttons : [{
			text : '保存',
			handler : function() {
				if (uForm.getForm().isValid()) {
					uForm.getForm().submit({
						url : path+'/loginUser/add.do',
						success : function(form, action) {
							Ext.Msg.alert('信息提示',action.result.message);
							addUserWindow.hide();
							store.reload();
						},
						failure : function(form, action) {
							if(action.result.errors){
								Ext.Msg.alert('信息提示',action.result.errors);
							}else{
								Ext.Msg.alert('信息提示','连接失败');
							}
						},
						waitTitle : '提交',
						waitMsg : '正在保存数据，稍后...'
					});
				}
			}
		}, {
			text : '取消',
			handler : function() {
				addUserWindow.hide();
			}
		}]
	});
    
	
    //用户表单
	var userForm = new Ext.FormPanel({
		id:'userForm',
		region:'north',
		height: 130,
		border : false,
		layout : 'form',
		labelWidth:60,
		padding : 20,
		items:[{
			id:"userfieldset",
			xtype:"fieldset",
			title:"查询条件：",
			padding:'0 20 0 15',
			items:[{
				layout:"column",
				defaults:{
					xtype:"container",
					autoEl:"div",
					columnWidth:0.2,
					labelAlign:'right',
					layout:"form"
				},
				items:[{
					items:[{
						xtype:"textfield",
						name:'loginName',
						fieldLabel:"登录姓名",
						anchor:"90%",
						maxLength :200
					}]
				},{
					items:[{
						xtype:"textfield",
						name:'nikeName',
						fieldLabel:"昵&nbsp;&nbsp;&nbsp;&nbsp;称",
						anchor:"90%",
						maxLength :200
					}]
				},{
					items:[{
						xtype:"textfield",
						name:'phone',
						fieldLabel:"电话号码",
						anchor:"90%",
						maxLength :11
					}]
				},{columnWidth:0.1},{
					items:[{
						id:'createDateStart',
						xtype:"datefield",
						name:'startDate',
						fieldLabel:"开始日期",
						format:'Y-m-d h:i:s',
						allowBlank : false,
						value:new Date(),
						anchor:"90%",
					
					}]
				},{columnWidth:0.1},{
					items:[{
						id:'createDateEnd',
						xtype:"datefield",
						name:'endDate',
						fieldLabel:"结束日期",
						format:'Y-m-d h:i:s',
						allowBlank : false,
						value:new Date(),
						anchor:"90%",
					
					}]
				}
				
				]
			},{
				layout:"column",
				defaults:{
					xtype:"container",
					autoEl:"div",
					labelAlign:'right',
					layout:"form"
				},
				items:[{
					columnWidth:0.2,
					items:[{
						xtype:"textfield",
						name:'email',
						fieldLabel:"邮&nbsp;&nbsp;&nbsp;箱",
						anchor:"90%",
						maxLength :50
					}]
				},{
					columnWidth:0.2,
					items:[{
						xtype:'combo',
						hiddenName:'source',
						fieldLabel:'注册来源',
						mode: 'local',
						triggerAction: 'all',
						valueField :'value',
						displayField: 'text',
						allowBlank : false,
						editable : false,
						anchor:"90%",
						value:'-1',
						store : new Ext.data.SimpleStore({
						    fields: ['value', 'text'],
						    data : [['-1','--请选择--'],['0','web注册'],['1','app注册'],['2','微信注册'],['3','其它']]
						})
					}]
				},
				{
					columnWidth:0.2,
					items:[{
						xtype:'combo',
						hiddenName:'status',
						fieldLabel:'状态',
						mode: 'local',
						triggerAction: 'all',
						valueField :'value',
						displayField: 'text',
						allowBlank : false,
						editable : false,
						anchor:"90%",
						value:'-1',
						store : new Ext.data.SimpleStore({
						    fields: ['value', 'text'],
						    data : [['-1','--请选择--'],['0','正常'],['1','禁用'],['2','注销']]
						})
					}]
				}
				,{
					columnWidth:0.2,
					items:[{
						width:75,
						xtype:"button",
						text:'查询',
						handler:function(){
							
							var f = userForm.getForm();
							console.debug(f)
							console.debug(f.isValid())
							console.debug({params:f.getValues()})
							if (f.isValid()) {
								userStore.load({params:f.getValues()});
							}
							
							
							
							
							
							
							if (f.isValid()) {
								if(userStore.getCount()<=0){
									Ext.Msg.alert("信息提示","请添加商品");
									return;
								}
								var jsonArray = [];
								userStore.each(function(item) {
						            jsonArray.push(item.data);
						        });
								f.submit({
									url : 'jh_saveOrUpdateJhd.do',
									params :{djsps:Ext.encode(jsonArray) },
									success : function(form, action) {
										Ext.Msg.alert("信息提示","数据保存成功",function(){
											getCode();
											f.findField("bz").setValue("");
											f.findField("yfje").setValue("");
											f.findField("sfje").setValue("");
											f.clearInvalid();
											userStore.removeAll();
										});
									},
									failure : function(form, action) {
										if(action.result && action.result.errors){
											Ext.Msg.alert('信息提示',action.result.errors);
										}else{
											Ext.Msg.alert('信息提示','连接失败');
										}
									}
								});
							}
							
							
							
							
							
							
							
							
						}
					}]
				}]
			},{
				xtype:'hidden',
				name:'djid'
			},{
				xtype:'hidden',
				name:'gysname'
			}]
		}]
	});
	
	//用户数据查询
//	var userStore = new Ext.data.JsonStore({
//	    url: 'search_findKcByParams.do',
//	    root: 'root',
//	    totalProperty: 'total',
//	    fields: userObj
//	});
	
	var userObj = [
	       		{ name:'userName', type:'string'},
	       		{ name:'nikeName', type:'string'},
	       		{ name:'email', type:'string'},
	       		{ name:'phone', type:'string'},
	       		{ name:'sex', type:'int'},
	       		{ name:'xsll', type:'int'},
	       		{ name:'scjj', type:'double'},
	       		{ name:'jhprice', type:'double'},
	       		{ name:'chprice', type:'double'},
	       		{ name:'kczj', type:'double'},
	       		{ name:'dw', type:'string'},
	       		{ name:'csname', type:'string'},
	       		{ name:'zj', type:'string'}
	       	];
//	
//	//设置单据编号
//	var getCode = function(){
//		var ymd = Ext.getCmp("jhriqi").getValue().format("Y-m-d");
//		Ext.Ajax.request({
//   			url : "jh_getDjCode.do",
//   			params : {tab:'Jhd',ymd:ymd},
//   			success : function(o) {
//   				if(o.responseText){
//   					var code = "JH"+o.responseText;
//   					Ext.getCmp("userfieldset").setTitle("查询条件："+code);
//   					userForm.getForm().findField("djid").setValue(code);
//   				}
//   			}
//   		});
//	};
	
	//布局
    new Ext.Viewport({
		layout:'fit',
		items:[{
			frame:true,
			title:'用户查询',
			iconCls:'menu-62',
			layout:'border',
			items:[userForm,userGrid]
		}],
		listeners:{
			render:function(){
//				getCode();
//				gysStore.load();
			}
		}
	});
	
   
    
	

});