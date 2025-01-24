import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'login',
    loadComponent: () =>
      import('./components/login/login.component').then((m) => m.LoginComponent)
  },
  {
    path: 'menu',
    loadComponent: () =>
      import('./components/layout/menu/menu.component').then((m) => m.MenuComponent),
    children: [
      {
        path: 'productos',
        loadComponent: () =>
          import('./components/productos/listado-productos/listado-productos.component').then(
            (m) => m.ListadoProductosComponent
          )
      },
      {
        path: 'productos/nuevo',
        loadComponent: () =>
          import('./components/productos/formulario-productos/formulario-productos.component').then(
            (m) => m.FormularioProductosComponent
          )
      },
      {
        path: 'categorias',
        loadComponent: () =>
          import('./components/categorias/listado-categorias/listado-categorias.component').then(
            (m) => m.ListadoCategoriasComponent
          )
      }
    ]
  },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', redirectTo: 'login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
