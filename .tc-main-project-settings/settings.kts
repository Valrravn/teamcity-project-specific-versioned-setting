import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2024.03"

project {

    buildType(BuildAll)

    subProject(S1)
}

object BuildAll : BuildType({
    name = "Build All"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        showDependenciesChanges = true
    }

    dependencies {
        snapshot(RelativeId("Sample1_Build")) {
        }
        snapshot(RelativeId("Sample2_BuildSample2")) {
        }
    }
})


object S1 : Project({
    name = "S1"

    vcsRoot(S1_HttpsGithubComValrravnTeamcityProjectSpecificVersionedSettingRefsHeadsMain)

    buildType(S1_Build)
})

object S1_Build : BuildType({
    name = "Build"

    vcs {
        root(S1_HttpsGithubComValrravnTeamcityProjectSpecificVersionedSettingRefsHeadsMain)
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})

object S1_HttpsGithubComValrravnTeamcityProjectSpecificVersionedSettingRefsHeadsMain : GitVcsRoot({
    name = "https://github.com/Valrravn/teamcity-project-specific-versioned-setting#refs/heads/main"
    url = "https://github.com/Valrravn/teamcity-project-specific-versioned-setting"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "Valrravn"
        password = "credentialsJSON:44a02572-22ac-4888-a5a9-4186fb062ddb"
    }
})
