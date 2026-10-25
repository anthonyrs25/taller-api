package ec.edu.taller_api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/reparaciones")
class ReparacionesController {

    private val reparaciones = mutableListOf(
        Reparacion(1, "Bicicleta de montaña", Cliente("Luis Paredes", "0991234567"), listOf("cadena", "frenos"), 15.0, true),
        Reparacion(2, "Bicicleta de ruta", Cliente("María Torres", "0987001122"), listOf("llanta"), 8.75, false),
    )
    private var siguienteId = 3

    @GetMapping
    fun listar(): List<Reparacion> = reparaciones

    @GetMapping("/{id}")
    fun obtener(@PathVariable id: Int): ResponseEntity<Reparacion> =
        reparaciones.find { it.id == id }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @PostMapping
    fun crear(@RequestBody nueva: NuevaReparacion): ResponseEntity<Reparacion> {
        val reparacion = Reparacion(
            id = siguienteId++,
            bicicleta = nueva.bicicleta,
            cliente = nueva.cliente,
            repuestos = nueva.repuestos,
            costo = nueva.costo,
            lista = false,
        )
        reparaciones.add(reparacion)
        return ResponseEntity.created(URI("/reparaciones/${reparacion.id}")).body(reparacion)
    }
}