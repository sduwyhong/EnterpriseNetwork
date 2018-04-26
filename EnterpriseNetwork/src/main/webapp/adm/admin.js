var content = $('#content')
var admin = {
	id:'',
	name:'',
	enterprise_id:''
}
var vm_modify_enterprise = {};
var vm_employee = {};
$(document).ready(function(){
	var cookieArray = document.cookie.split(';');
	var isLogin = false;
	for(var i in cookieArray){
		if(cookieArray[i].split('=')[0].trim() == 'admin'){
			isLogin = true;
			var infoArray = cookieArray[i].split('=')[1].split(':');
			admin.id = infoArray[0].substr(1);
			admin.name = decodeURI(infoArray[1]);
			admin.enterprise_id = infoArray[2].substr(0,infoArray[2].length-1);
		}
	}
	if(!isLogin){
		alert('请登录！');
		location.href = '../admin-login.html';
	}
})
$(document).ajaxError(function(){
	alert('服务器忙！请稍后重试。。');
})
var system = {
	init_modify_enterprise:function(){
		vm_modify_enterprise = new Vue({
			el:'#modify_enterprise_el',
			data:{
				enterprise:{}
			},
			methods:{
				getEnterpriseInfo:function(){
					$.ajax({
						url:path+'admin/auth/enterprise/'+admin.enterprise_id,
						type:'get',
						success:function(data){
							vm_modify_enterprise.enterprise = data.object;
						}
					})
				},
				modify:function(){
					$.ajax({
						url:path+'admin/auth/enterprise',
						type:'post',
						data:vm_modify_enterprise.enterprise,
						success:function(data){
							if(data.status == '200'){
								alert('修改成功！');
							}else{
								alert(data.message);
							}
						}
					})
				}
			}
		});
		vm_modify_enterprise.getEnterpriseInfo();
	},
	init_employee:function(){
		vm_employee = new Vue({
			el:'#employee_el',
			data:{
				employee:[],
				size:''
			},
			methods:{
				getEmployees:function(){
					$.ajax({
						url:path+'admin/auth/employee/'+admin.enterprise_id,
						type:'get',
						success:function(data){
							vm_employee.employee = data.object;
							vm_employee.size = data.object.length;
						}
					})
				},
				remove:function(employeeId){
					$.ajax({
						url:path+'admin/auth/employee/'+employeeId,
						type:'delete',
						success:function(data){
							alert(data.message);
						}
					})
				}
			}
		});
		vm_employee.getEmployees();
	}
}