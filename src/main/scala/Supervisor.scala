import akka.actor.SupervisorStrategy.Stop
import akka.actor.{Actor, ActorLogging, OneForOneStrategy, Props}
import akka.pattern.{Backoff, BackoffSupervisor}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

/**
  * SupervisionTest
  * Created by fabiotiriticco on 5/11/2016.
  */
class Supervisor extends Actor with ActorLogging {

  // create the backoff supervisor props
  val backoffSupervisorProps = BackoffSupervisor.props(
    Backoff.onStop(
      childProps = Props(new Child),
      childName = "myChild",
      minBackoff = 3.seconds,
      maxBackoff = 30.seconds,
      randomFactor = 0.2
    ).withManualReset
      .withSupervisorStrategy(
        OneForOneStrategy() {
          case npe: NullPointerException =>
            log.debug("npe caught")
            Stop
        })
  )

  // instantiate an actual backoff supervisor
  val backoffSupervisor = context.actorOf(backoffSupervisorProps, name = "backoffSupervisor")

  // periodically send a Crash message to the supervised child (via his supervisor):
  // it took me a while to find out that the backoff supervisor forwards messages to the child!
  context.system.scheduler.schedule(
    0.seconds,
    1.seconds,
    backoffSupervisor,
    Crash
  )

  override def receive: Receive = {
    case _ => // do nothing
  }
}
