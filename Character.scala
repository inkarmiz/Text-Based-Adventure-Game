package mytextadventure

class Character(val name: String, val description: String) {

  /** Returns a short textual representation of the character (their name, that is). */
  override def toString = this.name + "\n" + this.description

}
