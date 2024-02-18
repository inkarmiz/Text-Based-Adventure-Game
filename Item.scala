package mytextadventure

class Item(val name: String, val description: String) {

  /** Returns a short textual representation of the item (its name, that is). */
  override def toString = this.name

}