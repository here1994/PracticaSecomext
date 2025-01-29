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
      NAME: ['', Validators.required], // Cambiado a NAME
      PRICE: ['', [Validators.required, Validators.min(1)]], // Cambiado a PRICE
      CATEGORY_ID: ['', Validators.required] // Cambiado a CATEGORY_ID
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
      const producto = this.productoForm.value; // El formulario ya tiene los nombres correctos
      this.apiService.createProducto(producto).subscribe({
        next: () => {
          alert('Producto guardado exitosamente');
          this.productoForm.reset(); // Reinicia el formulario
        },
        error: (error) => {
          console.error('Error al guardar producto:', error);
          alert('Ocurrió un error al guardar el producto');
        }
      });
    } else {
      alert('Formulario inválido');
    }
  }
  
  
}
