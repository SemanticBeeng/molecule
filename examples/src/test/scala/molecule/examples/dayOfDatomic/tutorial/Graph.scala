//package molecule
//package examples.dayOfDatomic.tutorial
//import molecule.examples.dayOfDatomic.spec.DayOfAtomicSpec
////import molecule.examples.dayOfDatomic.GraphSchema
//class Graph extends DayOfAtomicSpec {
//
//  "Graph" >> {
//    implicit val conn = init("graph", "graph.edn")
//
//    // Roles of User1 in Group2
//    m(User("User1").Groups("Group2").Roles)
//
//    // Roles of User1 in all groups
//    m(User("User1").Groups.Roles)
//  }
//
//  "Graph" >> {
//    implicit val conn = init("graph", "graph.edn")
//
//    // Users
//    m(User.name)
//
//    // Users with their groups
//    m(User.name.Groups.name)
//
//    // User1's groups (or "Groups of User1")
//    m(User.name("User1").Groups.name)
//
//    // Users in Group2 (badly optimized)
//    m(User.name.Groups.name("Group2"))
//    // Group2's users (better optimized with most specific data first)
//    m(Group.name("Group2").Users.name)
//
//
//    // Users with their groups and their roles
//    // map(User -> map(Group -> Role))
//    m(User.name.Groups.name.Roles.name)
//
//    // User1's groups and their roles
//    // User1, map(Group -> Role)
//    m(User.name("User1").Groups.name.Roles.name)
//
//    // Hyper-edges
//    // "A chained fact" that involves 3 or more entities
//
//    // Normal fact:
//    // John likes Pizza
//    //  e1          e2
//
//    // Hyper-fact (3-arity):
//    // John likes Pizza at PizzaHut
//    //  e1          e2        e3
//
//    // Hyper-fact (4-arity):
//    // John likes Pizza at PizzaHut in Boston
//    //  e1          e2        e3         e4
//
//    // etc...
//
//    // Back to our domain
//    // User1 in Group2 has Role1
//    //  e1        e2        e3
//
//    // John on Board is Chairman
//    //  e1       e2        e3
//
//    // User1 in Group2's roles
//    m(User.name("User1").Groups.name("Group2").Roles.name)
//
//    // Group2's User1's roles
//    m(Groups.name("Group2").User.name("User1").Roles.name)
//
//    // Roles of User1 in Group2
//    m(Roles.name.User.name("User1").Groups.name("Group2"))
//  }
//
//  "Lazy Graph" >> {
//    implicit val conn = init("lazy graph", "graph.edn")
//
//    // Users
//    m(User)
//
//    // Users with their groups
//    m(User.Groups)
//
//    // User1's groups (or "Groups of User1")
//    m(User("User1").Groups)
//
//    // Users in Group2 (badly optimized)
//    m(User.Groups("Group2"))
//    // Group2's users (better optimized with most specific data first)
//    m(Group("Group2").Users)
//
//
//    // Users with their groups and their roles
//    // map(User -> map(Group -> Role))
//    m(User.Groups.Roles)
//
//    // User1's groups and their roles
//    // User1, map(Group -> Role)
//    m(User("User1").Groups.Roles)
//
//    // Group2's roles
//    m(Group("Group2").Roles)
//    // Group2's roles of users
//    m(User.Groups("Group2").Roles)
//
//    // Hyper-edges
//    // "A chained fact" that involves 3 or more entities
//
//    // Normal fact:
//    // John likes Pizza
//    //  e1          e2
//
//    // Hyper-fact (3-arity):
//    // John likes Pizza at PizzaHut
//    //  e1          e2        e3
//
//    // Hyper-fact (4-arity):
//    // John likes Pizza at PizzaHut in Boston
//    //  e1          e2        e3         e4
//
//    // etc...
//
//    // Back to our domain
//    // User1 in Group2 has Role1
//    //  e1        e2        e3
//
//    // John on Board is Chairman
//    //  e1       e2        e3
//
//    // User1 in Group2's roles
//    // Roles of User1 in Group2
//    m(User("User1").Groups("Group2").Roles)
//
//
//    m(User("User1").Groups.Roles)
//
//    //    // User1's roles in Group2
//    //    m(User("User1").Roles.Group("Group2"))
//
//
//    //    // Group2's User1's roles
//    //    m(Group("Group2").Users("User1").Roles)
//    //
//    //    // Group2's roles for User1
//    //    m(Group("Group2").Roles.User("User1"))
//    //
//    //
//    //    // Roles of User1 in Group2
//    //    m(Role.User("User1").Groups("Group2"))
//    //
//    //    // Roles of Group2's User1
//    //    m(Role.Groups("Group2").User("User1"))
//  }
//  "More complicated graph" >> {
//
//  }
//}