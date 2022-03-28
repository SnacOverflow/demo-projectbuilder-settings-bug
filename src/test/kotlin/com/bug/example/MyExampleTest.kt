package com.bug.example

import org.gradle.api.internal.project.DefaultProject
import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder

class MyExampleTest {

    @Test
    fun intended_use_case() {
        val project = ProjectBuilder.builder().build()

        project.afterEvaluate {
            project.tasks.forEach { println(it) } // Throws 'The settings are not yet available for build 'test'.'
        }

        (project as DefaultProject).evaluate() // request calling afterEvaluate closures
    }


    @Test
    fun simplified_problem_isolation() {
        val project = ProjectBuilder.builder().build()

        project.getTasksByName("tasks", false) // get tasks, which also runs afterEvaluate closures

        project.tasks.forEach { println(it) } // Throws 'The settings are not yet available for build 'test'.'
    }
}