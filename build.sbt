name := "FrittRegnskap"

version := "1.0"

resolvers += "Sonatype" at "https://repository.sonatype.org/"

resolvers += Classpaths.typesafeResolver

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

libraryDependencies += "org.scalatest" % "scalatest_2.9.0" % "1.6.1"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.21"

libraryDependencies +=  "net.noerd" %% "prequel" % "0.3.8" changing()

libraryDependencies ++= Seq(
    "uk.co.bigbeeconsultants" %% "bee-client" % "0.18.+",
    "org.slf4j" % "slf4j-api" % "1.7.+",
    "ch.qos.logback" % "logback-core"    % "1.0.+",
    "ch.qos.logback" % "logback-classic" % "1.0.+"
)

resolvers += "Big Bee Consultants" at "http://www.bigbeeconsultants.co.uk/repo"




addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.1.1")