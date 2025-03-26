<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="校园资讯"
				source_table="article"
			  ></list_result_search>
				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="交流论坛"
				source_table="forum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/express_information/list', 'get')"
				:list="result_express_information_courier_number"
				title="快递信息快递单号"
				source_table="express_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/express_information/list', 'get')"
				:list="result_express_information_courier_services_company"
				title="快递信息快递公司"
				source_table="express_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_gender"
				title="学生性别"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_student_number"
				title="学生学号"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/courier/list', 'get')"
				:list="result_courier_gender"
				title="快递员性别"
				source_table="courier"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/express_cabinet/list', 'get')"
				:list="result_express_cabinet_cabinet_number"
				title="快递柜柜号"
				source_table="express_cabinet"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/express_pick_up/list', 'get')"
				:list="result_express_pick_up_courier_number"
				title="快递取件快递单号"
				source_table="express_pick_up"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/express_pick_up/list', 'get')"
				:list="result_express_pick_up_courier_services_company"
				title="快递取件快递公司"
				source_table="express_pick_up"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/use_express_delivery/list', 'get')"
				:list="result_use_express_delivery_sender_telephone"
				title="寄快递寄件人电话"
				source_table="use_express_delivery"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/apply_for_receipt/list', 'get')"
				:list="result_apply_for_receipt_courier_telephone"
				title="申请接单快递员电话"
				source_table="apply_for_receipt"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/courier_services_company/list', 'get')"
				:list="result_courier_services_company_corporate_name"
				title="快递公司公司名称"
				source_table="courier_services_company"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/express_delivery/list', 'get')"
				:list="result_express_delivery_courier_number"
				title="快递配送快递单号"
				source_table="express_delivery"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/express_delivery/list', 'get')"
				:list="result_express_delivery_courier_services_company"
				title="快递配送快递公司"
				source_table="express_delivery"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/express_charges/list', 'get')"
				:list="result_express_charges_sender_telephone"
				title="快递收取费用寄件人电话"
				source_table="express_charges"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/express_evaluation/list', 'get')"
				:list="result_express_evaluation_evaluation_number"
				title="快递评价评价编号"
				source_table="express_evaluation"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/express_logistics/list', 'get')"
				:list="result_express_logistics_courier_number"
				title="快递物流快递单号"
				source_table="express_logistics"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
			"result_express_information_courier_number":[],
			"result_express_information_courier_services_company":[],
			"result_student_gender":[],
			"result_student_student_number":[],
			"result_courier_gender":[],
			"result_express_cabinet_cabinet_number":[],
			"result_express_pick_up_courier_number":[],
			"result_express_pick_up_courier_services_company":[],
			"result_use_express_delivery_sender_telephone":[],
			"result_apply_for_receipt_courier_telephone":[],
			"result_courier_services_company_corporate_name":[],
			"result_express_delivery_courier_number":[],
			"result_express_delivery_courier_services_company":[],
			"result_express_charges_sender_telephone":[],
			"result_express_evaluation_evaluation_number":[],
			"result_express_logistics_courier_number":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取交流论坛
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},
	/**
	 * 获取courier_number
	 */
	get_express_information_courier_number(){
		this.$get("~/api/express_information/get_list?like=0", { page: 1, size: 10, "courier_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_express_information_courier_number = json.result.list;
			result_express_information_courier_number.map(o => o.title = o['courier_number'])
	  			this.result_express_information_courier_number = result_express_information_courier_number
		 	}
		});
	},
	/**
	 * 获取courier_services_company
	 */
	get_express_information_courier_services_company(){
		this.$get("~/api/express_information/get_list?like=0", { page: 1, size: 10, "courier_services_company": this.query.word }, (json) => {
		  if (json.result) {
			var result_express_information_courier_services_company = json.result.list;
			result_express_information_courier_services_company.map(o => o.title = o['courier_services_company'])
	  			this.result_express_information_courier_services_company = result_express_information_courier_services_company
		 	}
		});
	},
	/**
	 * 获取gender
	 */
	get_student_gender(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_gender = json.result.list;
			result_student_gender.map(o => o.title = o['gender'])
	  			this.result_student_gender = result_student_gender
		 	}
		});
	},
	/**
	 * 获取student_number
	 */
	get_student_student_number(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "student_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_student_number = json.result.list;
			result_student_student_number.map(o => o.title = o['student_number'])
	  			this.result_student_student_number = result_student_student_number
		 	}
		});
	},
	/**
	 * 获取gender
	 */
	get_courier_gender(){
		this.$get("~/api/courier/get_list?like=0", { page: 1, size: 10, "gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_courier_gender = json.result.list;
			result_courier_gender.map(o => o.title = o['gender'])
	  			this.result_courier_gender = result_courier_gender
		 	}
		});
	},
	/**
	 * 获取cabinet_number
	 */
	get_express_cabinet_cabinet_number(){
		this.$get("~/api/express_cabinet/get_list?like=0", { page: 1, size: 10, "cabinet_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_express_cabinet_cabinet_number = json.result.list;
			result_express_cabinet_cabinet_number.map(o => o.title = o['cabinet_number'])
	  			this.result_express_cabinet_cabinet_number = result_express_cabinet_cabinet_number
		 	}
		});
	},
	/**
	 * 获取courier_number
	 */
	get_express_pick_up_courier_number(){
		this.$get("~/api/express_pick_up/get_list?like=0", { page: 1, size: 10, "courier_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_express_pick_up_courier_number = json.result.list;
			result_express_pick_up_courier_number.map(o => o.title = o['courier_number'])
	  			this.result_express_pick_up_courier_number = result_express_pick_up_courier_number
		 	}
		});
	},
	/**
	 * 获取courier_services_company
	 */
	get_express_pick_up_courier_services_company(){
		this.$get("~/api/express_pick_up/get_list?like=0", { page: 1, size: 10, "courier_services_company": this.query.word }, (json) => {
		  if (json.result) {
			var result_express_pick_up_courier_services_company = json.result.list;
			result_express_pick_up_courier_services_company.map(o => o.title = o['courier_services_company'])
	  			this.result_express_pick_up_courier_services_company = result_express_pick_up_courier_services_company
		 	}
		});
	},
	/**
	 * 获取sender_telephone
	 */
	get_use_express_delivery_sender_telephone(){
		this.$get("~/api/use_express_delivery/get_list?like=0", { page: 1, size: 10, "sender_telephone": this.query.word }, (json) => {
		  if (json.result) {
			var result_use_express_delivery_sender_telephone = json.result.list;
			result_use_express_delivery_sender_telephone.map(o => o.title = o['sender_telephone'])
	  			this.result_use_express_delivery_sender_telephone = result_use_express_delivery_sender_telephone
		 	}
		});
	},
	/**
	 * 获取courier_telephone
	 */
	get_apply_for_receipt_courier_telephone(){
		this.$get("~/api/apply_for_receipt/get_list?like=0", { page: 1, size: 10, "courier_telephone": this.query.word }, (json) => {
		  if (json.result) {
			var result_apply_for_receipt_courier_telephone = json.result.list;
			result_apply_for_receipt_courier_telephone.map(o => o.title = o['courier_telephone'])
	  			this.result_apply_for_receipt_courier_telephone = result_apply_for_receipt_courier_telephone
		 	}
		});
	},
	/**
	 * 获取corporate_name
	 */
	get_courier_services_company_corporate_name(){
		this.$get("~/api/courier_services_company/get_list?like=0", { page: 1, size: 10, "corporate_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_courier_services_company_corporate_name = json.result.list;
			result_courier_services_company_corporate_name.map(o => o.title = o['corporate_name'])
	  			this.result_courier_services_company_corporate_name = result_courier_services_company_corporate_name
		 	}
		});
	},
	/**
	 * 获取courier_number
	 */
	get_express_delivery_courier_number(){
		this.$get("~/api/express_delivery/get_list?like=0", { page: 1, size: 10, "courier_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_express_delivery_courier_number = json.result.list;
			result_express_delivery_courier_number.map(o => o.title = o['courier_number'])
	  			this.result_express_delivery_courier_number = result_express_delivery_courier_number
		 	}
		});
	},
	/**
	 * 获取courier_services_company
	 */
	get_express_delivery_courier_services_company(){
		this.$get("~/api/express_delivery/get_list?like=0", { page: 1, size: 10, "courier_services_company": this.query.word }, (json) => {
		  if (json.result) {
			var result_express_delivery_courier_services_company = json.result.list;
			result_express_delivery_courier_services_company.map(o => o.title = o['courier_services_company'])
	  			this.result_express_delivery_courier_services_company = result_express_delivery_courier_services_company
		 	}
		});
	},
	/**
	 * 获取sender_telephone
	 */
	get_express_charges_sender_telephone(){
		this.$get("~/api/express_charges/get_list?like=0", { page: 1, size: 10, "sender_telephone": this.query.word }, (json) => {
		  if (json.result) {
			var result_express_charges_sender_telephone = json.result.list;
			result_express_charges_sender_telephone.map(o => o.title = o['sender_telephone'])
	  			this.result_express_charges_sender_telephone = result_express_charges_sender_telephone
		 	}
		});
	},
	/**
	 * 获取evaluation_number
	 */
	get_express_evaluation_evaluation_number(){
		this.$get("~/api/express_evaluation/get_list?like=0", { page: 1, size: 10, "evaluation_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_express_evaluation_evaluation_number = json.result.list;
			result_express_evaluation_evaluation_number.map(o => o.title = o['evaluation_number'])
	  			this.result_express_evaluation_evaluation_number = result_express_evaluation_evaluation_number
		 	}
		});
	},
	/**
	 * 获取courier_number
	 */
	get_express_logistics_courier_number(){
		this.$get("~/api/express_logistics/get_list?like=0", { page: 1, size: 10, "courier_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_express_logistics_courier_number = json.result.list;
			result_express_logistics_courier_number.map(o => o.title = o['courier_number'])
	  			this.result_express_logistics_courier_number = result_express_logistics_courier_number
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
		this.get_express_information_courier_number();
		this.get_express_information_courier_services_company();
		this.get_student_gender();
		this.get_student_student_number();
		this.get_courier_gender();
		this.get_express_cabinet_cabinet_number();
		this.get_express_pick_up_courier_number();
		this.get_express_pick_up_courier_services_company();
		this.get_use_express_delivery_sender_telephone();
		this.get_apply_for_receipt_courier_telephone();
		this.get_courier_services_company_corporate_name();
		this.get_express_delivery_courier_number();
		this.get_express_delivery_courier_services_company();
		this.get_express_charges_sender_telephone();
		this.get_express_evaluation_evaluation_number();
		this.get_express_logistics_courier_number();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
	  this.get_express_information_courier_number();
	  this.get_express_information_courier_services_company();
	  this.get_student_gender();
	  this.get_student_student_number();
	  this.get_courier_gender();
	  this.get_express_cabinet_cabinet_number();
	  this.get_express_pick_up_courier_number();
	  this.get_express_pick_up_courier_services_company();
	  this.get_use_express_delivery_sender_telephone();
	  this.get_apply_for_receipt_courier_telephone();
	  this.get_courier_services_company_corporate_name();
	  this.get_express_delivery_courier_number();
	  this.get_express_delivery_courier_services_company();
	  this.get_express_charges_sender_telephone();
	  this.get_express_evaluation_evaluation_number();
	  this.get_express_logistics_courier_number();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
