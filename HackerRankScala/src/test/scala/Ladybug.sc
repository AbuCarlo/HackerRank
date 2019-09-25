// Complete the happyLadybugs function below.
def happyLadybugs(input: String): String = {

  def noMovement() = {
    input.size != 1 &&
    input(0) == input(1) &&
      input(input.size - 1) == input(input.size - 2) &&
      Range(1, input.size - 2).forall(i => input(i) == input(i - 1) || input(i) == input(i + 1))
  }

  def movement() = {
    val colors = input.toList.filterNot(_ == '_')
    colors.isEmpty || colors.groupBy(c => c).values.map(_.size).forall(_ > 1)
  }

  if ((input.exists(_ == '_') && movement() || noMovement()))
    "YES"
  else
    "NO"
}

happyLadybugs("AA")
