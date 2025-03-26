<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','courier') || $check_field('add','courier') || $check_field('set','courier')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="快递员" prop="courier">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_courier(form['courier']) }}
							<!--<el-input id="business_name" v-model="form['courier']" placeholder="请输入快递员"-->
							<!--v-if="user_group === '管理员' || (form['apply_for_receipt_id'] && $check_field('set','courier')) || (!form['apply_for_receipt_id'] && $check_field('add','courier'))" :disabled="disabledObj['courier_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','courier')">{{form['courier']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['apply_for_receipt_id'] && $check_field('set','courier')) || (!form['apply_for_receipt_id'] && $check_field('add','courier'))" id="courier" v-model="form['courier']" :disabled="disabledObj['courier_isDisabled']">
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
			<el-col v-if="user_group === '管理员' || $check_field('get','courier_telephone') || $check_field('add','courier_telephone') || $check_field('set','courier_telephone')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="快递员电话" prop="courier_telephone">
					<el-input id="courier_telephone" v-model="form['courier_telephone']" placeholder="请输入快递员电话"
							  v-if="user_group === '管理员' || (form['apply_for_receipt_id'] && $check_field('set','courier_telephone')) || (!form['apply_for_receipt_id'] && $check_field('add','courier_telephone'))" :disabled="disabledObj['courier_telephone_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','courier_telephone')">{{form['courier_telephone']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','application_time') || $check_field('add','application_time') || $check_field('set','application_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="申请时间" prop="application_time">
					<el-date-picker :disabled="disabledObj['application_time_isDisabled']" v-if="user_group === '管理员' || (form['apply_for_receipt_id'] && $check_field('set','application_time')) || (!form['apply_for_receipt_id'] && $check_field('add','application_time'))" id="application_time"
						v-model="form['application_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','application_time')">{{form['application_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','courier_services_company') || $check_field('add','courier_services_company') || $check_field('set','courier_services_company')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="快递公司" prop="courier_services_company">
						<el-select v-if="user_group === '管理员' || (form['apply_for_receipt_id'] && $check_field('set','courier_services_company')) || (!form['apply_for_receipt_id'] && $check_field('add','courier_services_company'))" id="courier_services_company" v-model="form['courier_services_company']" :disabled="disabledObj['courier_services_company_isDisabled']">
							<el-option v-for="o in list_user_courier_services_company" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','courier_services_company')" id="courier_services_company" v-model="form['courier_services_company']" :disabled="true">
							<el-option v-for="o in list_user_courier_services_company" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','reason_for_application') || $check_field('add','reason_for_application') || $check_field('set','reason_for_application')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="申请原因" prop="reason_for_application">
					<el-input type="textarea" id="reason_for_application" v-model="form['reason_for_application']" placeholder="请输入申请原因"
						v-if="user_group === '管理员' || (form['apply_for_receipt_id'] && $check_field('set','reason_for_application')) || (!form['apply_for_receipt_id'] && $check_field('add','reason_for_application'))" :disabled="disabledObj['reason_for_application_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','reason_for_application')">{{form['reason_for_application']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="审核状态" prop="examine_state">
					<el-select id="examine_state" v-model="form['examine_state']"
						v-if="user_group === '管理员' || (form['examine_state'] && $check_examine()) || (!form['examine_state'] && $check_examine())">
						<el-option key="未审核" label="未审核" value="未审核"></el-option>
						<el-option key="已通过" label="已通过" value="已通过"></el-option>
						<el-option key="未通过" label="未通过" value="未通过"></el-option>
					</el-select>
					<div v-else-if="$check_field('get','examine_state')">{{form["examine_state"]}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="审核回复" prop="examine_reply">
					<el-input id="examine_reply" v-model="form['examine_reply']" placeholder="请输入审核回复"
						v-if="user_group === '管理员' || (form['examine_reply'] && $check_examine()) || (!form['examine_reply'] && $check_examine())"></el-input>
					<div v-else-if="$check_field('get','examine_reply')">{{form["examine_reply"]}}</div>
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
				field: "apply_for_receipt_id",
				url_add: "~/api/apply_for_receipt/add?",
				url_set: "~/api/apply_for_receipt/set?",
				url_get_obj: "~/api/apply_for_receipt/get_obj?",
				url_upload: "~/api/apply_for_receipt/upload?",

				query: {
					"apply_for_receipt_id": 0,
				},

				form: {
					"courier": 0, // 快递员
					"courier_telephone":'', // 快递员电话
					"application_time":'', // 申请时间
					"courier_services_company": 0, // 快递公司
					"reason_for_application":'', // 申请原因
					"examine_state": "未审核",
					"examine_reply": "",
					"apply_for_receipt_id": 0, // ID

				},
				disabledObj:{
					"courier_isDisabled": false,
					"courier_telephone_isDisabled": false,
					"application_time_isDisabled": false,
					"courier_services_company_isDisabled": false,
					"reason_for_application_isDisabled": false,
				},
				// 用户列表
				list_user_courier: [],
				// 用户组
				group_user_courier: "",
				// 用户列表
				list_user_courier_services_company: [],

			}
		},
		methods: {
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
			 * 获取快递公司用户列表
			 */
			async get_list_user_courier_services_company() {
                // if(this.user_group !== "管理员" && this.form["courier_services_company"] === 0) {
                //     this.form["courier_services_company"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=快递公司");
                if(json.result && json.result.list){
                    this.list_user_courier_services_company = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_courier_services_company(id){
				var obj = this.list_user_courier_services_company.getObj({"user_id":id});
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
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "快递员":
							if(param["courier"] > 0){
								param["courier"] = this.user.user_id;
							}
							break;
						case "快递公司":
							if(param["courier_services_company"] > 0){
								param["courier_services_company"] = this.user.user_id;
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
				this.form["application_time"] = this.$toTime(parseInt(this.form["application_time"]),"yyyy-MM-dd")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["application_time"]) > 9999){
					this.form["application_time"] = this.$toTime(parseInt(this.form["application_time"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/apply_for_receipt/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/apply_for_receipt/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/apply_for_receipt/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/apply_for_receipt/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/apply_for_receipt/view','get');
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
			this.get_list_user_courier();
			this.get_group_user_courier();
			this.get_list_user_courier_services_company();
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
