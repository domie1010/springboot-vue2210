<template>
	<div class="diy_home diy_list diy_express_logistics" id="diy_express_logistics_list">
		<!-- 列表 -->
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods diy_list_box_wrap" v-for="(o, i) in list" :key="i"
				:to="'/express_logistics/details?express_logistics_id=' + o['express_logistics_id']">
				<!-- 图片 -->
				<div class="diy_list_img_box" v-if="imgList.length" >
					<div class="diy_row" v-for="(item,index) in imgList" :key="item+index" v-show="$check_field('get',item.name,'/express_logistics/details') && +item.is_img_list">
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
						<div class="diy_row" :class="{[item.name]:true}" v-if="$check_field('get',item.name,'/express_logistics/details') && +item.is_img_list">
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
					<th class="diy_title" v-if="$check_field('get','sender')">寄件人
					</th>
					<th class="diy_title" v-if="$check_field('get','courier_number')">快递单号
					</th>
					<th class="diy_title" v-if="$check_field('get','courier_services_company')">快递公司
					</th>
					<th class="diy_title" v-if="$check_field('get','place_of_shipment')">发货地
					</th>
					<th class="diy_title" v-if="$check_field('get','destination')">目的地
					</th>
					<th class="diy_title" v-if="$check_field('get','company_account_number')">公司账号
					</th>
				</tr>
				<tr class="diy_row" v-for="(o,i) in list" :key="o+i">
					<td class="diy_field diy_uid" v-if="$check_field('get','sender')">
						<span>
							{{ get_user_name(o['sender']) }}
						</span>
					</td>
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
					<td class="diy_field diy_text" v-if="$check_field('get','place_of_shipment')">
						<span>
							{{ o["place_of_shipment"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','destination')">
						<span>
							{{ o["destination"] }}
						</span>
					</td>
					<td class="diy_field diy_uid" v-if="$check_field('get','company_account_number')">
						<span>
							{{ get_user_name(o['company_account_number']) }}
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
									title: "寄件人",
									name: "sender",
									type: "UID",
									is_img_list: "0"
								},
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
									title: "发货地",
									name: "place_of_shipment",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "目的地",
									name: "destination",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "公司账号",
									name: "company_account_number",
									type: "UID",
									is_img_list: "0"
								},
						],
						richList: [
								{
									title: "物流信息",
									name: "logistics_information",
									type: "编辑"
								},
						],
				// 用户列表
				list_user_sender: [],
				// 用户列表
				list_user_company_account_number: [],
			};
		},
		methods: {
			/**
			 * 获取学生用户列表
			 */
			async get_list_user_sender() {
				var json = await this.$get("~/api/user/get_list?user_group=学生");
				if(json.result && json.result.list){
					this.list_user_sender = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_name(id){
				var obj = this.list_user_sender.getObj({"user_id":id});
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
			 * 获取快递公司用户列表
			 */
			async get_list_user_company_account_number() {
				var json = await this.$get("~/api/user/get_list?user_group=快递公司");
				if(json.result && json.result.list){
					this.list_user_company_account_number = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_name(id){
				var obj = this.list_user_company_account_number.getObj({"user_id":id});
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
			this.get_list_user_sender();
			this.get_list_user_company_account_number();
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

