package repositories

import dao.TokenDAO
import javax.inject.Inject
import models.Token

import scala.concurrent.Future

trait AbstractRepository {
  def all: Future[Seq[Token]]
}

class TokenRepository @Inject() (tokenDao: TokenDAO) extends AbstractRepository {
  override def all: Future[Seq[Token]] = {
    tokenDao.all
  }
}