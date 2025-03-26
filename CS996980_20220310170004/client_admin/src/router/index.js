import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index,
		meta: {
			index: 0,
			title: '首页'
		}
	},

	// 登录
	{
		path: '/login',
		name: 'login',
		component: login,
		meta: {
			index: 0,
			title: '登录'
		}
	},

	// 忘记密码
	{
		path: '/forgot',
		name: "forgot",
		component: forgot,
		meta: {
			index: 0,
			title: '忘记密码'
		}
	},

	// 修改密码
	{
		path: '/user/password',
		name: "password",
		component: () => import("../views/user/password.vue"),
		meta: {
			index: 0,
			title: '修改密码'
		}
	},

	// 视频播放页
	{
		path: "/media/video",
		name: "video",
		component: () => import('../views/media/video.vue'),
		meta: {
			index: 0,
			title: "视频"
		}
	},

	// 音频播放页
	{
		path: "/media/audio",
		name: "audio",
		component: () => import('../views/media/audio.vue'),
		meta: {
			index: 0,
			title: "音频"
		}
	},
	// 权限路由
	{
		path: '/auth/table',
		name: 'auth_table',
		component: () => import('../views/nav/table.vue'),
		meta: {
			index: 0,
			title: '权限列表'
		}
	},
	{
		path: '/auth/view',
		name: 'auth_view',
		component: () => import('../views/nav/view.vue'),
		meta: {
			index: 0,
			title: '权限详情'
		}
	},

	// 友情链接路由
	// {
	// 	path: '/link/table',
	// 	name: 'link_table',
	// 	component: () => import('../views/link/table.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '链接列表'
	// 	}
	// },
	// {
	// 	path: '/link/view',
	// 	name: 'link_view',
	// 	component: () => import('../views/link/view.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '链接详情'
	// 	}
	// },

	// 轮播图路由
	{
		path: '/slides/table',
		name: 'slides_table',
		component: () => import('../views/slides/table.vue'),
		meta: {
			index: 0,
			title: '轮播图列表'
		}
	},
	{
		path: '/slides/view',
		name: 'slides_view',
		component: () => import('../views/slides/view.vue'),
		meta: {
			index: 0,
			title: '轮播图详情'
		}
	},
	// 文章路由
	{
		path: '/article/table',
		name: 'article_table',
		component: () => import('../views/article/table.vue'),
		meta: {
			index: 0,
			title: '文章列表'
		}
	},
	{
		path: '/article/view',
		name: 'article_view',
		component: () => import('../views/article/view.vue'),
		meta: {
			index: 0,
			title: '文章详情'
		}
	},

	// 文章分类路由
	{
		path: '/article_type/table',
		name: 'article_type_table',
		component: () => import('../views/article_type/table.vue'),
		meta: {
			index: 0,
			title: '文章分类列表'
		}
	},
	{
		path: '/article_type/view',
		name: 'article_type_view',
		component: () => import('../views/article_type/view.vue'),
		meta: {
			index: 0,
			title: '文章分类详情'
		}
	},

	// 广告路由
	// {
	// 	path: '/ad/table',
	// 	name: 'ad_table',
	// 	component: () => import('../views/ad/table.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '广告列表'
	// 	}
	// },
	// {
	// 	path: '/ad/view',
	// 	name: 'ad_view',
	// 	component: () => import('../views/ad/view.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '广告详情'
	// 	}
	// },
	// 论坛路由
	{
		path: '/forum/table',
		name: 'forum_table',
		component: () => import('../views/forum/table.vue'),
		meta: {
			index: 0,
			title: '论坛列表'
		}
	},
	{
		path: '/forum/view',
		name: 'forum_view',
		component: () => import('../views/forum/view.vue'),
		meta: {
			index: 0,
			title: '论坛详情'
		}
	},

	// 论坛分类路由
	{
		path: '/forum_type/table',
		name: 'forum_type_table',
		component: () => import('../views/forum_type/table.vue'),
		meta: {
			index: 0,
			title: '论坛分类列表'
		}
	},
	{
		path: '/forum_type/view',
		name: 'forum_type_view',
		component: () => import('../views/forum_type/view.vue'),
		meta: {
			index: 0,
			title: '论坛分类详情'
		}
	},
	// 公告路由
	{
		path: '/notice/table',
		name: 'notice_table',
		component: () => import('../views/notice/table.vue'),
		meta: {
			index: 0,
			title: '公告列表'
		}
	},
	{
		path: '/notice/view',
		name: 'notice_view',
		component: () => import('../views/notice/view.vue'),
		meta: {
			index: 0,
			title: '公告详情'
		}
	},
	// 评论路由
	{
		path: '/comment/table',
		name: 'comment_table',
		component: () => import('../views/comment/table.vue'),
		meta: {
			index: 0,
			title: '评论列表'
		}
	},
	{
		path: '/comment/view',
		name: 'comment_view',
		component: () => import('../views/comment/view.vue'),
		meta: {
			index: 0,
			title: '评论详情'
		}
	},
	// 快递信息路由
	{
		path: '/express_information/table',
		name: 'express_information_table',
		component: () => import('../views/express_information/table.vue')
	},
	{
		path: '/express_information/view',
		name: 'express_information_view',
		component: () => import('../views/express_information/view.vue')
	},
	// 学生路由
	{
		path: '/student/table',
		name: 'student_table',
		component: () => import('../views/student/table.vue')
	},
	{
		path: '/student/view',
		name: 'student_view',
		component: () => import('../views/student/view.vue')
	},
	// 快递员路由
	{
		path: '/courier/table',
		name: 'courier_table',
		component: () => import('../views/courier/table.vue')
	},
	{
		path: '/courier/view',
		name: 'courier_view',
		component: () => import('../views/courier/view.vue')
	},
	// 快递柜路由
	{
		path: '/express_cabinet/table',
		name: 'express_cabinet_table',
		component: () => import('../views/express_cabinet/table.vue')
	},
	{
		path: '/express_cabinet/view',
		name: 'express_cabinet_view',
		component: () => import('../views/express_cabinet/view.vue')
	},
	// 快递取件路由
	{
		path: '/express_pick_up/table',
		name: 'express_pick_up_table',
		component: () => import('../views/express_pick_up/table.vue')
	},
	{
		path: '/express_pick_up/view',
		name: 'express_pick_up_view',
		component: () => import('../views/express_pick_up/view.vue')
	},
	// 寄快递路由
	{
		path: '/use_express_delivery/table',
		name: 'use_express_delivery_table',
		component: () => import('../views/use_express_delivery/table.vue')
	},
	{
		path: '/use_express_delivery/view',
		name: 'use_express_delivery_view',
		component: () => import('../views/use_express_delivery/view.vue')
	},
	// 申请接单路由
	{
		path: '/apply_for_receipt/table',
		name: 'apply_for_receipt_table',
		component: () => import('../views/apply_for_receipt/table.vue')
	},
	{
		path: '/apply_for_receipt/view',
		name: 'apply_for_receipt_view',
		component: () => import('../views/apply_for_receipt/view.vue')
	},
	// 快递公司路由
	{
		path: '/courier_services_company/table',
		name: 'courier_services_company_table',
		component: () => import('../views/courier_services_company/table.vue')
	},
	{
		path: '/courier_services_company/view',
		name: 'courier_services_company_view',
		component: () => import('../views/courier_services_company/view.vue')
	},
	// 快递配送路由
	{
		path: '/express_delivery/table',
		name: 'express_delivery_table',
		component: () => import('../views/express_delivery/table.vue')
	},
	{
		path: '/express_delivery/view',
		name: 'express_delivery_view',
		component: () => import('../views/express_delivery/view.vue')
	},
	// 快递收取费用路由
	{
		path: '/express_charges/table',
		name: 'express_charges_table',
		component: () => import('../views/express_charges/table.vue')
	},
	{
		path: '/express_charges/view',
		name: 'express_charges_view',
		component: () => import('../views/express_charges/view.vue')
	},
	// 快递评价路由
	{
		path: '/express_evaluation/table',
		name: 'express_evaluation_table',
		component: () => import('../views/express_evaluation/table.vue')
	},
	{
		path: '/express_evaluation/view',
		name: 'express_evaluation_view',
		component: () => import('../views/express_evaluation/view.vue')
	},
	// 快递收取路由
	{
		path: '/express_collection/table',
		name: 'express_collection_table',
		component: () => import('../views/express_collection/table.vue')
	},
	{
		path: '/express_collection/view',
		name: 'express_collection_view',
		component: () => import('../views/express_collection/view.vue')
	},
	// 快递物流路由
	{
		path: '/express_logistics/table',
		name: 'express_logistics_table',
		component: () => import('../views/express_logistics/table.vue')
	},
	{
		path: '/express_logistics/view',
		name: 'express_logistics_view',
		component: () => import('../views/express_logistics/view.vue')
	},

	// 用户路由
	{
		path: '/user/table',
		name: 'user_table',
		component: () => import('../views/user/table.vue'),
		meta: {
			index: 0,
			title: '用户列表'
		}
	},
	{
		path: '/user/view',
		name: 'user_view',
		component: () => import('../views/user/view.vue'),
		meta: {
			index: 0,
			title: '用户详情'
		}
	},
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue'),
		meta: {
			index: 0,
			title: '个人信息'
		}
	},
	// 用户组路由
	{
		path: '/user_group/table',
		name: 'user_group_table',
		component: () => import('../views/user_group/table.vue'),
		meta: {
			index: 0,
			title: '用户组列表'
		}
	},
	{
		path: '/user_group/view',
		name: 'user_group_view',
		component: () => import('../views/user_group/view.vue'),
		meta: {
			index: 0,
			title: '用户组详情'
		}
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {
	let token = to.query.token;
	if (token){
		$.db.set("token",token,120);
	}
	next();
})

router.afterEach((to, from, next) => {
	let title = "学校快递站点管理系统-admin";
	document.title = title;
})

export default router
