import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-formulario-productos',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule
  ],
  templateUrl: './formulario-productos.component.html',
  styleUrls: ['./formulario-productos.component.scss']
})
export class FormularioProductosComponent implements OnInit {
  productoForm!: FormGroup;
  categorias: any[] = [];

  constructor(private fb: FormBuilder, private apiService: ApiService) {}

  ngOnInit(): void {
    this.productoForm = this.fb.group({
      nombre: ['', Validators.required],
      precio: ['', [Validators.required, Validators.min(1)]],
      categoria: ['', Validators.required]
    });

    this.cargarCategorias();
  }

  cargarCategorias(): void {
    this.apiService.getCategorias().subscribe({
      next: (data) => (this.categorias = data),
      error: (error) => console.error('Error al cargar categorías', error)
    });
  }

  guardarProducto(): void {
    if (this.productoForm.valid) {
      const productoFormValue = this.productoForm.value;
  
      // Estructurar el objeto correctamente para el backend
      const producto = {
        nombre: productoFormValue.nombre,
        precio: productoFormValue.precio,
        categoria: {
          id: productoFormValue.categoria // Solo enviar el ID de la categoría
        }
      };
      this.apiService.createProducto(producto).subscribe({
        next: () => {
          alert('Producto guardado exitosamente');
          this.productoForm.reset(); // Reiniciar el formulario
        },
        error: (error) => {
          console.error('Detalles del error:', error.message || error); // Registra el error en detalle
          alert('Ocurrió un error al guardar el producto'+error.message);
        },
        complete: () => {
          console.log('Petición completada');
        }
      });
    } else {
      alert('Formulario inválido');
    }
  }
}
