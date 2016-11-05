import akka.actor.{Actor, ActorLogging}
import akka.pattern.BackoffSupervisor.Reset

import scala.util.Random

/**
  * SupervisionTest
  * Created by fabiotiriticco on 5/11/2016.
  */

case object Crash

class Child extends Actor with ActorLogging {

  log.debug("i'm being created: {}", self)

  // this child will randomly send a reset message to its parent or throw an exception
  def receive = {
    case Crash =>
      if (isEverythingFine) {
        log.debug("telling dad that everything is fine")
        context.parent ! Reset
      } else throw new NullPointerException("boom!")
  }

  def isEverythingFine = new Random().nextInt(4) == 1

  override def preRestart(reason: Throwable, message: Option[Any]) = {
    log.debug("someone is restarting me because of {}", reason)
    log.debug("what made me crash was {}", message.getOrElse("unknown"))
    super.preRestart(reason, message)
  }

  override def postStop(): Unit = {
    log.debug("i have been stopped")
    super.postStop()
  }
}
