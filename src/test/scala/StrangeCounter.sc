def strangeCounter(t: Long): Long = {

  def inner(starter: Long, n: Long): Long = {
     println(s"$starter , $n")
    if (n < starter)
     starter - n
    else
      inner(starter * 2, n - starter)
  }

  inner(3, t - 1)
}

strangeCounter(10)