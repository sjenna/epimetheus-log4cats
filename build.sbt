import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

ThisBuild / crossScalaVersions := Seq("2.12.14", "2.13.6", "3.0.1")

val epimetheusV = "0.5.0-M2"
val catsV = "2.7.0"
val catsEffectV = "3.3.12"

val log4catsV = "2.3.1"

val specs2V = "4.15.0"

lazy val `epimetheus-log4cats` = project.in(file("."))
  .disablePlugins(MimaPlugin)
  .enablePlugins(NoPublishPlugin)
  .aggregate(core)

lazy val core = project.in(file("core"))
  .settings(
    name := "epimetheus-log4cats",
    libraryDependencies ++= Seq(
      "io.chrisdavenport"           %% "epimetheus"                 % epimetheusV,
      "org.typelevel"               %% "cats-core"                  % catsV,

      "org.typelevel"               %% "cats-effect"                % catsEffectV,

      "org.typelevel"           %% "log4cats-core"              % log4catsV,
      "org.typelevel"           %% "log4cats-testing"           % log4catsV     % Test,

      ("org.specs2"                  %% "specs2-core"                % specs2V       % Test).cross(CrossVersion.for3Use2_13),
      ("org.specs2"                  %% "specs2-scalacheck"          % specs2V       % Test).cross(CrossVersion.for3Use2_13)
    )
  )

lazy val site = project.in(file("site"))
  .disablePlugins(MimaPlugin)
  .enablePlugins(NoPublishPlugin)
  .enablePlugins(DavenverseMicrositePlugin)
  .dependsOn(core)
  .settings(
    micrositeDescription := "Epimetheus Log4cats Metrics",
  )


