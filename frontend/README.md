# Frontend - Prueba Práctica

Este proyecto es el frontend de una aplicación de gestión de productos y categorías, desarrollado con **Angular 15** y utilizando **Angular Material** para la interfaz de usuario. Se conecta con un backend desarrollado en Spring Boot para realizar operaciones CRUD y manejar autenticación.

## Características Principales

- **Login:**
  - Autenticación mediante credenciales.
  - Almacenamiento de sesión mediante cookies.

- **Gestor de Productos:**
  - Listado de productos con información básica.
  - Creación, edición y eliminación de productos.

- **Gestor de Categorías:**
  - Listado de categorías.
  - Creación, edición y eliminación de categorías.

- **Angular Material:**
  - Uso de componentes como `mat-table`, `mat-form-field`, `mat-select` y `mat-toolbar` para una interfaz moderna y responsiva.

---

## Requisitos Previos

1. **Node.js:** Versión 18 o superior.
2. **Angular CLI:** Versión 15 o superior.
3. **NPM:** Versión 8 o superior.

---

## Instalación y Configuración

1. **Clonar el Repositorio:**
   ```bash
   git clone git@github.com:jmf/secomextfront.git
   cd frontend-pruebapractica
   ```

2. **Instalar Dependencias:**
   ```bash
   npm install
   ```

3. **Configurar el Archivo `environment.ts`:**
   En `src/environments/environment.ts`, define la URL base del backend:

   ```typescript
   export const environment = {
     production: false,
     apiUrl: 'http://localhost:8080/pruebapractica/api' // Ajustar segun el deploy del war del  backend
   };
   ```

4. **Iniciar el Servidor de Desarrollo:**
   ```bash
   ng serve
   ```

5. **Acceder a la Aplicación:**
   - URL: `http://localhost:4200`

---

## Estructura del Proyecto

```
frontend-pruebapractica/
├── src/
│   ├── app/
│   │   ├── components/
│   │   │   ├── layout/
│   │   │   │   └── menu/
│   │   │   ├── productos/
│   │   │   │   ├── listado-productos/
│   │   │   │   ├── formulario-productos/
│   │   │   │   └── modificar-producto/
│   │   │   ├── categorias/
│   │   │   │   └── listado-categorias/
│   │   │   └── login/
│   │   ├── services/
│   │   │   └── api.service.ts
│   │   ├── app-routing.module.ts
│   │   └── app.component.ts
├── environments/
│   ├── environment.ts
│   └── environment.prod.ts
└── angular.json
```

---

## Características de los Componentes

### **1. LoginComponent**
- **Ruta:** `/login`
- **Función:** Permite a los usuarios iniciar sesión enviando sus credenciales al backend.
- **Componentes Usados:**
  - `mat-card`
  - `mat-form-field`
  - `mat-button`

### **2. ListadoProductosComponent**
- **Ruta:** `/productos`
- **Función:** Muestra una tabla con la lista de productos.
- **Componentes Usados:**
  - `mat-table`
  - `mat-toolbar`
  - `mat-button`

### **3. FormularioProductosComponent**
- **Ruta:** `/productos/nuevo`
- **Función:** Permite crear un nuevo producto.
- **Componentes Usados:**
  - `mat-form-field`
  - `mat-select`
  - `mat-button`

### **4. ModificarProductoComponent**
- **Ruta:** `/productos/modificar`
- **Función:** Permite seleccionar y modificar un producto existente.
- **Componentes Usados:**
  - `mat-select`
  - `mat-form-field`
  - `mat-button`

### **5. ListadoCategoriasComponent**
- **Ruta:** `/categorias`
- **Función:** Muestra una lista de categorías disponibles.
- **Componentes Usados:**
  - `mat-table`
  - `mat-toolbar`

---

## API Service (`api.service.ts`)

Este servicio maneja la comunicación con el backend. Principales métodos disponibles:

- **Autenticación:**
  - `login(credentials: any): Observable<any>`

- **Productos:**
  - `getProductos(): Observable<any[]>`
  - `createProducto(producto: any): Observable<any>`
  - `updateProducto(producto: any): Observable<any>`
  - `deleteProducto(id: number): Observable<any>`

- **Categorías:**
  - `getCategorias(): Observable<any[]>`
  - `createCategoria(categoria: any): Observable<any>`
  - `updateCategoria(categoria: any): Observable<any>`
  - `deleteCategoria(id: number): Observable<any>`

---

## Seguridad

El frontend utiliza cookies para manejar la sesión del usuario autenticado:

1. La cookie `JSESSIONID` se obtiene tras un login exitoso.
2. Esta cookie se envía automáticamente en cada solicitud posterior al backend.

---

## Ejecución en local


```bash
ng serve
```

### **Pruebas E2E:**
Ejecuta las pruebas end-to-end con el comando:

```bash
ng e2e
```

---

## Despliegue

1. Generar los archivos para producción:
   ```bash
   ng build --prod
   ```

2. Copiar los archivos generados en la carpeta `dist/` al servidor web de tu elección.

---

## Autor
**jmf** 

---

## Licencia
Sin licencia
