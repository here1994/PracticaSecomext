import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = 'http://localhost:8080/pruebapractica/api'; // URL del backend

  constructor(private http: HttpClient) {}
  //Login
  // Método para realizar login con Basic Auth
  login(username: string, password: string): Observable<any> {
    const authHeader = 'Basic ' + btoa(`${username}:${password}`); // Codifica usuario y contraseña en Base64
    const headers = new HttpHeaders({
      Authorization: authHeader
    });

    return this.http.get(`${this.baseUrl}/login`, {
      headers,
      withCredentials: true // Para incluir cookies/sesión si el backend las utiliza
    });
  }

  // Productos: Obtnener todos los productos
  getProductos(): Observable<any> {
    return this.http.get(`${this.baseUrl}/productos`, {
      withCredentials: true // La cookie JSESSIONID se enviará automáticamente
    });
  }

  getProducto(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/productos/${id}`,{
      withCredentials: true // La cookie JSESSIONID se enviará automáticamente
    });
  }

  createProducto(producto: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/productos`, producto,{
      withCredentials: true,  headers: {
        'Content-Type': 'application/json'
      }
    });
  }

  updateProducto(id: number, producto: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/productos/${id}`, producto,{
      withCredentials: true // La cookie JSESSIONID se enviará automáticamente
    });
  }

  deleteProducto(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/productos/${id}`,{
      withCredentials: true // La cookie JSESSIONID se enviará automáticamente
    });
  }

  // Categorías
  getCategorias(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/categorias`,{
      withCredentials: true // La cookie JSESSIONID se enviará automáticamente
    });
  }
}
