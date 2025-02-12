lazy val commonSettings = Seq(
  version := "1.0.0-beta88",
  scalaVersion := "2.13.12"
)


// Ensure Maven Central is used for dependency resolution
resolvers += Resolver.mavenCentral

// Library dependencies
val scalaTest   = "org.scalatest" %% "scalatest" % "3.2.17" % Test
val sprayJson   = "io.spray" %% "spray-json" % "1.3.6" % Compile
val snakeYaml   = "org.yaml" % "snakeyaml" % "2.2" % Compile
val scaffeine   = "com.github.blemale" %% "scaffeine" % "5.2.1" % Compile
val directories = "io.github.soc" % "directories" % "11" % Compile  // Ensure correct version
val fastparse   = "com.lihaoyi" %% "fastparse" % "3.0.2" % Compile
val junit       = "junit" % "junit" % "4.13.2" % Test

(ThisBuild / intellijPluginName) := "IntelliJ-Haskell"

lazy val intellijHaskell = (project in file("."))
  .enablePlugins(SbtIdeaPlugin)
  .settings(commonSettings: _*)
  .settings(
    name := "IntelliJ Haskell",

    // Java and Scala compiler options
    javacOptions ++= Seq("-source", "21", "-target", "21"),
    scalacOptions ++= Seq("-release", "21", "-deprecation", "-feature", "-unchecked"),

    // Dependencies
    libraryDependencies ++= Seq(
      scalaTest,
      sprayJson,
      snakeYaml,
      scaffeine,
      directories,
      fastparse,
      junit
    ),

    // Custom source directories
    (Compile / unmanagedSourceDirectories) += baseDirectory.value / "gen",

    // Speed up test execution
    Test / parallelExecution := true
  )

// IntelliJ Plugin Settings
(ThisBuild / intellijBuild) := "243.23654.189"

intellijPlugins += "com.intellij.java".toPlugin
