<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','cabinet_number') || $check_field('add','cabinet_number') || $check_field('set','cabinet_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="柜号" prop="cabinet_number">
					<el-input id="cabinet_number" v-model="form['cabinet_number']" placeholder="请输入柜号"
							  v-if="user_group === '管理员' || (form['express_cabinet_id'] && $check_field('set','cabinet_number')) || (!form['express_cabinet_id'] && $check_field('add','cabinet_number'))" :disabled="disabledObj['cabinet_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','cabinet_number')">{{form['cabinet_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','lattice_type') || $check_field('add','lattice_type') || $check_field('set','lattice_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="格口类型" prop="lattice_type">
					<el-select id="lattice_type" v-model="form['lattice_type']"
						v-if="user_group === '管理员' || (form['express_cabinet_id'] && $check_field('set','lattice_type')) || (!form['express_cabinet_id'] && $check_field('add','lattice_type'))">
						<el-option v-for="o in list_lattice_type" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','lattice_type')">{{form['lattice_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','address') || $check_field('add','address') || $check_field('set','address')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="地址" prop="address">
					<el-input id="address" v-model="form['address']" placeholder="请输入地址"
							  v-if="user_group === '管理员' || (form['express_cabinet_id'] && $check_field('set','address')) || (!form['express_cabinet_id'] && $check_field('add','address'))" :disabled="disabledObj['address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','address')">{{form['address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','picture') || $check_field('add','picture') || $check_field('set','picture')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="图片" prop="picture">
					<el-upload :disabled="disabledObj['picture_isDisabled']" id="picture" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_picture"
						:show-file-list="false" v-if="user_group === '管理员' || (form['express_cabinet_id'] && $check_field('set','picture')) || (!form['express_cabinet_id'] && $check_field('add','picture'))">
						<img v-if="form['picture']" :src="$fullUrl(form['picture'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','picture')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['picture'])" :preview-src-list="[$fullUrl(form['picture'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "express_cabinet_id",
				url_add: "~/api/express_cabinet/add?",
				url_set: "~/api/express_cabinet/set?",
				url_get_obj: "~/api/express_cabinet/get_obj?",
				url_upload: "~/api/express_cabinet/upload?",

				query: {
					"express_cabinet_id": 0,
				},

				form: {
					"cabinet_number":'', // 柜号
					"lattice_type":'', // 格口类型
					"address":'', // 地址
					"picture":'', // 图片
					"express_cabinet_id": 0, // ID

				},
				disabledObj:{
					"cabinet_number_isDisabled": false,
					"lattice_type_isDisabled": false,
					"address_isDisabled": false,
					"picture_isDisabled": false,
				},
				//格口类型选项列表
				list_lattice_type: ['小格口','中格口','大格口'],

			}
		},
		methods: {
			/**
			 * 上传图片
			 * @param {Object} param图片参数
			 */
			upload_picture(param){
				this.uploadFile(param.file, "picture");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							if(dbKey === "charging_standard"){
								this.form['charging_rules'] = form[dbKey];
								this.disabledObj['charging_rules_isDisabled'] = true;
							};
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/express_cabinet/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/express_cabinet/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/express_cabinet/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/express_cabinet/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/express_cabinet/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
