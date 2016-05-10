package co.com.forensis.backend.leyes.domain

/**
 * Created by Usuario on 10/05/2016.
 */
final case class ArticuloDomain( id: Int, cuerpo: String )

trait ArticuloDomainOperation

case class ArticuloCreated( s: String ) extends ArticuloDomainOperation

case class ArticuloUpdated( s: String ) extends ArticuloDomainOperation

case class ArticuloDeleted( s: String ) extends ArticuloDomainOperation
