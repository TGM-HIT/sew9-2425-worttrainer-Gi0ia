rootProject.name = "worttrainerRemake"
include("src:test:java")
findProject(":src:test:java")?.name = "java"
