import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { MenuComponent } from './components/layout/menu/menu.component';
import { ListadoProductosComponent } from './components/productos/listado-productos/listado-productos.component';
import { FormularioProductosComponent } from './components/productos/formulario-productos/formulario-productos.component';
import { ListadoCategoriasComponent } from './components/categorias/listado-categorias/listado-categorias.component';

// Angular Material Modules
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select'; 
import { MatTableModule } from '@angular/material/table'; 

@NgModule({
  declarations: [
    AppComponent
    //LoginComponent,
    //MenuComponent,
    //ListadoProductosComponent,
    //FormularioProductosComponent,
    //ListadoCategoriasComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule,
    MatToolbarModule,
    MatButtonModule,
    MatInputModule,
    MatCardModule,
    MatListModule,
    MatSelectModule,
    MatTableModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
