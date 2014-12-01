//package molecule
//package examples.seattle
//import molecule.dsl.DbSchema._
//import molecule.examples.seattle.dsl.seattle._
//import scala.language.reflectiveCalls
//
//
//class SeattleQueryTests extends SeattleSpec {
//
//  "A first query" >> {
//
//    // Query of molecule
//    m(Community.name) --> {
//      """[:find ?b
//        | :where
//        |   [?a :community/name ?b]]""".stripMargin
//    }
//  }
//
//
//  "Querying _for_ attribute values" >> {
//
//    // Multiple attributes
//    m(Community.name.url.category) -->
//      """[:find ?b ?c (distinct ?d)
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/url ?c]
//        |   [?a :community/category ?d]]""".stripMargin
//  }
//
//  "Querying _by_ attribute values" >> {
//
//    // Names of twitter communities
//    m(Community.name.`type`.apply("twitter")) -->
//      """[:find ?b
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/type ":community.type/twitter"]]""".stripMargin
//
//
//    // Categories (many-cardinality) of the Belltown community
//    m(Community.name("belltown").category) -->
//      """[:find (distinct ?c)
//        | :where
//        |   [?a :community/name "belltown"]
//        |   [?a :community/category ?c]]""".stripMargin
//
//
//    // Names of news or arts communities - transforms to a query using Rules
//    m(Community.name.category("news", "arts")) -->
//      """[:find ?b
//        | :in $ %
//        | :where
//        |   [?a :community/name ?b]
//        |   (rule1 ?a)]
//        |
//        |INPUTS:
//        |List(
//        |  1 datomic.db.Db@xxx
//        |  2 [[[rule1 ?a] [?a :community/category "news"]]
//        |     [[rule1 ?a] [?a :community/category "arts"]]]
//        |)""".stripMargin
//  }
//
//
//  "Querying across references" >> {
//
//    // Communities in north eastern region
//    // Ref's are modelelled as "Bond"'s (between Atoms)
//    m(Community.name.Neighborhood.District.region("ne")) -->
//      """[:find ?b
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/neighborhood ?c]
//        |   [?c :neighborhood/district ?d]
//        |   [?d :district/region ":district.region/ne"]]""".stripMargin
//
//
//    // Communities and their region
//    m(Community.name.Neighborhood.District.region) -->
//      """[:find ?b ?e2
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/neighborhood ?c]
//        |   [?c :neighborhood/district ?d]
//        |   [?d :district/region ?e]
//        |   [?e :db/ident ?e1]
//        |   [(.getName ^clojure.lang.Keyword ?e1) ?e2]]""".stripMargin
//  }
//
//
//  "Advanced queries - parameterizing queries" >> {
//
//    /** ******* Single input parameter **************************/
//
//    // Community input molecule awaiting some type value
//    m(Community.name.`type`(?)) -->
//      """[:find ?b
//        | :in $ ?c
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/type ?c]]""".stripMargin
//
//    // Applying a value completes the query
//    m(Community.name.`type`(?)).apply("twitter") -->
//      """[:find ?b
//        | :in $ ?c
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/type ?c]]
//        |
//        |INPUTS:
//        |List(
//        |  1 datomic.db.Db@xxx
//        |  2 :community.type/twitter
//        |)""".stripMargin
//
//
//    // Add a `!` to the question mark to return the input value too
//    m(Community.name.`type`(?!)) -->
//      """[:find ?b ?c2
//        | :in $ ?c
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/type ?c]
//        |   [?c :db/ident ?c1]
//        |   [(.getName ^clojure.lang.Keyword ?c1) ?c2]]""".stripMargin
//
//
//    m(Community.name.`type`(?!)).apply("twitter") -->
//      """[:find ?b ?c2
//        | :in $ ?c
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/type ?c]
//        |   [?c :db/ident ?c1]
//        |   [(.getName ^clojure.lang.Keyword ?c1) ?c2]]
//        |
//        |INPUTS:
//        |List(
//        |  1 datomic.db.Db@xxx
//        |  2 :community.type/twitter
//        |)""".stripMargin
//
//
//
//    // Multiple input values - logical OR ------------------------
//
//    m(Community.name.`type`(?!)).apply("facebook_page" or "twitter") -->
//      """[:find ?b ?c2
//        | :in $ [?c ...]
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/type ?c]
//        |   [?c :db/ident ?c1]
//        |   [(.getName ^clojure.lang.Keyword ?c1) ?c2]]
//        |
//        |INPUTS:
//        |List(
//        |  1 datomic.db.Db@xxx
//        |  2 [:community.type/facebook_page, :community.type/twitter]
//        |)""".stripMargin
//
//
//    // Finding communities of type "facebook_page" OR "twitter"
//    // The following 3 notation variations transform in the same way
//    m(Community.name.`type`(?!)).apply("facebook_page" or "twitter") -->
//      //    m(Community.name.`type`(?!)).apply("facebook_page", "twitter") -->
//      //    m(Community.name.`type`(?!)).apply(List("facebook_page", "twitter")) -->
//      """[:find ?b ?c2
//        | :in $ [?c ...]
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/type ?c]
//        |   [?c :db/ident ?c1]
//        |   [(.getName ^clojure.lang.Keyword ?c1) ?c2]]
//        |
//        |INPUTS:
//        |List(
//        |  1 datomic.db.Db@xxx
//        |  2 [:community.type/facebook_page, :community.type/twitter]
//        |)""".stripMargin
//
//
//    /** ******* Multiple input parameters **************************/
//
//    // Single tuple of input values - AND-semantics ------------------------
//
//    m(Community.name.`type`(?).orgtype(?)) -->
//      """[:find ?b
//        | :in $ ?c ?d
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/type ?c]
//        |   [?a :community/orgtype ?d]]""".stripMargin
//
//
//    // The following 3 notation variations transform in the same way
//
//    m(Community.name.`type`(?).orgtype(?)).apply("email_list" and "community") -->
//      //    m(Community.name.`type`(?).orgtype(?)).apply("email_list", "community") -->
//      //    m(Community.name.`type`(?).orgtype(?)).apply(List(("email_list", "community"))) -->
//      """[:find ?b
//        | :in $ [[ ?c ?d ]]
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/type ?c]
//        |   [?a :community/orgtype ?d]]
//        |
//        |INPUTS:
//        |List(
//        |  1 datomic.db.Db@xxx
//        |  2 [[:community.type/email_list, :community.orgtype/community]]
//        |)""".stripMargin
//
//
//    // Multiple tuples of input values ------------------------
//
//    // Communities of some `type` AND some `orgtype` (include input values!)
//    m(Community.name.`type`(?!).orgtype(?!)) -->
//      """[:find ?b ?c2 ?d2
//        | :in $ ?c ?d
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/type ?c]
//        |   [?c :db/ident ?c1]
//        |   [(.getName ^clojure.lang.Keyword ?c1) ?c2]
//        |   [?a :community/orgtype ?d]
//        |   [?d :db/ident ?d1]
//        |   [(.getName ^clojure.lang.Keyword ?d1) ?d2]]""".stripMargin
//
//
//    // The following 3 notation variations transform in the same way
//
//    //    m(Community.name.`type`(?!).orgtype(?!)).apply(("email_list" and "community") or ("website" and "commercial")) -->
//    //    m(Community.name.`type`(?!).orgtype(?!)).apply(("email_list", "community"), ("website", "commercial")) -->
//    m(Community.name.`type`(?!).orgtype(?!)).apply(Seq(("email_list", "community"), ("website", "commercial"))) -->
//      """[:find ?b ?c2 ?d2
//        | :in $ [[ ?c ?d ]]
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/type ?c]
//        |   [?c :db/ident ?c1]
//        |   [(.getName ^clojure.lang.Keyword ?c1) ?c2]
//        |   [?a :community/orgtype ?d]
//        |   [?d :db/ident ?d1]
//        |   [(.getName ^clojure.lang.Keyword ?d1) ?d2]]
//        |
//        |INPUTS:
//        |List(
//        |  1 datomic.db.Db@xxx
//        |  2 [[:community.type/email_list, :community.orgtype/community], [:community.type/website, :community.orgtype/commercial]]
//        |)""".stripMargin
//  }
//
//
//  "Invoking functions in queries" >> {
//
//    m(Community.name < "C") -->
//      """[:find ?b
//        | :where
//        |   [?a :community/name ?b]
//        |   [(.compareTo ^String ?b "C") ?b1]
//        |   [(< ?b1 0) ]]""".stripMargin
//
//    m(Community.name < ?) -->
//      """[:find ?b
//        | :in $ ?b1
//        | :where
//        |   [?a :community/name ?b]
//        |   [(.compareTo ^String ?b ?b1) ?b2]
//        |   [(< ?b2 0) ]]""".stripMargin
//
//    m(Community.name < ?).apply("C") -->
//      """[:find ?b
//        | :in $ ?b1
//        | :where
//        |   [?a :community/name ?b]
//        |   [(.compareTo ^String ?b ?b1) ?b2]
//        |   [(< ?b2 0) ]]
//        |
//        |INPUTS:
//        |List(
//        |  1 datomic.db.Db@xxx
//        |  2 C
//        |)""".stripMargin
//  }
//
//
//  "Querying with fulltext search" >> {
//
//    m(Community.name contains "Wallingford") -->
//      """[:find ?b
//        | :where
//        |   [(fulltext $ :community/name "Wallingford") [[ ?a ?b ]]]]""".stripMargin
//
//
//    m(Community.name contains ?) -->
//      """[:find ?b
//        | :in $ ?b1
//        | :where
//        |   [(fulltext $ :community/name ?b1) [[ ?a ?b ]]]]""".stripMargin
//
//
//    m(Community.name contains ?).apply("Wallingford") -->
//      """[:find ?b
//        | :in $ ?b1
//        | :where
//        |   [(fulltext $ :community/name ?b1) [[ ?a ?b ]]]]
//        |
//        |INPUTS:
//        |List(
//        |  1 datomic.db.Db@xxx
//        |  2 Wallingford
//        |)""".stripMargin
//
//
//    // Fulltext search on many-attribute (`category`)
//
//    m(Community.name.`type`("website").category contains "food") -->
//      """[:find ?b (distinct ?d)
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/type ":community.type/website"]
//        |   [(fulltext $ :community/category "food") [[ ?a ?d ]]]]""".stripMargin
//
//
//    m(Community.name.`type`(?).category contains ?) -->
//      """[:find ?b (distinct ?d)
//        | :in $ ?c ?d1
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/type ?c]
//        |   [(fulltext $ :community/category ?d1) [[ ?a ?d ]]]]""".stripMargin
//
//
//    m(Community.name.`type`(?).category contains ?).apply("website", Set("food")) -->
//      """[:find ?b (distinct ?d)
//        | :in $ [[ ?c ?d1 ]]
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/type ?c]
//        |   [(fulltext $ :community/category ?d1) [[ ?a ?d ]]]]
//        |
//        |INPUTS:
//        |List(
//        |  1 datomic.db.Db@xxx
//        |  2 [[:community.type/website, Set(food)]]
//        |)""".stripMargin
//  }
//
//
//  "Querying with rules (logical OR)" >> {
//
//    m(Community.name.`type`("twitter" or "facebook_page")) -->
//      """[:find ?b
//        | :in $ %
//        | :where
//        |   [?a :community/name ?b]
//        |   (rule1 ?a)]
//        |
//        |INPUTS:
//        |List(
//        |  1 datomic.db.Db@xxx
//        |  2 [[[rule1 ?a] [?a :community/type ":community.type/twitter"]]
//        |     [[rule1 ?a] [?a :community/type ":community.type/facebook_page"]]]
//        |)""".stripMargin
//
//
//    m(Community.name.Neighborhood.District.region("ne" or "sw")) -->
//      """[:find ?b
//        | :in $ %
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/neighborhood ?c]
//        |   [?c :neighborhood/district ?d]
//        |   (rule1 ?d)]
//        |
//        |INPUTS:
//        |List(
//        |  1 datomic.db.Db@xxx
//        |  2 [[[rule1 ?d] [?d :district/region ":district.region/ne"]]
//        |     [[rule1 ?d] [?d :district/region ":district.region/sw"]]]
//        |)""".stripMargin
//
//
//    // Social media in southern regions
//    m(Community.name.`type`("twitter" or "facebook_page").Neighborhood.District.region("sw" or "s" or "se")) -->
//      """[:find ?b
//        | :in $ %
//        | :where
//        |   [?a :community/name ?b]
//        |   (rule1 ?a)
//        |   [?a :community/neighborhood ?d]
//        |   [?d :neighborhood/district ?e]
//        |   (rule2 ?e)]
//        |
//        |INPUTS:
//        |List(
//        |  1 datomic.db.Db@xxx
//        |  2 [[[rule1 ?a] [?a :community/type ":community.type/twitter"]]
//        |     [[rule1 ?a] [?a :community/type ":community.type/facebook_page"]]
//        |     [[rule2 ?e] [?e :district/region ":district.region/sw"]]
//        |     [[rule2 ?e] [?e :district/region ":district.region/s"]]
//        |     [[rule2 ?e] [?e :district/region ":district.region/se"]]]
//        |)""".stripMargin
//
//
//    m(Community.name.`type`(?).Neighborhood.District.region(?)) -->
//      """[:find ?b
//        | :in $ ?c ?f //        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/type ?c]
//        |   [?a :community/neighborhood ?d]
//        |   [?d :neighborhood/district ?e]
//        |   [?e :district/region ?f]]""".stripMargin
//
//
//    m(Community.name.`type`(?).Neighborhood.District.region(?)).apply(
//      ("twitter" or "facebook_page") and ("sw" or "s" or "se")
//      // or
//      // Seq("twitter", "facebook_page"), Seq("sw", "s", "se")
//    ) -->
//      """[:find ?b
//        | :in $ %
//        | :where
//        |   [?a :community/name ?b]
//        |   [?a :community/type ?c]
//        |   [?a :community/neighborhood ?d]
//        |   [?d :neighborhood/district ?e]
//        |   [?e :district/region ?f]
//        |   (rule1 ?a)
//        |   (rule2 ?e)]
//        |
//        |INPUTS:
//        |List(
//        |  1 datomic.db.Db@xxx
//        |  2 [[[rule1 ?a] [?a :community/type ":community.type/twitter"]]
//        |     [[rule1 ?a] [?a :community/type ":community.type/facebook_page"]]
//        |     [[rule2 ?e] [?e :district/region ":district.region/sw"]]
//        |     [[rule2 ?e] [?e :district/region ":district.region/s"]]
//        |     [[rule2 ?e] [?e :district/region ":district.region/se"]]]
//        |)""".stripMargin
//  }
//
//
//  "Working with time" >> {
//
//    implicit val conn = loadFromFiles("seattle-schema1a.dtm", "seattle-data0a.dtm", 2)
//
//
//    m(Db.txI) -->
//      """[:find ?b
//        | :where [?a :db/txInstant ?b]]""".stripMargin
//  }
//
//
//  "Manipulating data - insert" >> {
//
//    implicit val conn = loadSeattle(3)
//
//
//    /** Insert data into molecule and save ***********************************************/
//
//    testInsertMolecule(
//      Community
//        .name("AAA")
//        .url("myUrl")
//        .`type`("twitter")
//        .orgtype("personal")
//        .category("my", "favorites")
//        .Neighborhood.name("myNeighborhood")
//        .District.name("myDistrict").region("nw")
//    ) -->
//      //  Some things to notice:
//      //  - We start from the end of the molecule and traverse left. This allow us to create
//      //    the entities that we will subsequently refer to (#db/id[:db.part/user -1000001])
//      //  - Enum values are prefixed with their namespace ("nw" becomes ":district.region/nw")
//      //  - Multiple values of many-cardinality attributes each get their own statement ("my" + "favorites")
//      //
//      //           action             temp id                   attribute                 value
//      """List(
//        |  List(  :db/add,   #db/id[:db.part/user -1000001],   :district/region       ,   :district.region/nw             )
//        |  List(  :db/add,   #db/id[:db.part/user -1000001],   :district/name         ,   myDistrict                      )
//        |  List(  :db/add,   #db/id[:db.part/user -1000002],   :neighborhood/district ,   #db/id[:db.part/user -1000001]  )
//        |  List(  :db/add,   #db/id[:db.part/user -1000002],   :neighborhood/name     ,   myNeighborhood                  )
//        |  List(  :db/add,   #db/id[:db.part/user -1000003],   :community/neighborhood,   #db/id[:db.part/user -1000002]  )
//        |  List(  :db/add,   #db/id[:db.part/user -1000003],   :community/category    ,   my                              )
//        |  List(  :db/add,   #db/id[:db.part/user -1000003],   :community/category    ,   favorites                       )
//        |  List(  :db/add,   #db/id[:db.part/user -1000003],   :community/orgtype     ,   :community.orgtype/personal     )
//        |  List(  :db/add,   #db/id[:db.part/user -1000003],   :community/type        ,   :community.type/twitter         )
//        |  List(  :db/add,   #db/id[:db.part/user -1000003],   :community/url         ,   myUrl                           )
//        |  List(  :db/add,   #db/id[:db.part/user -1000003],   :community/name        ,   AAA                             )
//        |)""".stripMargin
//
//
//    /** Use molecule as template to insert matching data sets ********************************************/
//
//    m(Community.name.url.`type`.orgtype.category.Neighborhood.name.District.name.region) -->
//      List(
//        List("DDD Blogging Georgetown", "http://www.blogginggeorgetown.com/", "blog", "commercial", Set("DD cat 1", "DD cat 2"), "DD Georgetown", "Greater Duwamish", "s"),
//        List("DDD Interbay District Blog", "http://interbayneighborhood.neighborlogs.com/", "blog", "community", Set("DD cat 3"), "DD Interbay", "Magnolia/Queen Anne", "w")
//      ) -->
//      // Semantically identical to the previous transaction
//      """List(
//        |  List(  :db/add,   #db/id[:db.part/user -1000001],   :district/region       ,   :district.region/s                             )
//        |  List(  :db/add,   #db/id[:db.part/user -1000001],   :district/name         ,   Greater Duwamish                               )
//        |  List(  :db/add,   #db/id[:db.part/user -1000002],   :neighborhood/district ,   #db/id[:db.part/user -1000001]                 )
//        |  List(  :db/add,   #db/id[:db.part/user -1000002],   :neighborhood/name     ,   DD Georgetown                                  )
//        |  List(  :db/add,   #db/id[:db.part/user -1000003],   :community/neighborhood,   #db/id[:db.part/user -1000002]                 )
//        |  List(  :db/add,   #db/id[:db.part/user -1000003],   :community/category    ,   DD cat 1                                       )
//        |  List(  :db/add,   #db/id[:db.part/user -1000003],   :community/category    ,   DD cat 2                                       )
//        |  List(  :db/add,   #db/id[:db.part/user -1000003],   :community/orgtype     ,   :community.orgtype/commercial                  )
//        |  List(  :db/add,   #db/id[:db.part/user -1000003],   :community/type        ,   :community.type/blog                           )
//        |  List(  :db/add,   #db/id[:db.part/user -1000003],   :community/url         ,   http://www.blogginggeorgetown.com/             )
//        |  List(  :db/add,   #db/id[:db.part/user -1000003],   :community/name        ,   DDD Blogging Georgetown                        )
//        |  List(  :db/add,   #db/id[:db.part/user -1000004],   :district/region       ,   :district.region/w                             )
//        |  List(  :db/add,   #db/id[:db.part/user -1000004],   :district/name         ,   Magnolia/Queen Anne                            )
//        |  List(  :db/add,   #db/id[:db.part/user -1000005],   :neighborhood/district ,   #db/id[:db.part/user -1000004]                 )
//        |  List(  :db/add,   #db/id[:db.part/user -1000005],   :neighborhood/name     ,   DD Interbay                                    )
//        |  List(  :db/add,   #db/id[:db.part/user -1000006],   :community/neighborhood,   #db/id[:db.part/user -1000005]                 )
//        |  List(  :db/add,   #db/id[:db.part/user -1000006],   :community/category    ,   DD cat 3                                       )
//        |  List(  :db/add,   #db/id[:db.part/user -1000006],   :community/orgtype     ,   :community.orgtype/community                   )
//        |  List(  :db/add,   #db/id[:db.part/user -1000006],   :community/type        ,   :community.type/blog                           )
//        |  List(  :db/add,   #db/id[:db.part/user -1000006],   :community/url         ,   http://interbayneighborhood.neighborlogs.com/  )
//        |  List(  :db/add,   #db/id[:db.part/user -1000006],   :community/name        ,   DDD Interbay District Blog                     )
//        |)""".stripMargin
//  }
//
//
//  "Manipulating data - update/retract" >> {
//
//    implicit val conn = loadSeattle(4)
//
//    val belltownId: Long = Community.name("belltown").ids.head
//
//
//    // One-cardinality attributes ..............................
//
//    // Assert new value
//    testUpdateMolecule(
//      Community.name("belltown 2").url("url 2")
//    ) --> belltownId -->
//      """List(
//        |  List(  :db/add,   17592186045888,   :community/url ,   url 2       )
//        |  List(  :db/add,   17592186045888,   :community/name,   belltown 2  )
//        |)""".stripMargin
//
//
//    // Many-cardinality attributes ............................
//
//    // Retract current value + assert new value
//    testUpdateMolecule(
//      Community.category("news" -> "Cool news")
//    ) --> belltownId -->
//      """List(
//        |  List(  :db/retract,   17592186045888,   :community/category,   news       )
//        |  List(  :db/add    ,   17592186045888,   :community/category,   Cool news  )
//        |)""".stripMargin
//
//
//    // Update multiple categories
//    testUpdateMolecule(
//      Community.category(
//        "Cool news" -> "Super cool news",
//        "events" -> "Super cool events"
//      )
//    ) --> belltownId -->
//      """List(
//        |  List(  :db/retract,   17592186045888,   :community/category,   Cool news          )
//        |  List(  :db/add    ,   17592186045888,   :community/category,   Super cool news    )
//        |  List(  :db/retract,   17592186045888,   :community/category,   events             )
//        |  List(  :db/add    ,   17592186045888,   :community/category,   Super cool events  )
//        |)""".stripMargin
//
//
//    // Add a category
//    testUpdateMolecule(
//      Community.category.add("extra category")
//    ) --> belltownId -->
//      """List(
//        |  List(  :db/add,   17592186045888,   :community/category,   extra category  )
//        |)""".stripMargin
//
//
//    // Remove a category
//    testUpdateMolecule(
//      Community.category.remove("Super cool events")
//    ) --> belltownId -->
//      """List(
//        |  List(  :db/retract,   17592186045888,   :community/category,   Super cool events  )
//        |)""".stripMargin
//
//
//    // Mixing updates and deletes..........................
//
//    // Applying nothing (empty parenthesises) finds and retract all values of an attribute
//    // Note how the name is updated at the same time
//    testUpdateMolecule(
//      Community.name("belltown 3").url().category()
//    ) --> belltownId -->
//      """List(
//        |  List(  :db/retract,   17592186045888,   :community/category,   events                          )
//        |  List(  :db/retract,   17592186045888,   :community/category,   news                            )
//        |  List(  :db/retract,   17592186045888,   :community/url     ,   http://www.belltownpeople.com/  )
//        |  List(  :db/add    ,   17592186045888,   :community/name    ,   belltown 3                      )
//        |)""".stripMargin
//  }
//}