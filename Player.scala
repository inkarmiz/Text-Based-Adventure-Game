package mytextadventure

import scala.collection.mutable.{ListBuffer, Map}


/** A `Player` object represents a player character controlled by the real-life user of the program.
  * A player object's state is mutable: the player's location, possessions and bending abilities can change, for instance.
  * @param startingArea  the initial location of the player */

class Player(startingArea: Area) {

  private var currentLocation = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag
  private val possessions = Map[String, Item]()     // container of all the items that the player has
  private val bending = ListBuffer[String]()        // list of all the elements mastered

  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven

  /** Returns the current location of the player. */
  def location = this.currentLocation

  /** Returns the list of all the elements mastered by the player. */
  def skills = this.bending

  /** Attempts to move the player in the given direction.
    * Returns a description of the result: "You go DIRECTION.".
    * If player selects anything other than the four directions, returns
    * "You have to choose from four cardinal directions: south, east, north or west." */
  def go(direction: String) = {
    val directionEdited = direction.toLowerCase
    val fourDirections = ListBuffer[String]("south", "east", "north", "west")
    if (fourDirections.contains(directionEdited)) {
      this.currentLocation = this.location.neighbor(direction)
      "You go " + direction + "."
    } else
      "You have to choose from four cardinal directions: south, east, north or west."
  }

  /** Signals that the player wants to quit the game. Returns a description of what happened
    * within the game as a result (which is the empty string, in this case). */
  def quit() = {
    this.quitCommandGiven = true
    ""
  }

  /** Returns a brief description of the player's state, for debugging purposes. */
  override def toString = "Now at: " + this.location.name

  /** Tries to pick up an item of the given name. This is successful if such an item is
    * located in the player's current location. If so, the item is added to the player's
    * inventory. Returns a description of the result: "You pick up the ITEM." or
    * "There is no ITEM here to pick up.". In addition,
    * when "scroll" is picked up, "water" is added to bending.
    * when "staff" is picked up, then "air" is added to bending.*/
  def get(itemName: String) = {
    val received = this.location.removeItem(itemName)
    for (newItem <- received) {
      this.possessions.put(newItem.name, newItem)
    }
    if (received.isDefined) {
      if (itemName == "scroll") this.bending += "water" else this.bending += "air"
      "You pick up the " + itemName + "."
    } else
      "There is no " + itemName + " here to pick up."
  }

  /** Determines whether the player is carrying an item of the given name. */
  def has(itemName: String) = this.possessions.contains(itemName)

  /** Tries to drop an item of the given name. This is successful if such an item is
    * currently in the player's possession. If so, the item is removed from the
    * player's inventory and placed in the area. Returns a description of the result
    * of the attempt: "You drop the ITEM." or "You don't have that!".
    * If "scroll" is dropped, "water" is removed from bending.
    * * If "staff" is droped, then "air" is removed from bending.
    * */
  def drop(itemName: String) = {
    val removed = this.possessions.remove(itemName)
    for (oldItem <- removed) {
      this.location.addItem(oldItem)
    }

    if (removed.isDefined) {
      if (itemName == "scroll") this.bending -= "water" else this.bending -= "air"
      "You drop the " + itemName + "."
    } else
      "You don't have that!"
  }


  /** Causes the player to examine the item of the given name. This is successful if such
    * an item is currently in the player's possession. Returns a description of the result,
    * which, if the attempt is successful, includes a description of the item. The description
    * has the form: "You look closely at the ITEM.\nDESCRIPTION" or "If you want
    * to examine something, you need to pick it up first." */
  def examine(itemName: String) = {
    def lookText(item: Item) = "You look closely at the " + item.name + ".\n" + item.description
    val failText = "If you want to examine something, you need to pick it up first."
    this.possessions.get(itemName).map(lookText).getOrElse(failText)
  }


  /** Causes the player to learn from the character, if the character is present in that area.
    * "TOPH" teaches the player to earthbend, while "ZUKO" helps to master firebending. Corresponding elements are added to bending.
    * "ROKU" warns about the time limit.
    * If learn-from is attempted on character more than once, the return value has the form "You have improved your skills."
    * If character is not found, the return value has the form "There is no such person in this area."*/
  def learn(character: String) = {
    if (currentLocation.has(character)) {
      if (character == "TOPH" && !this.bending.contains("earth")) {
        this.bending += "earth"
        "You have mastered an earthbending."
      } else if (character == "ZUKO" && !this.bending.contains("fire")) {
        this.bending += "fire"
        "You have mastered a firebending."
      } else if (character == "ROKU")
        "Avatar Roku warned you of the impending comet." +
          "\nWhen the comet is at the surface of the upper atmosphere, firebenders are able to harness its energy, greatly increasing their powers." +
          "\nFire Lord Ozai is planning to use its power to end the war. You have to hurry, Aang!"
      else
        "You have improved your skills."

    } else
      "There is no such person in this area."
  }

  /** Checks whether the player mastered the elements.
    * If not, the return value has the form "You have not completely mastered any elements."
    * If player already mastered an element, the return value has the form "You have mastered:\nELEMENTS ON SEPARATE LINES*/
  def mastered = {
    if (this.bending.isEmpty)
      "You have not completely mastered any elements."
    else
      "You have mastered:\n" + this.bending.mkString("\n")
  }

  /** The short description of the task, which is what the player should do to win the game. */
  def help = {
    "You have to beat Fire Lord Ozai after mastering all four elements:" +
      "\nWaterbending is learnt using a scroll." +
      "\nAn airbending requires a staff." +
      "\nEarth can be taught by the powerful earthbender." +
      "\nYour firebending mentor, ZUKO, is waiting for you in the land of Sun Warriors." +
      "\nRemember! You should complete your task before summer. The preceding Avatar, Roku, will tell you why. Find him!" +
      "\n\nAvailable commands: go, quit, inventory, get, drop, examine, mastered, learn-from, help"
  }


  /** Causes the player to list what they are carrying. Returns a listing of the player's
    * possessions or a statement indicating that the player is carrying nothing. The return
    * value has the form "You are carrying:\nITEMS ON SEPARATE LINES" or "You are empty-handed."
    * The items are listed in an arbitrary order. */
  def inventory = {
    if (this.possessions.isEmpty)
      "You are empty-handed."
    else
      "You are carrying:\n" + this.possessions.keys.mkString("\n")
  }

}


