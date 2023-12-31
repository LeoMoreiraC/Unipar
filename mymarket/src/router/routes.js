const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      { path: "", component: () => import("pages/IndexPage.vue") },
      { path: "signin", component: () => import("pages/signIn/Index.vue") },
      { path: "main", component: () => import("pages/Main/Index.vue") },
      {
        path: "inventario",
        component: () => import("pages/Inventario/Index.vue"),
      },
      {
        path: "usuarios",
        component: () => import("pages/Usuarios/Index.vue"),
      },
      {
        path: "configuracoes",
        component: () => import("pages/Configuracoes/Index.vue"),
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;
