package mytextadventure

class Adventure {

  /**
    * The text adventure game "Avatar: The Last Airbender" is based on the Emmy award-winning American
    * animated television series of the same name, created by Michael Dante DiMartino and Bryan Konietzko.
    *
    * The series follows the adventures of the main protagonist Aang and his friends, who must save the world
    * by defeating Fire Lord Ozai and ending the destructive war with the Fire Nation.
    */

  /** The title of the adventure game. */
  val title = "Avatar: The Last Airbender"

  /** The description used by four Air Nomad Temples. */
  val airNomad = "The old land of airbenders and flying bison. It remains uninhabited after Air Nomad Genocide." +
    "\nAir Nomad Genocide is a mass slaughter committed by the Fire Nation that resulted in the near eradication of the Air Nomads." +
    "\nAvatar Aang, you are the only airbender that survived it."


  /** The map closely resembles the actual map created in the series. */
  private val southWater  = new Area("Southern Water Tribes", "The land of warriors, waterbenders, and healers.")
  private val ocean1      = new Area("The ocean", "The horizon stretches across the entire field of view in the most spectacular fashion." +
    "\nIn the far east, you see the long abandoned land of air tribes.")
  private val southAir    = new Area("Southern Air Temple", airNomad)
  private val ocean2      = new Area("The ocean", "Calm and gentle.\nSubtle and sweet.\nYou see the land of Earth Kingdom in the south and east.")

  private val ocean3      = new Area("The ocean", "Please be aware! You are entering the Fire Nation's territory.\nThe north and west sides are the land of firebenders.")
  private val kyoshi      = new Area("Kyoshi Island", "The home of Avatar Kyoshi.\nThe land of Kyoshi Warriors. " +
    "These elite fighters ensured the safety and isolationism of their homeland during the Hundred Year War.")
  private val roku        = new Area("Roku's Island", "This place is used to be preceding Avatar's home. It is an only place where you can connect with Avatar Roku.")
  private val omashu      = new Area("Omashu", "The Earth Kingdom city of Omashu! It is the second largest city in the Earth Kingdom.")

  private val ocean4      = new Area("The ocean", "An enormous pool of wonder, forging its own wave-song...")
  private val ocean5      = new Area("The ocean", "Enjoy its glorious vastness, its dreamy surface, and metronomic wave music...")
  private val ocean6      = new Area("The ocean", "A solitary cormorant, sleek wings a-flurry,\nstreaked out to the place " +
    "where ocean and sky melt into each other and was lost from sight...")
  private val ocean7      = new Area("The ocean", "Blue, sparkling water is everywhere you look with no end.")

  private val fireCapital = new Area("The Fire Capital", "The home to the Fire Lord, the Fire Nation Royal Family, and Fire Nation nobility." +
    "\nYou have to master all four elements to defeat the Lord.")
  private val hamaVillage = new Area("Hama's Village", "The small village in the territory of Fire Nation.")
  private val sunWarriors = new Area("The city of Sun Warriors", "The land of the ancient civilization who first discovered firebending from the dragons.")
  private val westAir     = new Area("Western Air Temple", airNomad)
  private val desert      = new Area("Si Wong Desert", "Otherwise known as the 'Desert of the Dead', the Si Wong Desert is the driest, hottest environment in the world, " +
    "consisting of miles of barren sand.")
  private val ocean8      = new Area("The ocean", "In the west, you can see the 'Desert of the Dead'.")
  private val eastAir     = new Area("Eastern Air Temple", airNomad)
  private val swamp       = new Area("Foggy swamp", "A vast, mysterious wetland covering a large portion of the southwestern Earth Kingdom.")

  private val northWater  = new Area("Northern Water Tribes", "Even though much of its territory encompasses largely inhospitable tundra terrain," +
    "\nthe Northern Water Tribe always thrived in its isolation.\nAll the powerful waterbenders are in this place.")
  private val northAir    = new Area("Northern Air Temple", airNomad)
  private val ocean9      = new Area("The ocean", "Blue, sparkling water is everywhere you look which seems to have no end.")
  private val baSingSe    = new Area("Ba Sing Se", "This is a capital of the Earth Kingdom. Ba Sing Se means \"Impenetrable City\"." +
    "\nIt is so named for its world-famous walls, the gates of which are opened only by the use of earthbending.")

  private val destination = fireCapital

  southWater.setNeighbors(Vector("north" -> southAir, "east" -> ocean1, "south" -> ocean4, "west" -> fireCapital))
  ocean1.setNeighbors(Vector("north" -> ocean2, "east" -> eastAir, "south" -> ocean5, "west" -> southWater))
  southAir.setNeighbors(Vector("north" -> ocean3, "east" -> ocean2, "south" -> southWater, "west" -> hamaVillage))
  ocean2.setNeighbors(Vector("north" -> kyoshi, "east" -> swamp, "south" -> ocean1, "west" -> southAir))

  ocean3.setNeighbors(Vector("north" -> roku, "east" -> kyoshi, "south" -> southAir, "west" -> hamaVillage))
  kyoshi.setNeighbors(Vector("north" -> omashu, "east" -> swamp, "south" -> ocean2, "west" -> ocean3))
  roku.setNeighbors(Vector("north" -> northAir, "east" -> omashu, "south" -> ocean3, "west" -> sunWarriors))
  omashu.setNeighbors(Vector("north" -> baSingSe, "east" -> desert, "south" -> kyoshi, "west" -> roku))

  westAir.setNeighbors(Vector("north" -> northWater, "east" -> sunWarriors, "south" -> fireCapital, "west" -> ocean6))
  sunWarriors.setNeighbors(Vector("north" -> northAir, "east" -> roku, "south" -> hamaVillage, "west" -> westAir))
  fireCapital.setNeighbors(Vector("north" -> westAir, "east" -> hamaVillage, "south" -> southWater, "west" -> ocean4))
  hamaVillage.setNeighbors(Vector("north" -> sunWarriors, "east" -> ocean3, "south" -> southAir, "west" -> fireCapital))

  desert.setNeighbors(Vector("north" -> baSingSe, "east" -> ocean8, "south" -> swamp, "west" -> omashu))
  ocean8.setNeighbors(Vector("north" -> ocean9, "east" -> ocean7, "south" -> eastAir, "west" -> desert))
  swamp.setNeighbors(Vector("north" -> desert, "east" -> eastAir, "south" -> ocean2, "west" -> kyoshi))
  eastAir.setNeighbors(Vector("north" -> ocean8, "east" -> ocean5, "south" -> ocean1, "west" -> swamp))

  ocean4.setNeighbors(Vector("north" -> southWater, "east" -> ocean5, "south" -> ocean6, "west" -> fireCapital))
  ocean5.setNeighbors(Vector("north" -> ocean1, "east" -> eastAir, "south" -> ocean7, "west" -> ocean4))
  ocean6.setNeighbors(Vector("north" -> ocean4, "east" -> ocean7, "south" -> northWater, "west" -> westAir))
  ocean7.setNeighbors(Vector("north" -> ocean5, "east" -> ocean8, "south" -> ocean9, "west" -> ocean6))

  northWater.setNeighbors(Vector("north" -> ocean6, "east" -> ocean9, "south" -> northAir, "west" -> westAir))
  ocean9.setNeighbors(Vector("north" -> ocean7, "east" -> ocean8, "south" -> baSingSe, "west" -> northWater))
  northAir.setNeighbors(Vector("north" -> northWater, "east" -> baSingSe, "south" -> roku, "west" -> sunWarriors))
  baSingSe.setNeighbors(Vector("north" -> ocean9, "east" -> desert, "south" -> omashu, "west" -> northAir))


  /** The addition of the items and characters in the appropriate locations. */
  northWater.addItem(new Item("scroll", "It is a guide for waterbenders wishing to learn combative techniques and forms, without requiring a physical teacher."))
  eastAir.addItem(new Item("staff", "It is a wooden stave that enables airbenders to fly by manipulating the air currents around them."))
  baSingSe.addCharacter(new Character("TOPH", "She is an earthbending master, one of the most powerful of her time." +
    "\nBlind since birth, Toph used her earthbending to \"see\" every vibration that passes through the ground."))
  sunWarriors.addCharacter(new Character("ZUKO", "He is a firebending master, born as a prince in the Fire Nation Royal Family." +
    "\nHe rejected his own father, Fire Lord Ozai, and his idea of conquring the world." +
    "\nHe is now a firebending mentor and determined to end the war and restore both his and the Fire Nation's honor."))
  roku.addCharacter(new Character("ROKU", "Upon death, the Avatar Spirit causes the Avatar to reincarnate into the next nation, dictated by the cyclic order: " +
    "fire, air, water, and earth.\nAvatar Roku is the direct successor to the previous Avatar, Kyoshi and the predecessor before you, Aang. Roku was born in the Fire Nation."))



   /** The character who is the protagonist of the adventure and whom the real-life player controls. */
  val player = new Player(southWater)

  /** The number of turns that have passed since the start of the game. */
  var turnCount = 0
  /** The maximum number of turns that this adventure game allows before time runs out. */
  val timeLimit = 40


  /** Determines if the adventure is complete, that is, if the player has won. */
  def isComplete = this.player.location == this.destination && this.player.skills.length == 4

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver = this.isComplete || this.player.hasQuit || this.turnCount == this.timeLimit


  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage = "The world is divided into four elemental nations: The Northern and Southern Water Tribes, the Earth Kingdom, the Fire Nation, and the Air Nomads." +
    "\nThe Avatar upholds the balance between the nations, but everything changed when the Fire Nation invaded." +
    "\nOnly the Avatar, master of all four elements, can stop them. But when the world needed him the most, he vanished." +
    "\nA hundred years later waterbenders discovered the new Avatar, an airbender named Aang." +
    "\n\nAang, you have to master fire, air, water, and earth to battle with the Fire Lord." +
    "\nYou are the only chance to restore the peace and save the world!" +
    "\n\nTo know more about your fateful mission, enter the command 'help'."


  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage = {
    if (this.isComplete)
      "You have mastered all the elements and defeated the Fire Lord.\nYou ended the war and restored the peace!"
    else if (this.turnCount == this.timeLimit)
      "The comet, just skimming the surface of the Earth, gave the power of a hundred suns to the Fire Lord. He is unstoppable now." +
        "\nYou could not make it, Aang! I am sorry..."
    else // game over due to player quitting
      "You wished to quit the game."
  }


  /** Plays a turn by executing the given in-game command, such as "go west". Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  def playTurn(command: String) = {
    val action = new Action(command)
    val outcomeReport = action.execute(this.player)
    if (outcomeReport.isDefined) {
      this.turnCount += 1
    }
    outcomeReport.getOrElse("Unknown command: \"" + command + "\".")
  }

}