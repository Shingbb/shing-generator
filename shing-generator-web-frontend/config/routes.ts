export default [
  {
    path: '/user',
    layout: false,
    routes: [
      { path: '/user/login', component: './User/Login' },
      { path: '/user/register', component: './User/Register' },
    ],
  },
  // { path: '/welcome', icon: 'smile', component: './Welcome', name: '欢迎页' },
  // { path: '/File', icon: 'home', component: './File', name: '文件上传' },
  {
    path: '/Index',
    icon: 'HomeOutlined',
    component: './Index',
    name: '主页',
  },
  {
    path: '/generator',
    icon: 'VerticalAlignBottomOutlined',
    name: '生成页',
    routes: [
      {
        path: '/generator/add',
        icon: 'crown',
        component: './Generator/Add',
        name: '创建生成器',
      },
      {
        path: '/generator/update',
        component: './Generator/Add',
        name: '修改生成器',
        hideInMenu: true,
      },
         {
           path: '/generator/user/:id',
           component: './Generator/Use',
           name: '使用生成器',
           hideInMenu: true,
         },
      {
        path: '/generator/detail/:id',
        component: './Generator/Detail',
        name: '生成器详情',
        hideInMenu: true,
      },
    ],
  },
  {
    path: '/admin',
    icon: 'SettingOutlined',
    name: '管理页',
    access: 'canAdmin',
    routes: [
      { path: '/admin', redirect: '/admin/user' },
      { icon: 'table', path: '/admin/user', component: './Admin/User', name: '用户管理' },
      {
        icon: 'table',
        path: '/admin/generator',
        component: './Admin/Generator',
        name: '生成器管理',
      },
    ],
  },
  { path: '/', redirect: '/index' },
  { path: '*', layout: false, component: './404' },
];
