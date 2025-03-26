<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','sender') || $check_field('add','sender') || $check_field('set','sender')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="寄件人" prop="sender">
						<el-select v-if="user_group === '管理员' || (form['express_logistics_id'] && $check_field('set','sender')) || (!form['express_logistics_id'] && $check_field('add','sender'))" id="sender" v-model="form['sender']" :disabled="disabledObj['sender_isDisabled']">
							<el-option v-for="o in list_user_sender" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','sender')" id="sender" v-model="form['sender']" :disabled="true">
							<el-option v-for="o in list_user_sender" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','courier_number') || $check_field('add','courier_number') || $check_field('set','courier_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="快递单号" prop="courier_number">
					<el-input id="courier_number" v-model="form['courier_number']" placeholder="请输入快递单号"
							  v-if="user_group === '管理员' || (form['express_logistics_id'] && $check_field('set','courier_number')) || (!form['express_logistics_id'] && $check_field('add','courier_number'))" :disabled="disabledObj['courier_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','courier_number')">{{form['courier_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','courier_services_company') || $check_field('add','courier_services_company') || $check_field('set','courier_services_company')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="快递公司" prop="courier_services_company">
					<el-input id="courier_services_company" v-model="form['courier_services_company']" placeholder="请输入快递公司"
							  v-if="user_group === '管理员' || (form['express_logistics_id'] && $check_field('set','courier_services_company')) || (!form['express_logistics_id'] && $check_field('add','courier_services_company'))" :disabled="disabledObj['courier_services_company_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','courier_services_company')">{{form['courier_services_company']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','place_of_shipment') || $check_field('add','place_of_shipment') || $check_field('set','place_of_shipment')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="发货地" prop="place_of_shipment">
					<el-input id="place_of_shipment" v-model="form['place_of_shipment']" placeholder="请输入发货地"
							  v-if="user_group === '管理员' || (form['express_logistics_id'] && $check_field('set','place_of_shipment')) || (!form['express_logistics_id'] && $check_field('add','place_of_shipment'))" :disabled="disabledObj['place_of_shipment_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','place_of_shipment')">{{form['place_of_shipment']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','destination') || $check_field('add','destination') || $check_field('set','destination')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="目的地" prop="destination">
					<el-input id="destination" v-model="form['destination']" placeholder="请输入目的地"
							  v-if="user_group === '管理员' || (form['express_logistics_id'] && $check_field('set','destination')) || (!form['express_logistics_id'] && $check_field('add','destination'))" :disabled="disabledObj['destination_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','destination')">{{form['destination']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','company_account_number') || $check_field('add','company_account_number') || $check_field('set','company_account_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="公司账号" prop="company_account_number">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_company_account_number(form['company_account_number']) }}
							<!--<el-input id="business_name" v-model="form['company_account_number']" placeholder="请输入公司账号"-->
							<!--v-if="user_group === '管理员' || (form['express_logistics_id'] && $check_field('set','company_account_number')) || (!form['express_logistics_id'] && $check_field('add','company_account_number'))" :disabled="disabledObj['company_account_number_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','company_account_number')">{{form['company_account_number']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['express_logistics_id'] && $check_field('set','company_account_number')) || (!form['express_logistics_id'] && $check_field('add','company_account_number'))" id="company_account_number" v-model="form['company_account_number']" :disabled="disabledObj['company_account_number_isDisabled']">
								<el-option v-for="o in list_user_company_account_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','company_account_number')" id="company_account_number" v-model="form['company_account_number']" :disabled="true">
								<el-option v-for="o in list_user_company_account_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="company_account_number" v-model="form['company_account_number']" :disabled="disabledObj['company_account_number_isDisabled']">
							<el-option v-for="o in list_user_company_account_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','logistics_information') || $check_field('add','logistics_information') || $check_field('set','logistics_information')" :xs="24" :sm="24" :lg="24">
				<el-form-item label="物流信息" prop="logistics_information">
					<quill-editor v-model.number="form['logistics_information']"
						v-if="user_group === '管理员' || (form['express_logistics_id'] && $check_field('set','logistics_information')) || (!form['express_logistics_id'] && $check_field('add','logistics_information')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','logistics_information')" v-html="form['logistics_information']"></div>
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
				field: "express_logistics_id",
				url_add: "~/api/express_logistics/add?",
				url_set: "~/api/express_logistics/set?",
				url_get_obj: "~/api/express_logistics/get_obj?",
				url_upload: "~/api/express_logistics/upload?",

				query: {
					"express_logistics_id": 0,
				},

				form: {
					"sender": 0, // 寄件人
					"courier_number":'', // 快递单号
					"courier_services_company":'', // 快递公司
					"place_of_shipment":'', // 发货地
					"destination":'', // 目的地
					"company_account_number": 0, // 公司账号
					"logistics_information":'', // 物流信息
					"express_logistics_id": 0, // ID

				},
				disabledObj:{
					"sender_isDisabled": false,
					"courier_number_isDisabled": false,
					"courier_services_company_isDisabled": false,
					"place_of_shipment_isDisabled": false,
					"destination_isDisabled": false,
					"company_account_number_isDisabled": false,
					"logistics_information_isDisabled": false,
				},
				// 用户列表
				list_user_sender: [],
				// 用户列表
				list_user_company_account_number: [],
				// 用户组
				group_user_company_account_number: "",

			}
		},
		methods: {
			/**
			 * 获取学生用户列表
			 */
			async get_list_user_sender() {
                // if(this.user_group !== "管理员" && this.form["sender"] === 0) {
                //     this.form["sender"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生");
                if(json.result && json.result.list){
                    this.list_user_sender = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_sender(id){
				var obj = this.list_user_sender.getObj({"user_id":id});
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
			async get_list_user_company_account_number() {
                // if(this.user_group !== "管理员" && this.form["company_account_number"] === 0) {
                //     this.form["company_account_number"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=快递公司");
                if(json.result && json.result.list){
                    this.list_user_company_account_number = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取快递公司用户组
			 */
			async get_group_user_company_account_number() {
				this.form["company_account_number"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=快递公司");
				if(json.result && json.result.obj){
					this.group_user_company_account_number = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_company_account_number(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_company_account_number.source_table+"/get_obj?"
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
									if (arr[i]!=="company_account_number") {
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
			get_user_company_account_number(id){
				var obj = this.list_user_company_account_number.getObj({"user_id":id});
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
						case "寄件人":
							if(param["sender"] > 0){
								param["sender"] = this.user.user_id;
							}
							break;
						case "公司账号":
							if(param["company_account_number"] > 0){
								param["company_account_number"] = this.user.user_id;
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
					bl = this.$check_action('/express_logistics/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/express_logistics/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/express_logistics/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/express_logistics/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/express_logistics/view','get');
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
			this.get_list_user_sender();
			this.get_list_user_company_account_number();
			this.get_group_user_company_account_number();
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
