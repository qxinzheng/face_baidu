<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 
  - Author(s): qxz
  - Date: 2018-01-05 11:04:33
  - Description:
-->
<head>
<title>百度人脸识别</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script src="<%=request.getContextPath()%>/common/nui/nui.js"
	type="text/javascript"></script>

</head>
<body>
	<input style="width: 100%; height: 300px" id="textarea1"
		class="mini-textarea" />
	<br />
	<br />
	<a class="nui-button" onclick="detect"> 人脸检测 </a>
	<a class="nui-button" onclick="match"> 人脸比对 </a>
	<a class="nui-button" onclick="identifyUser"> 人脸识别 </a>
	<a class="nui-button" onclick="verifyUser"> 人脸认证 </a>
	<a class="nui-button" onclick="addUser"> 人脸注册 </a>
	<a class="nui-button" onclick="updateUser"> 人脸更新 </a>
	<a class="nui-button" onclick="deleteUser"> 人脸删除 </a>
	<a class="nui-button" onclick="getUser"> 用户信息查询 </a>
	<a class="nui-button" onclick="getGroupList"> 组列表查询 </a>
	<a class="nui-button" onclick="getGroupUsers"> 组内用户列表查询 </a>
	<a class="nui-button" onclick="addGroupUser"> 组间复制用户 </a>


	<script type="text/javascript">
		nui.parse();
		var textarea1 = nui.get("textarea1");

		//人脸检测
		function detect() {
			var data = {
				max_face_num : 2,
				face_fields : "age,beauty,expression,faceshape,gender,glasses,landmark,race,qualities",
				image : "c:\\b.jpg"
			};
			var json = nui.encode({
				param1 : data
			});
			nui.ajax({
				url : "zzas.face.baidu.apicomponent.detectbiz.biz.ext",
				type : "post",
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					textarea1.setValue(nui.encode(text.out1));
				},
				error : function() {
					alert("error");
				}
			});
		}
		
		function match() {
			var data = {
				ext_fields : "qualities",
				image_liveness : ",faceliveness",
				types:"7,13",
				image1 : "c:\\b.jpg",
				image2 : "c:\\b.jpg"
			};
			var json = nui.encode({
				param1 : data
			});
			nui.ajax({
				url : "zzas.face.baidu.apicomponent.matchbiz.biz.ext",
				type : "post",
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					textarea1.setValue(nui.encode(text.out1));
				},
				error : function() {
					alert("error");
				}
			});
		}
		
		function identifyUser() {
			var data = {
				ext_fields : "faceliveness",
				user_top_num : "3",
				group_id:"group1,group2",
				image : "c:\\b.jpg"
			};
			var json = nui.encode({
				param1 : data
			});
			nui.ajax({
				url : "zzas.face.baidu.apicomponent.identifyUserbiz.biz.ext",
				type : "post",
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					textarea1.setValue(nui.encode(text.out1));
				},
				error : function() {
					alert("error");
				}
			});
		}
		
		function verifyUser() {
			var data = {
				top_num : "3",
				ext_fields : "faceliveness",
				uid:"user1",
				group_id:"group1,group2",
				image : "c:\\b.jpg"
			};
			var json = nui.encode({
				param1 : data
			});
			nui.ajax({
				url : "zzas.face.baidu.apicomponent.verifyUserbiz.biz.ext",
				type : "post",
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					textarea1.setValue(nui.encode(text.out1));
				},
				error : function() {
					alert("error");
				}
			});
		}
		
		function addUser() {
			var data = {
				action_type : "replace",
				uid:"user1",
				group_id:"group1,group2",
				user_info:"user's info",
				image : "c:\\b.jpg"
			};
			var json = nui.encode({
				param1 : data
			});
			nui.ajax({
				url : "zzas.face.baidu.apicomponent.addUserbiz.biz.ext",
				type : "post",
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					textarea1.setValue(nui.encode(text.out1));
				},
				error : function() {
					alert("error");
				}
			});
		}
		function updateUser() {
			var data = {
				action_type : "replace",
				uid:"user1",
				group_id:"group1,group2",
				user_info:"user's info",
				image : "c:\\b.jpg"
			};
			var json = nui.encode({
				param1 : data
			});
			nui.ajax({
				url : "zzas.face.baidu.apicomponent.updateUserbiz.biz.ext",
				type : "post",
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					textarea1.setValue(nui.encode(text.out1));
				},
				error : function() {
					alert("error");
				}
			});
		}
		
		function deleteUser() {
			var data = {
				uid:"user1",
				group_id:"group1,group2"
			};
			var json = nui.encode({
				param1 : data
			});
			nui.ajax({
				url : "zzas.face.baidu.apicomponent.deleteUserbiz.biz.ext",
				type : "post",
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					textarea1.setValue(nui.encode(text.out1));
				},
				error : function() {
					alert("error");
				}
			});
		}
		
		function getUser() {
			var data = {
				uid:"user1",
				group_id:"group1,group2"
			};
			var json = nui.encode({
				param1 : data
			});
			nui.ajax({
				url : "zzas.face.baidu.apicomponent.getUserbiz.biz.ext",
				type : "post",
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					textarea1.setValue(nui.encode(text.out1));
				},
				error : function() {
					alert("error");
				}
			});
		}
		
		function getGroupList() {
			var data = {
				start:0,
				end:50
			};
			var json = nui.encode({
				param1 : data
			});
			nui.ajax({
				url : "zzas.face.baidu.apicomponent.getGroupListbiz.biz.ext",
				type : "post",
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					textarea1.setValue(nui.encode(text.out1));
				},
				error : function() {
					alert("error");
				}
			});
		}
		
		function getGroupUsers() {
			var data = {
				group_id:"group2",
				start:0,
				end:50
			};
			var json = nui.encode({
				param1 : data
			});
			nui.ajax({
				url : "zzas.face.baidu.apicomponent.getGroupUsersbiz.biz.ext",
				type : "post",
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					textarea1.setValue(nui.encode(text.out1));
				},
				error : function() {
					alert("error");
				}
			});
		}
		
		function addGroupUser() {
			var data = {
				src_group_id:"group1",
				group_id:"group1,group2",
				uid:"user1"
			};
			var json = nui.encode({
				param1 : data
			});
			nui.ajax({
				url : "zzas.face.baidu.apicomponent.addGroupUserbiz.biz.ext",
				type : "post",
				data : json,
				cache : false,
				contentType : 'text/json',
				success : function(text) {
					textarea1.setValue(nui.encode(text.out1));
				},
				error : function() {
					alert("error");
				}
			});
		}
	</script>
</body>
</html>