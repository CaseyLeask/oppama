package InputParsing
import org.specs2.mutable._

class CommandParsingTest extends Specification {
  "CommandParsing" should {
    "with a PLACE command" should {
      "parse valid commands" in {
        val lines = Iterator("PLACE 1,1,NORTH")
        CommandParsing.toRobotCommands(lines).toList must be equalTo List(Place(1,1,North))
      }

      "not parse invalid x values" in {
        val lines = Iterator(
          "PLACE -1,1,NORTH",
          "PLACE 0.5,1,NORTH",
          "PLACE 6,1,NORTH"
        )
        CommandParsing.toRobotCommands(lines).toList must be equalTo List()
      }

      "not parse invalid y values" in {
        val lines = Iterator(
          "PLACE 1,-1,NORTH",
          "PLACE 1,0.5,NORTH",
          "PLACE 1,6,NORTH"
        )
        CommandParsing.toRobotCommands(lines).toList must be equalTo List()
      }

      "not parse invalid direction values" in {
        val lines = Iterator(
          "PLACE 1,1,WESTSIDE",
          "PLACE 1,1,north",
          "PLACE 1,1,North",
          "PLACE 1,1,NORTHS"
        )
        CommandParsing.toRobotCommands(lines).toList must be equalTo List()
      }
    }

    "parse MOVE commands" in {
      val lines = Iterator("MOVE")
      CommandParsing.toRobotCommands(lines).toList must be equalTo List(Move)
    }

    "parse LEFT commands" in {
      val lines = Iterator("LEFT")
      CommandParsing.toRobotCommands(lines).toList must be equalTo List(Left)
    }

    "parse RIGHT commands" in {
      val lines = Iterator("RIGHT")
      CommandParsing.toRobotCommands(lines).toList must be equalTo List(Right)
    }

    "parse REPORT commands" in {
      val lines = Iterator("REPORT")
      CommandParsing.toRobotCommands(lines).toList must be equalTo List(Report)
    }

    "not parse mystery commands" in {
      val lines = Iterator(
        "MOVER",
        "SHAKER",
        "LEFT FOR DEAD",
        "CLEFT",
        "BRIGHT",
        "REPORTING FOR DUTY"
      )
      CommandParsing.toRobotCommands(lines).toList must be equalTo List()
    }
  }
}
