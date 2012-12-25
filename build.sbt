name := "FrittRegnskap"

version := "1.0"

resolvers += "Sonatype" at "https://repository.sonatype.org/"

resolvers += Classpaths.typesafeResolver

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

libraryDependencies += "org.scalatest" % "scalatest_2.9.0" % "1.6.1"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.21"

libraryDependencies += "net.databinder.dispatch" %% "dispatch-core" % "0.9.5"

libraryDependencies +=  "net.noerd" %% "prequel" % "0.3.8" changing()

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.1.1")