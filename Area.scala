package mytextadventure

import scala.collection.mutable.Map

/** The class `Area` represents locations in a text adventure game world. A game world
  * consists of areas. Player can be located in the area, and each area can have exits leading to
  * other, neighboring areas. An area has a name and a description.
  * @param name         the name of the area
  * @param description  a basic description of the area (typically not including information about items or characters) */
class Area(var name: String, var description: String) {

  private val neighbors = Map[String, Area]()
  private val contents = Map[String, Item]()
  private val characters = Map[String, Character]()

  /** Returns the area that can be reached from this area by moving in the given direction. */
  def neighbor(direction: String) = this.neighbors(direction)

  /** Adds exits from this area to the given areas.
    * @param exits  contains pairs consisting of a direction and the neighboring area in that direction */
  def setNeighbors(exits: Vector[(String, Area)]): Unit = {
    this.neighbors ++= exits
  }

  /** Returns a multi-line description of the area as a player sees it. This includes a basic
    * description of the area as well as information about items and characters. If there are no
    * items or characters present, the return value has the form "DESCRIPTION".
    * If there are one or more items present, the return value has the form "DESCRIPTION\nYou see here: ITEMS SEPARATED BY SPACES".
    * If there are one or more characters present, the return value has the form "DESCRIPTION\nCHARACTER.NAME\nCHARACTER.DESCRIPTION".
    * The items and characters are listed in an arbitrary order. */
  def fullDescription = {
    val contentsList = if (this.contents.isEmpty) "" else "\nYou see here: " + this.contents.values.mkString(" ")
    val character = if (this.characters.isEmpty) "" else "\n" + this.characters.values.mkString
    this.description + contentsList + character
  }

  /** Returns a single-line description of the area for debugging purposes. */
  override def toString = this.name + ": " + this.description.replaceAll("\n", " ").take(150)


  /** Places an item in the area so that it can be, for instance, picked up. */
  def addItem(item: Item): Unit = {
    this.contents.put(item.name, item)
  }

  /** Places a character in the area so that it can be, for instance, learned-from. */
  def addCharacter(character: Character) = {
    this.characters.put(character.name, character)
  }


  /** Determines if the area has the character of the given name. */
  def has(character: String) = this.characters.contains(character)


  /** Determines if the area contains an item of the given name. */
  def contains(itemName: String) = this.contents.contains(itemName)


  /** Removes the item of the given name from the area, assuming an item with that name
    * was there to begin with. Returns the removed item wrapped in an `Option` or `None`
    * in the case there was no such item present. */
  def removeItem(itemName: String) = {
    this.contents.remove(itemName)
  }

}
