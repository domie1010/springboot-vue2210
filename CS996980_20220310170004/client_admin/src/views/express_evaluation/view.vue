<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','evaluation_number') || $check_field('add','evaluation_number') || $check_field('set','evaluation_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="评价编号" prop="evaluation_number">
					<el-input id="evaluation_number" v-model="form['evaluation_number']" placeholder="请输入评价编号"
							  v-if="user_group === '管理员' || (form['express_evaluation_id'] && $check_field('set','evaluation_number')) || (!form['express_evaluation_id'] && $check_field('add','evaluation_number'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','evaluation_number')">{{form['evaluation_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','evaluation_score') || $check_field('add','evaluation_score') || $check_field('set','evaluation_score')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="评价分数" prop="evaluation_score">
					<el-input id="evaluation_score" v-model="form['evaluation_score']" placeholder="请输入评价分数"
							  v-if="user_group === '管理员' || (form['express_evaluation_id'] && $check_field('set','evaluation_score')) || (!form['express_evaluation_id'] && $check_field('add','evaluation_score'))" :disabled="disabledObj['evaluation_score_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','evaluation_score')">{{form['evaluation_score']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','evaluator') || $check_field('add','evaluator') || $check_field('set','evaluator')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="评价人" prop="evaluator">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_evaluator(form['evaluator']) }}
							<!--<el-input id="business_name" v-model="form['evaluator']" placeholder="请输入评价人"-->
							<!--v-if="user_group === '管理员' || (form['express_evaluation_id'] && $check_field('set','evaluator')) || (!form['express_evaluation_id'] && $check_field('add','evaluator'))" :disabled="disabledObj['evaluator_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','evaluator')">{{form['evaluator']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['express_evaluation_id'] && $check_field('set','evaluator')) || (!form['express_evaluation_id'] && $check_field('add','evaluator'))" id="evaluator" v-model="form['evaluator']" :disabled="disabledObj['evaluator_isDisabled']">
								<el-option v-for="o in list_user_evaluator" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','evaluator')" id="evaluator" v-model="form['evaluator']" :disabled="true">
								<el-option v-for="o in list_user_evaluator" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="evaluator" v-model="form['evaluator']" :disabled="disabledObj['evaluator_isDisabled']">
							<el-option v-for="o in list_user_evaluator" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','evaluation_time') || $check_field('add','evaluation_time') || $check_field('set','evaluation_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="评价时间" prop="evaluation_time">
					<el-date-picker :disabled="disabledObj['evaluation_time_isDisabled']" v-if="user_group === '管理员' || (form['express_evaluation_id'] && $check_field('set','evaluation_time')) || (!form['express_evaluation_id'] && $check_field('add','evaluation_time'))" id="evaluation_time"
						v-model="form['evaluation_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','evaluation_time')">{{form['evaluation_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','evaluation_details') || $check_field('add','evaluation_details') || $check_field('set','evaluation_details')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="评价详情" prop="evaluation_details">
					<el-input type="textarea" id="evaluation_details" v-model="form['evaluation_details']" placeholder="请输入评价详情"
						v-if="user_group === '管理员' || (form['express_evaluation_id'] && $check_field('set','evaluation_details')) || (!form['express_evaluation_id'] && $check_field('add','evaluation_details'))" :disabled="disabledObj['evaluation_details_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','evaluation_details')">{{form['evaluation_details']}}</div>
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
				field: "express_evaluation_id",
				url_add: "~/api/express_evaluation/add?",
				url_set: "~/api/express_evaluation/set?",
				url_get_obj: "~/api/express_evaluation/get_obj?",
				url_upload: "~/api/express_evaluation/upload?",

				query: {
					"express_evaluation_id": 0,
				},

				form: {
					"evaluation_number":this.$get_stamp(), // 评价编号
					"evaluation_score":'', // 评价分数
					"evaluator": 0, // 评价人
					"evaluation_time":'', // 评价时间
					"evaluation_details":'', // 评价详情
					"express_evaluation_id": 0, // ID

				},
				disabledObj:{
					"evaluation_number_isDisabled": false,
					"evaluation_score_isDisabled": false,
					"evaluator_isDisabled": false,
					"evaluation_time_isDisabled": false,
					"evaluation_details_isDisabled": false,
				},
				// 用户列表
				list_user_evaluator: [],
				// 用户组
				group_user_evaluator: "",

			}
		},
		methods: {
			/**
			 * 获取学生用户列表
			 */
			async get_list_user_evaluator() {
                // if(this.user_group !== "管理员" && this.form["evaluator"] === 0) {
                //     this.form["evaluator"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生");
                if(json.result && json.result.list){
                    this.list_user_evaluator = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取学生用户组
			 */
			async get_group_user_evaluator() {
				this.form["evaluator"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=学生");
				if(json.result && json.result.obj){
					this.group_user_evaluator = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_evaluator(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_evaluator.source_table+"/get_obj?"
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
									if (arr[i]!=="evaluator") {
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
			get_user_evaluator(id){
				var obj = this.list_user_evaluator.getObj({"user_id":id});
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
						case "评价人":
							if(param["evaluator"] > 0){
								param["evaluator"] = this.user.user_id;
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
				this.form["evaluation_time"] = this.$toTime(parseInt(this.form["evaluation_time"]),"yyyy-MM-dd")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["evaluation_time"]) > 9999){
					this.form["evaluation_time"] = this.$toTime(parseInt(this.form["evaluation_time"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/express_evaluation/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/express_evaluation/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/express_evaluation/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/express_evaluation/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/express_evaluation/view','get');
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
			this.get_list_user_evaluator();
			this.get_group_user_evaluator();
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
