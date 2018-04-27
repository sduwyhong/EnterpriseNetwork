var content = $('#content');
var admin = {
	id:'',
	name:'',
	enterprise_id:''
}
var vm_modify_enterprise = {};
var vm_employee = {};
var vm_add_employee = {};
var vm_product = {};
var vm_add_product = {};
var vm_composition = {};
var vm_add_composition = {};
var vm_corporation = {};
var vm_add_corporation = {};
$(document).ajaxError(function(){
	alert('服务器忙！');
})
$(document).ready(function(){
	var cookieArray = document.cookie.split(';');
	var isLogin = false;
	for(var i in cookieArray){
		if(cookieArray[i].split('=')[0].trim() == 'admin'){
			isLogin = true;
			var infoArray = cookieArray[i].split('=')[1].split(':');
			admin.id = infoArray[0].substr(1);
			admin.name = decodeURI(infoArray[1]);
			$('#username').text(admin.name);
			admin.enterprise_id = infoArray[2].substr(0,infoArray[2].length-1);
		}
	}
	if(!isLogin){
		alert('请登录！');
		location.href = '../admin-login.html';
	}
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
							content.load('employee.html',function(){
								system.init_employee();
							})
						}
					});
				},
				addEmployeePage:function(){
					content.load('add-employee.html',function(){
						system.init_add_employee();
					});
				}
			}
		});
		vm_employee.getEmployees();
	},
	init_add_employee:function(){
		vm_add_employee = new Vue({
			el:'#add_employee_el',
			data:{
				data:{
					enterprise_id:admin.enterprise_id,
					worker_no:'',
					password:'',
					name:'',
					gender:'',
					age:'',
					email:''
				}
			},
			methods:{
				register:function(){
					$.ajax({
						url:path+'admin/auth/employee',
						type:'post',
						data:vm_add_employee.data,
						success:function(data) {
							if(data.status == '200') {
								alert('添加成功！');
								content.load('employee.html',function(){
									system.init_employee();
								})
							}else{
								alert(data.message);
							}
						}
					})
				}
			}
		})
	},
	init_product:function(){
		vm_product = new Vue({
			el:'#product_el',
			data:{
				products:[],
				size:'',
				employees:[],
				employeeId:''
			},
			methods:{
				getProducts:function(){
					$.ajax({
						url:path+'admin/auth/product/'+admin.enterprise_id,
						success:function(data){
							vm_product.products = data.object;
							vm_product.size = data.object.length;
						}
					})
				},
				delegateChoices:function(event){
					$(event.currentTarget).hide();
					$.ajax({
						url:path+'admin/auth/employee/'+admin.enterprise_id,
						success:function(data){
							vm_product.employees = data.object;
						}
					});
					$(event.currentTarget).next().show();
				},
				delegate:function(productId){
					if(confirm('确认让其负责吗？')){
						$.ajax({
							url:path+'admin/auth/delegation/product',
							type:'post',
							data:{
								employeeId:vm_product.employeeId,
								productId:productId
							},
							success:function(data){
								alert('指派成功！');
								content.load('product.html',function(){
									system.init_product();
								})
							}
						})
					}
				},
				cancelDelegation:function(employeeId,productId){
					if(confirm('确认解除吗？')){
						$.ajax({
							url:path+'admin/auth/cancel/delegation/product',
							type:'post',
							data:{
								employeeId:employeeId,
								productId:productId
							},
							success:function(data){
								alert('解除成功！ ');
								content.load('product.html',function(){
									system.init_product();
								})
							}
						})
					}
				},
				remove:function(productId){
					if(confirm('确认删除吗？')){
						$.ajax({
							url:path+'admin/auth/product/'+productId,
							type:'delete',
							success:function(data){
								alert('删除成功');
								content.load('product.html',function(){
									system.init_product();
								})
							}
						})
					}
				},
				addProductPage:function(){
					content.load('add-product.html',function(){
						system.init_add_product();
					})
				}
			}
		});
		vm_product.getProducts();
	},
	init_add_product:function(){
		vm_add_product = new Vue({
			el:'#add_product_el',
			data:{
				data:{
					name:'',
					description:'',
					enterprise_id:admin.enterprise_id
				}
			},
			methods:{
				addProduct:function(){
					$.ajax({
						url:path+'admin/auth/product',
						type:'post',
						data:vm_add_product.data,
						success:function(data){
							if(data.status == '200'){
								alert('添加成功！');
								content.load('product.html',function(){
									system.init_product();
								})
							}else{
								alert(data.message);
							}
						}
					})
				}
			}
		})
	},
	init_composition:function(){
		vm_composition = new Vue({
			el:'#composition_el',
			data:{
				compositions:[],
				size:''
			},
			methods:{
				getCompositions:function(){
					$.ajax({
						url:path+'admin/auth/composition/'+admin.enterprise_id,
						success:function(data){
							vm_composition.compositions = data.object;
							vm_composition.size = data.object.length;
						}
					})
				},
				remove:function(productId, compositionId){
					if(confirm('确认删除吗？')){
						$.ajax({
							url:path+'admin/auth/delete/composition',
							type:'post',
							data:{
								productId:productId,
								compositionId:compositionId
							},
							success:function(data){
								alert('删除成功！');
								content.load('composition.html',function(){
									system.init_composition();
								})
							}
						});
					}
				},
				addCompositionPage:function(){
					content.load('add-composition.html',function(){
						system.init_add_composition();
					})
				}
			}
		})
		vm_composition.getCompositions();
	},
	init_add_composition:function(){
		vm_add_composition = new Vue({
			el:'#add_composition_el',
			data:{
				products:[],
				compositions:[],
				data:{
					productId:'',
					compositionId:''
				}
			},
			methods:{
				getProductsAndCompositions:function(){
					$.ajax({
						url:path+'admin/auth/product/'+admin.enterprise_id,
						success:function(data){
							vm_add_composition.products = data.object;
						}
					})
					$.ajax({
						url:path+'admin/auth/pretential/composition/'+admin.enterprise_id,
						success:function(data){
							vm_add_composition.compositions = data.object;
						}
					})
				},
				addComposition:function(){
					$.ajax({
						url:path+'admin/auth/composition',
						type:'post',
						data:vm_add_composition.data,
						success:function(data){
							if(data.status == '200') {
								alert('添加成功！');
								content.load('composition.html',function(){
									system.init_composition();
								})
							}else{
								alert(data.message);
							}
						}
					})
				}
			}
		})
		vm_add_composition.getProductsAndCompositions();
	},
	init_corporation:function(){
		vm_corporation = new Vue({
			el:'#corporation_el',
			data:{
				corporations:[],
				employees:[],
				size:'',
				employeeId:''
			},
			methods:{
				getCorporations:function(){
					$.ajax({
						url:path+'admin/auth/corporation/'+admin.enterprise_id,
						success:function(data){
							vm_corporation.corporations = data.object;
							vm_corporation.size = data.object.length;
						}
					})
				},
				delegateChoices:function(event){
					$(event.currentTarget).hide();
					$.ajax({
						url:path+'admin/auth/employee/'+admin.enterprise_id,
						success:function(data){
							vm_corporation.employees = data.object;
						}
					})
					$(event.currentTarget).next().show();
				},
				delegate:function(enterpriseId){
					if(confirm('确认让其负责吗？')){
						$.ajax({
							url:path+'admin/auth/delegation/enterprise',
							type:'post',
							data:{
								employeeId:vm_corporation.employeeId,
								enterpriseId:enterpriseId
							},
							success:function(data){
								alert('指派成功！');
								content.load('corporation.html',function(){
									system.init_corporation();
								})
							}
						})
					}
				},
				cancelDelegation:function(employeeId,enterpriseId){
					if(confirm('确认解除吗？')){
						$.ajax({
							url:path+'admin/auth/cancel/delegation/enterprise',
							type:'post',
							data:{
								employeeId:employeeId,
								enterpriseId:enterpriseId
							},
							success:function(data){
								alert('解除成功！');
								content.load('corporation.html',function(){
									system.init_corporation();
								})
							}
						})
					}
				},
				remove:function(partnerId){
					if(confirm('确认删除吗？')){
						$.ajax({
							url:path+'admin/auth/cancel/corporation',
							type:'post',
							data:{
								enterpriseId:admin.enterprise_id,
								partnerId:partnerId
							},
							success:function(data){
								alert('删除成功！');
								content.load('corporation.html',function(){
									system.init_corporation();
								})
							}
						})
					}
				},
				addCorporationPage:function(){
					content.load('add-corporation.html',function(){
						system.init_add_corporation();
					})
				}
			}
		});
		vm_corporation.getCorporations();
	},
	init_add_corporation:function(){
		vm_add_corporation = new Vue({
			el:'#add_corporation_el',
			data:{
				corporations:[],
				data:{
					enterprise_id_1:admin.enterprise_id,
					enterprise_id_2:'',
					supply:0,
					stock:0,
					distribution:0
				}
			},
			methods:{
				getPretentialCorporations:function(){
					$.ajax({
						url:path+'admin/auth/pretential/corporation/'+admin.enterprise_id,
						success:function(data){
							vm_add_corporation.corporations = data.object;
						}
					})
				},
				addCorporation:function(){
					$.ajax({
						url:path+'admin/auth/corporation',
						type:'post',
						data:vm_add_corporation.data,
						success:function(data){
							if(data.status == '200'){
								alert('添加合作企业成功！');
								content.load('corporation.html',function(){
									system.init_corporation();
								})
							}else{
								alert(data.message);
							}
						}
					})
				}
			}
		});
		vm_add_corporation.getPretentialCorporations();
	},
	logout:function(){
		$.ajax({
			url:path+'admin/auth/logout',
			type:'post',
			success:function(data){
				alert('bye');
				location.href = '../admin-login.html';
			}
		})
	}
}