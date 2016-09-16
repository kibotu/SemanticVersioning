package net.kibotu

import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class SemanticVersioningPluginSpec extends Specification {

    def "apply() should load the plugin"() {
        given:
        def project = ProjectBuilder.builder().build()

        when:
        project.with {
            apply plugin: 'SemanticVersioning'
        }

        then:
        project.plugins.hasPlugin(SemanticVersioningPlugin)
    }

}
