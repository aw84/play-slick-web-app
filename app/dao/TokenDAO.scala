package dao

import javax.inject.Inject
import models.Token
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile

import scala.concurrent.{ ExecutionContext, Future }

class TokenDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private class TokensTable(tag: Tag) extends Table[Token](tag, Some("TEST_SCHEMA"), "TOKENS") {
    def id = column[Int]("ID", O.PrimaryKey)

    def name = column[String]("NAME")

    override def * = (id, name) <> (Token.tupled, Token.unapply)
  }

  private val Tokens = TableQuery[TokensTable]

  def all: Future[Seq[Token]] = db.run(Tokens.result)

}
