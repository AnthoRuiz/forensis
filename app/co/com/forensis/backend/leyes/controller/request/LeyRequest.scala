package co.com.forensis.backend.leyes.controller.request

/**
 * Created by jarvis on 01/05/2016.
 */
final case class CreateLeyRequest( titulo: String, capitulo: String, articulo: String )

final case class UpdateLeyRequest( id: Int, titulo: String, capitulo: String, articulo: String )