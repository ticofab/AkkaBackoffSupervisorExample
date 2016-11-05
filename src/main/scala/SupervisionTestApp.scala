import akka.actor.{ActorSystem, Props}

/**
  * SupervisionTest
  * Created by fabiotiriticco on 5/11/2016.
  */
object SupervisionTestApp extends App {
  val actorSystem = ActorSystem("test")
  val supervisor = actorSystem.actorOf(Props(new Supervisor), "supervisor")
}
