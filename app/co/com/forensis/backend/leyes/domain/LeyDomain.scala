package co.com.forensis.backend.leyes.domain

/**
 * Created by jarvis on 01/05/2016.
 */
final case class Ley( id: Int, titulo: String, capitulo: String, articulo: String )

trait LeyDomainOperation

case class LeyCreated( s: String ) extends LeyDomainOperation

case class LeyUpdated( s: String ) extends LeyDomainOperation

case class LeyDeleted( s: String ) extends LeyDomainOperation