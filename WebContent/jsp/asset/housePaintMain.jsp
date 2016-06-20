<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row">
	<div class="col-md-12">
		<div class="tab-content">
			<div class="tab-pane active" id="paintMainInfo">
				<div class="form-group">
					<label class="col-sm-2 control-label" for="paintBrand">材料品牌</label>
					<div class="col-sm-4">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#paintBrand").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="paintBrand" name="paintBrand">
							<option value="">请选择</option>
							<option value="paintBrand_gr">格瑞</option>
						</select>
					</div>
					<label class="col-sm-2 control-label" for="paintModel">材料型号</label>
					<div class="col-sm-4">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#paintModel").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="paintModel" name="paintModel">
							<option value="">请选择</option>
							<option value="paintModel_rr">柔韧型</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="paintStyle">材料类型</label>
					<div class="col-sm-4">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#paintStyle").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="paintStyle" name="paintStyle">
							<option value="">请选择</option>
							<option value="paintStyle_gfzfs">高分子防水涂料</option>
						</select>
					</div>
					<label class="col-sm-2 control-label" for="liquidType">液态类型</label>
					<div class="col-sm-4">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#liquidType").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="liquidType" name="liquidType">
							<option value="">请选择</option>
							<option value="liquidType_fy">反应型</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="filmFormer">成膜物性质</label>
					<div class="col-sm-4">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#filmFormer").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="filmFormer" name="filmFormer">
							<option value="">请选择</option>
							<option value="filmFormer_wj">无机</option>
							<option value="filmFormer_yj">有机</option>
						</select>
					</div>
					<label class="col-sm-2 control-label" for="paintStorage">储存器</label>
					<div class="col-sm-4">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#paintStorage").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="paintStorage" name="paintStorage">
							<option value="">请选择</option>
							<option value="paintStorage_12m">12月</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="solidProportion">固体含量</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="solidProportion" name="solidProportion">
					</div>
					<label class="col-sm-2 control-label" for="brushArea">涂刷面积</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="brushArea" name="brushArea">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="tensileStrength">拉伸强度</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="tensileStrength" name="tensileStrength">
					</div>
					<label class="col-sm-2 control-label" for="breakElongation">断裂伸长率</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="breakElongation" name="breakElongation">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="surfaceDryTime">表干时间</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="surfaceDryTime" name="surfaceDryTime">
					</div>
					<label class="col-sm-2 control-label" for="dryingTime">干燥时间</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="dryingTime" name="dryingTime">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="coatingFilmColor">漆膜颜色</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="coatingFilmColor" name="coatingFilmColor">
					</div>
					<label class="col-sm-2 control-label" for="originPlace">产地</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="originPlace" name="originPlace">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>