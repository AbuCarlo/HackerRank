
def validate(s: String) = {
  if (s.isEmpty) {
    // This doesn't seem to occur in the test cases.
    true
  } else {
    val frequencies = s.foldLeft[Map[Char, Int]](Map.empty)((m, c) => m + (c -> (m.getOrElse(c, 0) + 1)))
    val reducedFrequencies = frequencies.values.toSet
    println(s"$frequencies, $reducedFrequencies")
    if (reducedFrequencies.size == 1) {
      // All characters have the same frequency.
      true
    } else if (reducedFrequencies.size > 2) {
      false
    } else {
      val min = reducedFrequencies.min
      if (min == 1 && frequencies.values.count(_ == min) == 1) {
        // There's a single instance of a character, which we can delete.
        true
      } else {
        val max = reducedFrequencies.max
        max == min + 1 &&
          frequencies.values.count(_ == max) == 1
      }
    }
  }
}

validate("")

validate("abc")

validate("aabbcd")

validate("aabbc")

validate("aabbccddd")

validate("aabbccddeefghi")

validate("abcdefghhgfedecba")

val seven = "ibfdgaeadiaefgbhbdghhhbgdfgeiccbiehhfcggchgghadhdhagfbahhddgghbdehidbibaeaagaeeigffcebfbaieggabcfbiiedcabfihchdfabifahcbhagccbdfifhghcadfiadeeaheeddddiecaicbgigccageicehfdhdgafaddhffadigfhhcaedcedecafeacbdacgfgfeeibgaiffdehigebhhehiaahfidibccdcdagifgaihacihadecgifihbebffebdfbchbgigeccahgihbcbcaggebaaafgfedbfgagfediddghdgbgehhhifhgcedechahidcbchebheihaadbbbiaiccededchdagfhccfdefigfibifabeiaccghcegfbcghaefifbachebaacbhbfgfddeceababbacgffbagidebeadfihaefefegbghgddbbgddeehgfbhafbccidebgehifafgbghafacgfdccgifdcbbbidfifhdaibgigebigaedeaaiadegfefbhacgddhchgcbgcaeaieiegiffchbgbebgbehbbfcebciiagacaiechdigbgbghefcahgbhfibhedaeeiffebdiabcifgccdefabccdghehfibfiifdaicfedagahhdcbhbicdgibgcedieihcichadgchgbdcdagaihebbabhibcihicadgadfcihdheefbhffiageddhgahaidfdhhdbgciiaciegchiiebfbcbhaeagccfhbfhaddagnfieihghfbaggiffbbfbecgaiiidccdceadbbdfgigibgcgchafccdchgifdeieicbaididhfcfdedbhaadedfageigfdehgcdaecaebebebfcieaecfagfdieaefdiedbcadchabhebgehiidfcgahcdhcdhgchhiiheffiifeegcfdgbdeffhgeghdfhbfbifgidcafbfcd"

validate(seven)