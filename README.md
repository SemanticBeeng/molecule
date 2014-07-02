# Molecule

Molecule is a type safe and intuitive Scala query/modelling DSL for 
[Datomic][datomic] - the immutable database of facts. As an
 example: to find

_Names of twitter/facebook_page communities in neighborhoods of southern districts_
 
we can compose a "molecule query" that is very close to our
human sentence:

```scala
Community.name.`type`("twitter" or "facebook_page")
  .Neighborhood.District.region("sw" or "s" or "se")
```

Molecule transforms this to a little more elaborate Datalog query string and
 input rules that finds those communities in the Datomic database:

<pre>
[:find ?a
 :in $ %
 :where
   [?ent :community/name ?a]
   (rule1 ?ent)
   [?ent :community/neighborhood ?c]
   [?c :neighborhood/district ?d]
   (rule2 ?d)]

INPUTS:
List(
  datomic.db.Db@xxx,
  [[[rule1 ?ent] [?ent :community/type ":community.type/twitter"]]
   [[rule1 ?ent] [?ent :community/type ":community.type/facebook_page"]]
   [[rule2 ?d] [?d :district/region ":district.region/sw"]]
   [[rule2 ?d] [?d :district/region ":district.region/s"]]
   [[rule2 ?d] [?d :district/region ":district.region/se"]]]
)
</pre>

#### Benefits

By not having to write such complex Datalog queries and rules "by hand", Molecule 
allows you to

- Type less
- Make type safe queries with inferred return types
- Use your domain terms directly as query building blocks
- Model complex queries intuitively (easier to understand and maintain)
- Reduce syntactic noise
- Focus more on your domain and less on queries

#### Possible drawbacks

We still need to explore how far Molecule can match the expressive powers
 of Datalog. So far, all 
 examples in the
[Seattle tutorial][seattle] have been 
"molecularized" succesfully (see the 
[Molecule Seattle tutorial][tutorial] and 
[code][tutorialcode]). So as a proof-of-concept it looks promising...

## Getting started

- [Quick introduction][intro] to Datomic/Molecule
- [Setup Database][setup]: initiate a Datomic database and create a database schema with Molecule
- [Populate Database][populate]: populate a Datomic database with Molecule
- [Molecule Seattle tutorial][tutorial] examples of using Molecule (based on the 
[Datomic Seattle tutorial][seattle])
- Tests in [SeattleQueries][tutorialqueries] shows the queries produced by the molecules in the tutorial
- Tests in [SeattleTransformations][tutorialtransformations] shows the full dsl -> model -> query -> query string 
transformations of molecules

## Using Molecule

Molecule 0.1.0 for Scala 2.11.1 will soon be available at 
[Sonatype](https://oss.sonatype.org/index.html#nexus-search;quick%7Escaladci)
 so that you can

1. Add `"org.scaladatomic" % "molecule_2.11.1" % "0.1.0"` to your sbt build file.
2. Define your domain in a [schema definition file][setup]
3. `sbt compile`
4. `sbt`
5. `gen-idea` to create your project // if you're using IntelliJ
6. [Setup your database][setup]
7. [Populate your database][populate] with data
8. [Make molecule queries][tutorial]

Please feel free to [submit bugs](), come with pull requests and suggestions. 

The [ScalaDatomic group]() is also open for your Molecule questions.

Have fun!

Marc Grue<br>
June 2014


### Resources
- [Datomic][datomic] website

[datomic]: http://www.datomic.com
[seattle]: http://docs.datomic.com/tutorial.html

[intro]: https://github.com/ScalaDatomic/molecule/wiki/1.1.-Quick-introduction
[setup]: https://github.com/ScalaDatomic/molecule/wiki/1.2.-Setup-the-database
[populate]: https://github.com/ScalaDatomic/molecule/wiki/1.3.-Populate-the-database
[tutorial]: https://github.com/ScalaDatomic/molecule/wiki/1.4.-Molecule-Seattle-tutorial
[tutorialcode]: https://github.com/ScalaDatomic/molecule/blob/master/examples/src/test/scala/molecule/examples/seattle/SeattleTests.scala
[tutorialqueries]: https://github.com/ScalaDatomic/molecule/blob/master/examples/src/test/scala/molecule/examples/seattle/SeattleQueryTests.scala
[tutorialtransformations]: https://github.com/ScalaDatomic/molecule/blob/master/examples/src/test/scala/molecule/examples/seattle/SeattleTransformationTests.scala
