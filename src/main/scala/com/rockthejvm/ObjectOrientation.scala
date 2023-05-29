package com.rockthejvm

object ObjectOrientation extends App {

  // class and instance
  class Animal {
    val age: Int = 0
    def eat(): Unit = println("I'm eating")
  }
  val anAnimal = new Animal

  // inheritance
  class Dog(val name: String) extends Animal // constructor definition
  val newDog = new Dog("Lassie")

  // constructor arguments are not fields: we need to add val to the constructor definition to make it a field
  newDog.name

  // subtype polymorphism
  val aDeclaredAnimal: Animal =  new Dog("Roger")
  aDeclaredAnimal.eat() // the most derived(specific) method will be called at runtime

  // abstract class
  abstract class WalkingAnimal {
    val hasLegs = true // by default all fields are public, restrict using private / protected
    def walk(): Unit
    private val isPrivate = true // viewable in the class only
    protected val isProtected = true // usable in the class, package and subclasses
  }

  // interface - the ultimate abstract type
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait Philosopher {
    def ?!(thoughts: String): Unit // valid method name, allowing for interesting natural language looking code
  }

  // single-class inheritance, multi-trait "mixing"
  class Crocodile extends Animal with Carnivore with Philosopher {
    override def eat(animal: Animal): Unit = println(s"I am eating you, $animal") // allows for specific implementation
    override def eat(): Unit = super.eat() // allows for usage of Superclass implementation
    override def ?!(thoughts: String): Unit = println(s"I was thinking: $thoughts")
  }

  val aCroc = new Crocodile
  aCroc.eat(newDog)
  aCroc eat newDog // infix notation = object method argument - only available for methods with ONE argument

  // operators are methods in Scala
  val basicMaths = 1 + 2
  val anotherBasicmath = 1.+(2)

  // anonymous classes
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur, I can eat anything")
  }

  /*
  * class Carnivore_Anonymous_32576 extends Carnivore {
  *   override def eat(animal: Animal): Unit = println("I am a dinosaur, I can eat anything" )
  * }
  * val dinosaur = new Carnivore_Anonymous_32576
  */

  // singleton object
  object MySingleton { // the only instance of the MySingleton type
    val mySpecialValue = 54321
    def mySpecialMethod(): Int = 9876
    def apply(x: Int): Int = x + 1
  }
  MySingleton.mySpecialMethod()
  MySingleton.apply(65) // these two lines are the same
  MySingleton(65)       // these two lines are the same

  object Animal { // a class and an object with the same name are called companions - companion object
    // companions can access each other's private fields/methods
    // singleton Animal and instances of Animal are different things
    val canLiveIndefinately = false
  }

  val animalsCanLiveForever = Animal.canLiveIndefinately // accessed in same way as static variables in other languages

  /* case classes = lightweight data structures with some boilerplate
  *   - sensible equals and hashcode
  *   - serialization
  *   - companion with apply
  * */
  case class Person(name: String, age: Int)
  val bob = new Person("Bob", 24)
  val alsoBob = Person("AlsoBob", 25) // => Person.apply("AlsoBob", 25)

  // exceptions
  try {
    // code that can throw
    val x: String = null
    x.length
  } catch {
    case e: Exception => "Some faulty error message"
  } finally {
    // execute some code no matter what (like closing connection to a db)
  }

  // generics
  abstract class MyList[T] {
    def head: T
    def tail: MyList[T]
  }

  val aList: List[Int] = List(1,2,3) // List.apply(1,2,3)
  val first = aList.head // the generic provides the type for this val: Int
  val rest = aList.tail // and this one

  val aStringList = List("hello", "scala") // the whole point of generics is to re-use the same code for different types
  val firstString = aStringList.head // string

  // Point #1: in Scala we usually operate with Immutable values/objects
  val reversedListt = aList.reverse // returns a new object
  /*
    Benefits:
    1). Works miracles in multithreaded environments
    2). helps make sense of the code ("reasoning about the code")
   */

  // Point #2: Scala is closest to the OO ideal, and is actually a very good OO language
}
