package molecule.examples.dayOfDatomic.tutorial
import molecule._
import molecule.examples.dayOfDatomic.dsl.aggregates._
import molecule.examples.dayOfDatomic.schema.AggregatesSchema
import molecule.examples.dayOfDatomic.spec.DayOfAtomicSpec


class Aggregates extends DayOfAtomicSpec {

  implicit val conn = load(AggregatesSchema.tx, "Aggregates")

  val planets  = Seq("Sun", "Jupiter", "Saturn", "Uranus", "Neptune", "Earth", "Venus", "Mars", "Ganymede", "Titan", "Mercury", "Callisto", "Io", "Moon", "Europa", "Triton", "Eris")
  val radiuses = Seq(696000.0, 69911.0, 58232.0, 25362.0, 24622.0, 6371.0, 6051.8, 3390.0, 2631.2, 2576.0, 2439.7, 2410.3, 1821.5, 1737.1, 1561.0, 1353.4, 1163.0)
  val url      = "http://en.wikipedia.org/wiki/List_of_Solar_System_objects_by_size"

  // Insert data with tx meta data
  Obj.name.meanRadius.tx(Data.source_(url)) insert (planets zip radiuses)


  "Aggregated Attributes" >> {

    // Maximum value(s)
    Obj.meanRadius(max).get.head === 696000.0
    Obj.meanRadius(max(3)).get.head === List(696000.0, 69911.0, 58232.0)


    // Minimum value(s)
    Obj.meanRadius(min).get.head === 1163.0
    Obj.meanRadius(min(3)).get.head === List(1163.0, 1353.4, 1561.0)


    // Random value(s) - duplicates possible
    val random = Obj.meanRadius(rand).get.head
    radiuses must contain(random)

    val randoms = Obj.meanRadius(rand(5)).get.head.toList
    randoms.size === 5
    randoms.map(radiuses.contains(_)).reduce(_ && _) === true


    // Sample values - no duplicates
    val samples = Obj.meanRadius(sample(5)).get.head.toList
    samples.size === 5
    samples.distinct.size === 5
    samples.map(radiuses.contains(_)).reduce(_ && _) === true


    println("Random:  " + random)
    println("Randoms: " + randoms)
    println("Samples: " + samples + "\n")
    ok
  }


  "Aggregate Calculations" >> {

    Obj.name(count).get.head === 17

    Obj.name(countDistinct).get.head === 17

    Obj.meanRadius(sum).get.head === 907633.0

    Obj.meanRadius(avg).get.head === 53390.17647058824

    Obj.meanRadius(median).get.head === 2631.2

    Obj.meanRadius(variance).get.head === 2.6212428511091217E10

    Obj.meanRadius(stddev).get.head === 161902.5278094546
  }


  "Schema aggregations" >> {
    //    import molecule.schemas.Db
    //
    //    // What is the average length of a schema name?
    ////    Db.a(avg.apply(count)).get.head === 5.1
    ////    Db.a.apply(count).apply(avg).get.head === 5.1
    //    Db.a(count)(avg).get.head === 5.1
    //    // or
    //    val attrs = Db.a.get
    //    attrs.map(_.length).sum / attrs.size === 5.1
    //
    //    // Todo Custom aggregates
    //    // ...and the mode(s) -

    // How many attributes and value types does this; schema use ?
    //    Db.a(count).valueType(countDistinct).get.head ===(3, 2)
    ok
  }
}