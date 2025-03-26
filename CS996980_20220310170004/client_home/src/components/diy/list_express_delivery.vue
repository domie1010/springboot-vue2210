<template>
	<div class="diy_home diy_list diy_express_delivery" id="diy_express_delivery_list">
		<!-- 列表 -->
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods diy_list_box_wrap" v-for="(o, i) in list" :key="i"
				:to="'/express_delivery/details?express_delivery_id=' + o['express_delivery_id']">
				<!-- 图片 -->
				<div class="diy_list_img_box" v-if="imgList.length" >
					<div class="diy_row" v-for="(item,index) in imgList" :key="item+index" v-show="$check_field('get',item.name,'/express_delivery/details') && +item.is_img_list">
						<div class="diy_title diy_list_img_title">
							<span>{{item.title}}:</span>
						</div>
						<div class="diy_field diy_img">
							<img :src="$fullUrl(o[item.name])" style="width:100%;height:100%" />
						</div>
					</div>
				</div>
				<!-- 内容 -->
				<div class="diy_list_item_box">
					<div class="diy_list_item_content" v-for="(item,index) in showItemList" :key="item+index">
						<div class="diy_row" :class="{[item.name]:true}" v-if="$check_field('get',item.name,'/express_delivery/details') && +item.is_img_list">
							<div class="diy_title">
								<span>{{item.title}}:</span>
							</div>
							<div class="diy_field diy_text">
								<span v-if="item.type == 'UID'" v-text="get_user_name(o[item.name])"></span>
								<span v-else-if="item.type == '日期'" v-text="$toTime(o[item.name],'yyyy-MM-dd')"></span>
								<span v-else-if="item.type == '时间'" v-text="$toTime(o[item.name],'hh:mm:ss')"></span>
								<span v-else-if="item.type == '日长'" v-text="$toTime(o[item.name],'yyyy-MM-dd hh:mm:ss')"></span>
								<span v-else v-text="o[item.name]"></span>
							</div>
						</div>
					</div>
				</div>
			</router-link>
		</div>
		<!-- 表格 -->
		<div class="diy_view_table" v-else>
			<table class="diy_table">
				<tr class="diy_row">
					<th class="diy_title" v-if="$check_field('get','courier_number')">快递单号
					</th>
					<th class="diy_title" v-if="$check_field('get','courier_services_company')">快递公司
					</th>
					<th class="diy_title" v-if="$check_field('get','express_quantity')">快递数量
					</th>
					<th class="diy_title" v-if="$check_field('get','student_name')">学生姓名
					</th>
					<th class="diy_title" v-if="$check_field('get','student_account')">学生账号
					</th>
					<th class="diy_title" v-if="$check_field('get','student_telephone')">学生电话
					</th>
					<th class="diy_title" v-if="$check_field('get','courier_telephone')">快递员电话
					</th>
					<th class="diy_title" v-if="$check_field('get','courier')">快递员
					</th>
					<th class="diy_title" v-if="$check_field('get','cabinet_number')">柜号
					</th>
					<th class="diy_title" v-if="$check_field('get','storage_time')">存放时间
					</th>
					<th class="diy_title" v-if="$check_field('get','pick_up_code')">取件码
					</th>
				</tr>
				<tr class="diy_row" v-for="(o,i) in list" :key="o+i">
					<td class="diy_field diy_text" v-if="$check_field('get','courier_number')">
						<span>
							{{ o["courier_number"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','courier_services_company')">
						<span>
							{{ o["courier_services_company"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','express_quantity')">
						<span>
							{{ o["express_quantity"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','student_name')">
						<span>
							{{ o["student_name"] }}
						</span>
					</td>
					<td class="diy_field diy_uid" v-if="$check_field('get','student_account')">
						<span>
							{{ get_user_name(o['student_account']) }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','student_telephone')">
						<span>
							{{ o["student_telephone"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','courier_telephone')">
						<span>
							{{ o["courier_telephone"] }}
						</span>
					</td>
					<td class="diy_field diy_uid" v-if="$check_field('get','courier')">
						<span>
							{{ get_user_name(o['courier']) }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','cabinet_number')">
						<span>
							{{ o["cabinet_number"] }}
						</span>
					</td>
					<td class="diy_field diy_date" v-if="$check_field('get','storage_time')">
						<span>
							{{ $toTime(o["storage_time"] ,"yyyy-MM-dd") }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','pick_up_code')">
						<span>
							{{ o["pick_up_code"] }}
						</span>
					</td>
				</tr>
			</table>
		</div>
	</div>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default: function() {
					return [];
				},
			},
			show: {
				type: Boolean,
				default: function(){
					return true;
				}
			}
		},
		data() {
			return {
						imgList: [
						],
						itemList: [
								{
									title: "快递单号",
									name: "courier_number",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "快递公司",
									name: "courier_services_company",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "快递数量",
									name: "express_quantity",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "学生姓名",
									name: "student_name",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "学生账号",
									name: "student_account",
									type: "UID",
									is_img_list: "0"
								},
								{
									title: "学生电话",
									name: "student_telephone",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "快递员电话",
									name: "courier_telephone",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "快递员",
									name: "courier",
									type: "UID",
									is_img_list: "0"
								},
								{
									title: "柜号",
									name: "cabinet_number",
									type: "下寻",
									is_img_list: "0"
								},
								{
									title: "存放时间",
									name: "storage_time",
									type: "日期",
									is_img_list: "0"
								},
								{
									title: "取件码",
									name: "pick_up_code",
									type: "文本",
									is_img_list: "0"
								},
						],
						richList: [
						],
				// 用户列表
				list_user_student_account: [],
				// 用户列表
				list_user_courier: [],
			};
		},
		methods: {
			/**
			 * 获取学生用户列表
			 */
			async get_list_user_student_account() {
				var json = await this.$get("~/api/user/get_list?user_group=学生");
				if(json.result && json.result.list){
					this.list_user_student_account = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_name(id){
				var obj = this.list_user_student_account.getObj({"user_id":id});
				var ret = "";
				if(obj){
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
			/**
			 * 获取快递员用户列表
			 */
			async get_list_user_courier() {
				var json = await this.$get("~/api/user/get_list?user_group=快递员");
				if(json.result && json.result.list){
					this.list_user_courier = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_name(id){
				var obj = this.list_user_courier.getObj({"user_id":id});
				var ret = "";
				if(obj){
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
		},
		created() {
			this.get_list_user_student_account();
			this.get_list_user_courier();
		},
		computed:{
			showItemList(){
				let arr = [];
				let _type = ["视频","音频","文件"];
				this.itemList.forEach(item => {
					if(_type.indexOf(item.type) === -1 && !!+item.is_img_list){
						arr.push(item)
					}
				})
				return arr.slice(0,4);
			}
		}
	};
</script>

<style scoped>
	.media {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		flex-basis: 75%;
		min-height: 10rem;
	}

	.goods {
		display: flex;
		width: calc(25% - 1rem);
		margin: 0.5rem;
		padding: 0.5rem;
		flex-direction: column;
		justify-content: space-between;
		background-color: white;
		border-radius: 0.5rem;
	}

	.goods:hover {
		border: 0.2rem solid #909399;
		box-shadow: 0 0.1rem 0.3rem rgba(0, 0, 0, 0.15);
	}

	.goods:hover img {
		filter: blur(1px);
	}

	.price {
		font-size: 1rem;
		margin-right: 3px;
	}

	.price_ago {
		text-decoration: line-through;
		font-size: 0.5rem;
		color: #999;

	}

	.title {
		word-break: break-all;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		font-weight: 700;
		padding: .25rem;
	}

	.icon_cart {
		color: #FF5722;
		float: right;
	}

	@media (max-width: 992px) {

		.goods {
			width: calc(33% - 1rem);
			;
		}

	}

	@media (max-width: 768px) {

		.goods {
			width: calc(50% - 1rem);
			;
		}

	}
</style>

