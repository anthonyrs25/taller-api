package ec.edu.taller_api

data class Cliente(
    val nombre: String,
    val telefono: String,
)

data class Reparacion(
    val id: Int,
    val bicicleta: String,
    val cliente: Cliente,
    val repuestos: List<String>,
    val costo: Double,
    val lista: Boolean,
)

data class NuevaReparacion(
    val bicicleta: String,
    val cliente: Cliente,
    val repuestos: List<String> = emptyList(),
    val costo: Double,
)