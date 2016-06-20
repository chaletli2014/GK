<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row">
	<div class="col-md-12">
		<div class="tab-content">
			<div class="tab-pane active" id="liftMainInfo">
				<div class="form-group">
					<label class="col-sm-2 control-label" for="deviceBrand">品牌</label>
					<div class="col-sm-4">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#deviceBrand").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="deviceBrand" name="deviceBrand">
							<option value="">请选择</option>
							<option value="aodi">奥迪电梯</option>
						</select>
					</div>
					<label class="col-sm-2 control-label" for="devicePurpose">用途</label>
					<div class="col-sm-4">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#devicePurpose").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="devicePurpose" name="devicePurpose">
							<option value="">请选择</option>
							<option value="yy">曳引式电梯</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="deviceStyle">型号</label>
					<div class="col-sm-4">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#deviceStyle").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="deviceStyle" name="deviceStyle">
							<option value="">请选择</option>
							<option value="standard">标配性</option>
						</select>
					</div>
					<label class="col-sm-2 control-label" for="deviceCT">载重量</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="deviceCT" name="deviceCT">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="deviceNS">额定速度</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="deviceNS" name="deviceNS">
					</div>
					<label class="col-sm-2 control-label" for="deviceQA">质保期限</label>
					<div class="col-sm-4">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#deviceQA").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="deviceQA" name="deviceQA">
							<option value="">请选择</option>
							<option value="12m">12个月</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="carSize">轿厢尺寸</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="carSize" name="carSize">
					</div>
					<label class="col-sm-2 control-label" for="carHeight">轿厢净高</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="carHeight" name="carHeight">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="doorSize">开门尺寸</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="doorSize" name="doorSize">
					</div>
					<label class="col-sm-2 control-label" for="mainPower">主机功率</label>
					<div class="col-sm-4">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#mainPower").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="mainPower" name="mainPower">
							<option value="">请选择</option>
							<option value="1000">1000</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="madeDate">生产日期</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="madeDate" name="madeDate">
					</div>
					<label class="col-sm-2 control-label" for="lifeTime">使用期限</label>
					<div class="col-sm-4">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#lifeTime").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="lifeTime" name="lifeTime">
							<option value="">请选择</option>
							<option value="10y">10年</option>
						</select>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>