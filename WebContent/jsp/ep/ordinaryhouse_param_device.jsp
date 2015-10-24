<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row" style="margin-bottom: 20px;">
	<div class="form-group">
		<label class="col-sm-2 control-label" for="deviceName">组件类别</label>
		<div class="col-sm-6">
			<c:if test="${fn:length(goodsDics) > 0}">
				<script type="text/javascript">
					jQuery(document).ready(function($)
					{
						$("#deviceType").selectBoxIt().on('open', function()
						{
							$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
						});
					});
				</script>
				<select class="form-control" id="deviceType" name="deviceType">
					<c:forEach items="${goodsDics}" var="dic">
						<option value="${dic.dicCode}">${dic.dicName}</option>
					</c:forEach>
				</select>
			</c:if>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label" for="deviceName">设备名称</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" id="deviceName" name="deviceName" data-validate="required" data-message-required="名称不能为空" onblur="checkValues()">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label" for="manufacturerName">制造商</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" id="manufacturerName" name="manufacturerName" onblur="checkValues()">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label" for="brandName">品牌</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" id="brandName" name="brandName">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label" for="model">规格型号</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" id="model" name="model">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label" for="deviceNum">数量</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" id="deviceNum" name="deviceNum">
		</div>
	</div>
</div>
