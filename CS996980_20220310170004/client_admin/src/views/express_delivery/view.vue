<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','courier_number') || $check_field('add','courier_number') || $check_field('set','courier_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="快递单号" prop="courier_number">
					<el-input id="courier_number" v-model="form['courier_number']" placeholder="请输入快递单号"
							  v-if="user_group === '管理员' || (form['express_delivery_id'] && $check_field('set','courier_number')) || (!form['express_delivery_id'] && $check_field('add','courier_number'))" :disabled="disabledObj['courier_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','courier_number')">{{form['courier_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','courier_services_company') || $check_field('add','courier_services_company') || $check_field('set','courier_services_company')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="快递公司" prop="courier_services_company">
					<el-input id="courier_services_company" v-model="form['courier_services_company']" placeholder="请输入快递公司"
							  v-if="user_group === '管理员' || (form['express_delivery_id'] && $check_field('set','courier_services_company')) || (!form['express_delivery_id'] && $check_field('add','courier_services_company'))" :disabled="disabledObj['courier_services_company_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','courier_services_company')">{{form['courier_services_company']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','express_quantity') || $check_field('add','express_quantity') || $check_field('set','express_quantity')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="快递数量" prop="express_quantity">
					<el-input id="express_quantity" v-model="form['express_quantity']" placeholder="请输入快递数量"
							  v-if="user_group === '管理员' || (form['express_delivery_id'] && $check_field('set','express_quantity')) || (!form['express_delivery_id'] && $check_field('add','express_quantity'))" :disabled="disabledObj['express_quantity_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','express_quantity')">{{form['express_quantity']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_name') || $check_field('add','student_name') || $check_field('set','student_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学生姓名" prop="student_name">
					<el-input id="student_name" v-model="form['student_name']" placeholder="请输入学生姓名"
							  v-if="user_group === '管理员' || (form['express_delivery_id'] && $check_field('set','student_name')) || (!form['express_delivery_id'] && $check_field('add','student_name'))" :disabled="disabledObj['student_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_name')">{{form['student_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_account') || $check_field('add','student_account') || $check_field('set','student_account')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学生账号" prop="student_account">
						<el-select v-if="user_group === '管理员' || (form['express_delivery_id'] && $check_field('set','student_account')) || (!form['express_delivery_id'] && $check_field('add','student_account'))" id="student_account" v-model="form['student_account']" :disabled="disabledObj['student_account_isDisabled']">
							<el-option v-for="o in list_user_student_account" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','student_account')" id="student_account" v-model="form['student_account']" :disabled="true">
							<el-option v-for="o in list_user_student_account" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_telephone') || $check_field('add','student_telephone') || $check_field('set','student_telephone')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学生电话" prop="student_telephone">
					<el-input id="student_telephone" v-model="form['student_telephone']" placeholder="请输入学生电话"
							  v-if="user_group === '管理员' || (form['express_delivery_id'] && $check_field('set','student_telephone')) || (!form['express_delivery_id'] && $check_field('add','student_telephone'))" :disabled="disabledObj['student_telephone_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_telephone')">{{form['student_telephone']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','courier_telephone') || $check_field('add','courier_telephone') || $check_field('set','courier_telephone')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="快递员电话" prop="courier_telephone">
					<el-input id="courier_telephone" v-model="form['courier_telephone']" placeholder="请输入快递员电话"
							  v-if="user_group === '管理员' || (form['express_delivery_id'] && $check_field('set','courier_telephone')) || (!form['express_delivery_id'] && $check_field('add','courier_telephone'))" :disabled="disabledObj['courier_telephone_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','courier_telephone')">{{form['courier_telephone']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','courier') || $check_field('add','courier') || $check_field('set','courier')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="快递员" prop="courier">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_courier(form['courier']) }}
							<!--<el-input id="business_name" v-model="form['courier']" placeholder="请输入快递员"-->
							<!--v-if="user_group === '管理员' || (form['express_delivery_id'] && $check_field('set','courier')) || (!form['express_delivery_id'] && $check_field('add','courier'))" :disabled="disabledObj['courier_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','courier')">{{form['courier']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['express_delivery_id'] && $check_field('set','courier')) || (!form['express_delivery_id'] && $check_field('add','courier'))" id="courier" v-model="form['courier']" :disabled="disabledObj['courier_isDisabled']">
								<el-option v-for="o in list_user_courier" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','courier')" id="courier" v-model="form['courier']" :disabled="true">
								<el-option v-for="o in list_user_courier" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="courier" v-model="form['courier']" :disabled="disabledObj['courier_isDisabled']">
							<el-option v-for="o in list_user_courier" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cabinet_number') || $check_field('add','cabinet_number') || $check_field('set','cabinet_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="柜号" prop="cabinet_number">
					<el-select id="cabinet_number" v-model="form['cabinet_number']"
						v-if="user_group === '管理员' || (form['express_delivery_id'] && $check_field('set','cabinet_number')) || (!form['express_delivery_id'] && $check_field('add','cabinet_number'))">
						<el-option v-for="o in list_cabinet_number" :key="o['cabinet_number']" :label="o['cabinet_number']"
							:value="o['cabinet_number']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','cabinet_number')">{{form['cabinet_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','storage_time') || $check_field('add','storage_time') || $check_field('set','storage_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="存放时间" prop="storage_time">
					<el-date-picker :disabled="disabledObj['storage_time_isDisabled']" v-if="user_group === '管理员' || (form['express_delivery_id'] && $check_field('set','storage_time')) || (!form['express_delivery_id'] && $check_field('add','storage_time'))" id="storage_time"
						v-model="form['storage_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','storage_time')">{{form['storage_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','pick_up_code') || $check_field('add','pick_up_code') || $check_field('set','pick_up_code')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="取件码" prop="pick_up_code">
					<el-input id="pick_up_code" v-model="form['pick_up_code']" placeholder="请输入取件码"
							  v-if="user_group === '管理员' || (form['express_delivery_id'] && $check_field('set','pick_up_code')) || (!form['express_delivery_id'] && $check_field('add','pick_up_code'))" :disabled="disabledObj['pick_up_code_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','pick_up_code')">{{form['pick_up_code']}}</div>
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
				field: "express_delivery_id",
				url_add: "~/api/express_delivery/add?",
				url_set: "~/api/express_delivery/set?",
				url_get_obj: "~/api/express_delivery/get_obj?",
				url_upload: "~/api/express_delivery/upload?",

				query: {
					"express_delivery_id": 0,
				},

				form: {
					"courier_number":'', // 快递单号
					"courier_services_company":'', // 快递公司
					"express_quantity":'', // 快递数量
					"student_name":'', // 学生姓名
					"student_account": 0, // 学生账号
					"student_telephone":'', // 学生电话
					"courier_telephone":'', // 快递员电话
					"courier": 0, // 快递员
					"cabinet_number":'', // 柜号
					"storage_time":'', // 存放时间
					"pick_up_code":'', // 取件码
					"express_delivery_id": 0, // ID

				},
				disabledObj:{
					"courier_number_isDisabled": false,
					"courier_services_company_isDisabled": false,
					"express_quantity_isDisabled": false,
					"student_name_isDisabled": false,
					"student_account_isDisabled": false,
					"student_telephone_isDisabled": false,
					"courier_telephone_isDisabled": false,
					"courier_isDisabled": false,
					"cabinet_number_isDisabled": false,
					"storage_time_isDisabled": false,
					"pick_up_code_isDisabled": false,
				},
				// 用户列表
				list_user_student_account: [],
				// 用户列表
				list_user_courier: [],
				// 用户组
				group_user_courier: "",
				//柜号选项列表
				list_cabinet_number: [],

			}
		},
		methods: {
			/**
			 * 获取学生用户列表
			 */
			async get_list_user_student_account() {
                // if(this.user_group !== "管理员" && this.form["student_account"] === 0) {
                //     this.form["student_account"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生");
                if(json.result && json.result.list){
                    this.list_user_student_account = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_student_account(id){
				var obj = this.list_user_student_account.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			/**
			 * 获取快递员用户列表
			 */
			async get_list_user_courier() {
                // if(this.user_group !== "管理员" && this.form["courier"] === 0) {
                //     this.form["courier"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=快递员");
                if(json.result && json.result.list){
                    this.list_user_courier = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取快递员用户组
			 */
			async get_group_user_courier() {
				this.form["courier"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=快递员");
				if(json.result && json.result.obj){
					this.group_user_courier = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_courier(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_courier.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="courier") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_courier(id){
				var obj = this.list_user_courier.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			/**
			 * 获取柜号列表
			 */
			async get_list_cabinet_number() {
				var json = await this.$get("~/api/express_cabinet/get_list?");
				if(json.result && json.result.list){
					this.list_cabinet_number = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "学生账号":
							if(param["student_account"] > 0){
								param["student_account"] = this.user.user_id;
							}
							break;
						case "快递员":
							if(param["courier"] > 0){
								param["courier"] = this.user.user_id;
							}
							break;
					}
				}
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
				this.form["storage_time"] = this.$toTime(parseInt(this.form["storage_time"]),"yyyy-MM-dd")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["storage_time"]) > 9999){
					this.form["storage_time"] = this.$toTime(parseInt(this.form["storage_time"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/express_delivery/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/express_delivery/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/express_delivery/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/express_delivery/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/express_delivery/view','get');
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
			this.get_list_user_student_account();
			this.get_list_user_courier();
			this.get_group_user_courier();
			this.get_list_cabinet_number();
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
