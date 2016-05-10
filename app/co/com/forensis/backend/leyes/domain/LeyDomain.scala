package co.com.forensis.backend.leyes.domain

/**
 * Created by jarvis on 01/05/2016.
 */
final case class LeyDomain( id: Int, titulo: String )

trait LeyDomainOperation

case class LeyCreated( s: String ) extends LeyDomainOperation

case class LeyUpdated( s: String ) extends LeyDomainOperation

case class LeyDeleted( s: String ) extends LeyDomainOperation