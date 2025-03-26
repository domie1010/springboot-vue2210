<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','sender') || $check_field('add','sender') || $check_field('set','sender')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="寄件人" prop="sender">
						<el-select v-if="user_group === '管理员' || (form['express_charges_id'] && $check_field('set','sender')) || (!form['express_charges_id'] && $check_field('add','sender'))" id="sender" v-model="form['sender']" :disabled="disabledObj['sender_isDisabled']">
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
			<el-col v-if="user_group === '管理员' || $check_field('get','sender_telephone') || $check_field('add','sender_telephone') || $check_field('set','sender_telephone')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="寄件人电话" prop="sender_telephone">
					<el-input id="sender_telephone" v-model="form['sender_telephone']" placeholder="请输入寄件人电话"
							  v-if="user_group === '管理员' || (form['express_charges_id'] && $check_field('set','sender_telephone')) || (!form['express_charges_id'] && $check_field('add','sender_telephone'))" :disabled="disabledObj['sender_telephone_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','sender_telephone')">{{form['sender_telephone']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','item_type') || $check_field('add','item_type') || $check_field('set','item_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="物品类型" prop="item_type">
					<el-input id="item_type" v-model="form['item_type']" placeholder="请输入物品类型"
							  v-if="user_group === '管理员' || (form['express_charges_id'] && $check_field('set','item_type')) || (!form['express_charges_id'] && $check_field('add','item_type'))" :disabled="disabledObj['item_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','item_type')">{{form['item_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','article_weight') || $check_field('add','article_weight') || $check_field('set','article_weight')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="物品重量" prop="article_weight">
					<el-input id="article_weight" v-model="form['article_weight']" placeholder="请输入物品重量"
							  v-if="user_group === '管理员' || (form['express_charges_id'] && $check_field('set','article_weight')) || (!form['express_charges_id'] && $check_field('add','article_weight'))" :disabled="disabledObj['article_weight_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','article_weight')">{{form['article_weight']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','mailing_address') || $check_field('add','mailing_address') || $check_field('set','mailing_address')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="寄件地址" prop="mailing_address">
					<el-input id="mailing_address" v-model="form['mailing_address']" placeholder="请输入寄件地址"
							  v-if="user_group === '管理员' || (form['express_charges_id'] && $check_field('set','mailing_address')) || (!form['express_charges_id'] && $check_field('add','mailing_address'))" :disabled="disabledObj['mailing_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','mailing_address')">{{form['mailing_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','price') || $check_field('add','price') || $check_field('set','price')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="价格" prop="price">
					<el-input-number id="price" v-model.number="form['price']"
						v-if="user_group === '管理员' || (form['express_charges_id'] && $check_field('set','price')) || (!form['express_charges_id'] && $check_field('add','price'))"></el-input-number>
					<div v-else-if="$check_field('get','price')">{{form['price']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','total_amount') || $check_field('add','total_amount') || $check_field('set','total_amount')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="总金额" prop="total_amount">
					<el-input id="total_amount" v-model="form['total_amount']" placeholder="请输入总金额"
							  v-if="user_group === '管理员' || (form['express_charges_id'] && $check_field('set','total_amount')) || (!form['express_charges_id'] && $check_field('add','total_amount'))"  @focus="set_total_amount()" :disabled="disabledObj['total_amount_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','total_amount')">{{form['total_amount']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','collection_time') || $check_field('add','collection_time') || $check_field('set','collection_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="收取时间" prop="collection_time">
					<el-date-picker :disabled="disabledObj['collection_time_isDisabled']" v-if="user_group === '管理员' || (form['express_charges_id'] && $check_field('set','collection_time')) || (!form['express_charges_id'] && $check_field('add','collection_time'))" id="collection_time"
						v-model="form['collection_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','collection_time')">{{form['collection_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','courier') || $check_field('add','courier') || $check_field('set','courier')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="快递员" prop="courier">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_courier(form['courier']) }}
							<!--<el-input id="business_name" v-model="form['courier']" placeholder="请输入快递员"-->
							<!--v-if="user_group === '管理员' || (form['express_charges_id'] && $check_field('set','courier')) || (!form['express_charges_id'] && $check_field('add','courier'))" :disabled="disabledObj['courier_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','courier')">{{form['courier']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['express_charges_id'] && $check_field('set','courier')) || (!form['express_charges_id'] && $check_field('add','courier'))" id="courier" v-model="form['courier']" :disabled="disabledObj['courier_isDisabled']">
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
			<el-col v-if="user_group === '管理员' || $check_field('get','courier_number') || $check_field('add','courier_number') || $check_field('set','courier_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="快递单号" prop="courier_number">
					<el-input id="courier_number" v-model="form['courier_number']" placeholder="请输入快递单号"
							  v-if="user_group === '管理员' || (form['express_charges_id'] && $check_field('set','courier_number')) || (!form['express_charges_id'] && $check_field('add','courier_number'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','courier_number')">{{form['courier_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','courier_services_company') || $check_field('add','courier_services_company') || $check_field('set','courier_services_company')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="快递公司" prop="courier_services_company">
					<el-input id="courier_services_company" v-model="form['courier_services_company']" placeholder="请输入快递公司"
							  v-if="user_group === '管理员' || (form['express_charges_id'] && $check_field('set','courier_services_company')) || (!form['express_charges_id'] && $check_field('add','courier_services_company'))" :disabled="disabledObj['courier_services_company_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','courier_services_company')">{{form['courier_services_company']}}</div>
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
				field: "express_charges_id",
				url_add: "~/api/express_charges/add?",
				url_set: "~/api/express_charges/set?",
				url_get_obj: "~/api/express_charges/get_obj?",
				url_upload: "~/api/express_charges/upload?",

				query: {
					"express_charges_id": 0,
				},

				form: {
					"sender": 0, // 寄件人
					"sender_telephone":'', // 寄件人电话
					"item_type":'', // 物品类型
					"article_weight":'', // 物品重量
					"mailing_address":'', // 寄件地址
					"price":0, // 价格
					"total_amount":'', // 总金额
					"collection_time":'', // 收取时间
					"courier": 0, // 快递员
					"courier_number":this.$get_stamp(), // 快递单号
					"courier_services_company":'', // 快递公司
					"express_charges_id": 0, // ID

				},
				disabledObj:{
					"sender_isDisabled": false,
					"sender_telephone_isDisabled": false,
					"item_type_isDisabled": false,
					"article_weight_isDisabled": false,
					"mailing_address_isDisabled": false,
					"total_amount_isDisabled": false,
					"collection_time_isDisabled": false,
					"courier_isDisabled": false,
					"courier_number_isDisabled": false,
					"courier_services_company_isDisabled": false,
				},
				// 用户列表
				list_user_sender: [],
				// 用户列表
				list_user_courier: [],
				// 用户组
				group_user_courier: "",

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
			set_total_amount(){
				this.form.total_amount =this.form.article_weight * this.form.price
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
						case "寄件人":
							if(param["sender"] > 0){
								param["sender"] = this.user.user_id;
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
				this.form["collection_time"] = this.$toTime(parseInt(this.form["collection_time"]),"yyyy-MM-dd")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["collection_time"]) > 9999){
					this.form["collection_time"] = this.$toTime(parseInt(this.form["collection_time"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/express_charges/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/express_charges/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/express_charges/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/express_charges/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/express_charges/view','get');
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
			this.get_list_user_courier();
			this.get_group_user_courier();
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
