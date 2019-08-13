package services

import javax.inject.Inject
import repositories.TokenRepository

class TokenServices @Inject() (tokenRepository: TokenRepository) {
  def all = tokenRepository.all
}
