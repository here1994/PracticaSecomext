import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-listado-categorias',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatListModule],
  templateUrl: './listado-categorias.component.html',
  styleUrls: ['./listado-categorias.component.scss']
})
export class ListadoCategoriasComponent implements OnInit {
  categorias: any[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.cargarCategorias();
  }

  cargarCategorias(): void {
    this.apiService.getCategorias().subscribe({
      next: (data) => (this.categorias = data),
      error: (error) => console.error('Error al cargar categorías', error)
    });
  }
}
