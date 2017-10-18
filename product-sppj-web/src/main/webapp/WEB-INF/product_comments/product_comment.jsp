<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title></title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>

<body>
	<label>商品评价(1个待评价)</label>
	<table class="table">
		<tr>
			<td>商品信息</td>
			<td>购买时间</td>
			<td>评价状态</td>
		</tr>
		<tr>
			<td>佳能(Canon) MG2400超值彩色喷墨一体机(打印 复印 扫描)</td>
			<td>2014-10-25</td>
			<td>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="http://productcomments.com/restful/comment-label">发表评价</a>
						</h4>
					</div>
				</div>
			</td>
		</tr>
	</table>
	<div id="collapseOne" class="panel-collapse collapse ">
		<div class="panel-body">
			<form action="http://productcomments.com/restful/evaluate" enctype="multipart/form-data" method="post">
				<table class="table">
					<tr id="scores">
						<td><label for="exampleInputEmail1">评分:</label></td>
						<td>
							<label class="checkbox-inline"> 
								<input type="radio" name="score" value="1"> 1星
							</label> 
							<label class="checkbox-inline"> 
								<input type="radio" name="score" value="2"> 2星
							</label> 
							<label class="checkbox-inline"> 
								<input type="radio" name="score" value="3"> 3星
							</label> 
							<label class="checkbox-inline"> 
								<input type="radio" name="score" value="4"> 4星
							</label> 
							<label class="checkbox-inline"> 
								<input type="radio" name="score" value="5"> 5星
							</label>
						</td>
					</tr>
					<tr>
						<td><label for="exampleInputPassword1">标签:</label></td>
						<td id="labels">
							<c:forEach items="${labelList }" var="label">
				 				<input type="checkbox" name="labels" value="${label.id }">${label.labelString }
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td>心得:</td>
						<td>
							<div class="form-group">
								<textarea id="experiences" name="experience" class="form-control" rows="3"></textarea>
							</div>
						</td>
					</tr>
					<tr>
						<td>晒单:</td>
						<td>
							<div class="form-group">
								<label for="exampleInputFile">添加图片</label>
								 <input type="file" id="exampleInputFile">
							</div>
						</td>
					</tr>
					<tr>
						<td><button id="subForm" type="submit" class="btn btn-default">评价提交</button></td>
						<td>同步分享到 <label class="checkbox-inline"> <input
								type="checkbox" id="inlineCheckbox1" value="option1">
								新浪微博
						</label> <label class="checkbox-inline"> <input type="checkbox"
								id="inlineCheckbox2" value="option2"> 腾讯微博
						</label> <label class="checkbox-inline"> <input type="checkbox"
								id="inlineCheckbox3" value="option3"> 人人网
						</label> <label class="checkbox-inline"> <input type="checkbox"
								id="inlineCheckbox3" value="option3"> qq空间
						</label> <label class="checkbox-inline"> <input type="checkbox"
								id="inlineCheckbox3" value="option3"> 豆瓣网
						</label> <label class="checkbox-inline"> <input type="checkbox"
								id="inlineCheckbox3" value="option3"> 匿名评价
						</label>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
		/* $(function(){
			$("#subForm").click(function(){
				//准备提交参数
				var scores =  $("#scores input:checked").length;
				var labelIds = "";
				$("#labels input:checked").each(function(){
					labelIds = $(this).attr("lid") + ",";
				});
				var experiences = $("#experiences").val();
				//获取图片文件，用formdata包装，发送post请求
				var imageFile = $("#exampleInputFile").files()[0];
				var formData = new FormData();
				formData.append("imageFile", imageFile);
				$.post("http://127.0.0.1:8092/restful/evaluate", 
						{commentScore:scores,
						 commentLabelIds:labelIds,
						 experience:experiences,
						 image:formData});
				return false;
			})
		}); */
	</script>
</body>

</html>