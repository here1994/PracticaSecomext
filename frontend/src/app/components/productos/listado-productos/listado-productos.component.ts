import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card'; // Importa el módulo
import { MatTableModule } from '@angular/material/table';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-listado-productos',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatTableModule], // Agrega MatCardModule aquí
  templateUrl: './listado-productos.component.html',
  styleUrls: ['./listado-productos.component.scss']
})
export class ListadoProductosComponent implements OnInit {
  productos: any[] = [];
  displayedColumns: string[] = ['nombre', 'precio', 'categoria'];

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.cargarProductos();
  }

  cargarProductos(): void {
    this.apiService.getProductos().subscribe({
      next: (data) => (this.productos = data),
      error: (error) => console.error('Error al cargar productos', error)
    });
  }
}
