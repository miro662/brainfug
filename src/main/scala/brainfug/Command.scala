package brainfug

sealed abstract class Command

case class Add() extends Command
case class Sub() extends Command

case class Out() extends Command
case class In() extends Command

case class Left() extends Command
case class Right() extends Command

case class Begin() extends Command
case class End() extends Command

