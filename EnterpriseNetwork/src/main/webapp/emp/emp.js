var content = $('#content');
var user = {
		id:'',
		name:'',
		enterprise_id:''
}
$(document).ready(function(){
	var cookies = document.cookie;
	var cookieArray = cookies.split(';');
	var auth = false;
	for(var i in cookieArray){
		if(cookieArray[i].split('=')[0].trim() == 'emp'){
			var value = cookieArray[i].split('=')[1].split(':');
			user.id = value[0].substr(1);
			user.name = decodeURI(value[1]);
			user.enterprise_id = value[2].substr(0,value[2].length-1);
			auth = true;
		}
	}
	if(!auth){
		alert('请登录！');
		location.href = '../emp-login.html';
	}
});
$(document).ajaxError(function(){
	alert('服务器忙！请稍候重试。');
})
var vm_modify_info = {};
var vm_responsible_enterprise = {};
var vm_responsible_product = {};
var vm_my_colleague = {};
var vm_add_colleague = {};
var system = {
	init_modify:function(){
		vm_modify_info = new Vue({
			el:'#emp_modify',
			data:{
				data:{
					enterprise_name:'',
					worker_no:'',
					password:'',
					name:'',
					gender:'',
					age:'',
					email:''
				}
			},
			methods:{
				getInfo:function(){
					$.ajax({
						url:path+'employee/auth/info/'+user.id,
						type:'get',
						success:function(data){
							vm_modify_info.data = data.object;
						}
					})
				},
				modify:function(){
					$.ajax({
						url:path+'employee/auth/update',
						type:'post',
						data:vm_modify_info.data,
						success:function(data){
							if(data.status == '200'){
								alert('修改成功！');
								location.href = 'emp-index.html';
							}else{
								alert(data.message);
							}
						}
					})
				}
			}
		});
		vm_modify_info.getInfo();
	},
	init_responsible_enterprise:function(){
		vm_responsible_enterprise = new Vue({
			el:'#responsible_enterprise_el',
			data:{
				enterprises:[],
				size:''
			},
			methods:{
				getResponsibleEnterprise:function(){
					$.ajax({
						url:path+'employee/auth/enterprise/'+user.id,
						type:'get',
						success:function(data){
							vm_responsible_enterprise.enterprises = data.object;
							vm_responsible_enterprise.size = data.object.length;
						}
					})
				}
			}
		});
		vm_responsible_enterprise.getResponsibleEnterprise();
	},
	init_responsible_product:function(){
		vm_responsible_product = new Vue({
			el:'#responsible_product_el',
			data:{
				products:[],
				size:''
			},
			methods:{
				getProducts:function(){
					$.ajax({
						url:path+'employee/auth/product/'+user.id,
						type:'get',
						success:function(data){
							vm_responsible_product.products = data.object;
							vm_responsible_product.size = data.object.length;
						}
					})
				}
			}
		});
		vm_responsible_product.getProducts();
	},
	init_my_colleague:function(){
		vm_my_colleague = new Vue({
			el:'#my_colleague_el',
			data:{
				colleagues:[],
				size:''
			},
			methods:{
				getColleagues:function(){
					$.ajax({
						url:path+'employee/auth/colleague/'+user.id,
						type:'get',
						success:function(data){
							vm_my_colleague.colleagues = data.object;
							vm_my_colleague.size = data.object.length;
						}
					})
				}
			}
		});
		vm_my_colleague.getColleagues();
	},
	init_add_colleague:function(){
		vm_add_colleague = new Vue({
			el:'#add_colleague_el',
			data:{
				pretentials:[],
				size:''
			},
			methods:{
				getPretentials:function(){
					$.ajax({
						url:path+'employee/auth/pretential/colleague/'+user.id,
						type:'get',
						success:function(data){
							vm_add_colleague.pretentials = data.object;
							vm_add_colleague.size = data.object.length;
						}
					})
				},
				addColleague:function(colleagueId){
					$.ajax({
						url:path+'employee/auth/colleague',
						type:'post',
						data:{
							employeeId:user.id,
							colleagueId:colleagueId
						},
						success:function(data){
							alert(data.message);
							content.load('my-colleague.html',function(){
								system.init_my_colleague();});
						}
					})
				}
			}
		}),
		vm_add_colleague.getPretentials();
	},
	logout:function(){
		$.ajax({
			url:path+'employee/auth/logout',
			type:'post',
			success:function(data){
				alert('bye');
				location.href = '../emp-login.html';
			}
		})
	}
}
