package brainfug

class Program(commands:List[Command]) {
  def optimize():Program = new Program(commands)

  def run(vm: BrainfugVM):Unit = {
    var pc = 0
    while (pc < commands.length) {
      val currentCommand = commands(pc)
      pc = vm.call(currentCommand, pc)
    }
  }
}

object Program {
  val non_comment_characters = "+-<>[].,"

  def compile(from:String):Program = {
    val commands = from.filter(ch => non_comment_characters contains ch) map {
      case '+' => Add()
      case '-' => Sub()
      case '.' => Out()
      case ',' => In()
      case '<' => Left()
      case '>' => Right()
      case '[' => Begin()
      case ']' => End()
    }

    new Program(commands.toList)
  }
}