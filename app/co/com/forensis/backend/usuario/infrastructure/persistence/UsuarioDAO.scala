package co.com.forensis.backend.usuario.infrastructure.persistence

import co.com.forensis.backend.usuario.controller.request.AuthenticateUsuarioRequest
import com.google.inject.Inject
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import play.db.NamedDatabase
import slick.driver.JdbcProfile

import scala.concurrent.Future

/**
 * Created by Usuario on 27/04/2016.
 */

final class UsuarioDAO @Inject() ( @NamedDatabase( "default" ) protected val dbConfigProvider:DatabaseConfigProvider )
    extends HasDatabaseConfigProvider[ JdbcProfile ] {

  import driver.api._

  private val usuarios = TableQuery[ UsuarioTable ]

  def getUsuario: Future[ Seq[ UsuarioRecord ] ] = {
    val query = usuarios.map( x => x )
    db.run( query.result )
  }

  def insertUsuario( usuario: UsuarioRecord ): Future[ Int ] = {
    val insert = usuarios += usuario
    db.run( insert )
  }

  def updateUsuario( usuario: UsuarioRecord ): Future[ Int ] = {
    val update = usuarios.filter( _.id === usuario.id ).update( usuario )
    db.run( update )
  }

  def deleteUsuario( id: Int ): Future[ Int ] = {
    val delete = usuarios.filter( _.id === id ).delete
    db.run( delete )
  }

  def authenticateUsuario( user: AuthenticateUsuarioRequest ): Future[ Option[ UsuarioRecord ] ] = {
    val autentica = usuarios.filter( x => x.correo === user.correo && x.clave === user.clave && x.activo === true )
    db.run( autentica.result.headOption )
  }
}
