/*
 * Copyright © 2018 Stefan Niederhauser (nidin@gmx.ch)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package guru.nidi.d3

import guru.nidi.codeassert.checkstyle.*
import guru.nidi.codeassert.config.AnalyzerConfig
import guru.nidi.codeassert.config.In
import guru.nidi.codeassert.config.Language.KOTLIN
import guru.nidi.codeassert.dependency.*
import guru.nidi.codeassert.findbugs.*
import guru.nidi.codeassert.junit.CodeAssertJunit5Test
import guru.nidi.codeassert.junit.kotlin.KotlinCodeAssertMatchers.hasNoKtlintIssues
import guru.nidi.codeassert.ktlint.KtlintAnalyzer
import guru.nidi.codeassert.ktlint.KtlintCollector
import guru.nidi.codeassert.pmd.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class CodeAnalysisTest : CodeAssertJunit5Test() {
    private val config = AnalyzerConfig.maven(KOTLIN).main()!!

    override fun analyzeDependencies(): DependencyResult {
        return DependencyAnalyzer(config)
                .rules(DependencyRules.denyAll()
                        .withExternals("java.*", "javax.*", "jdk.nashorn.*", "kotlin.*", "org.*")
                        .withRelativeRules(object : DependencyRuler() {
                            override fun defineRules() {
                                base().mayUse(all())
                            }
                        }))
                .analyze()
    }

    override fun analyzeCheckstyle() = CheckstyleAnalyzer(config, CheckstyleConfigs.adjustedGoogleStyleChecks(), StyleEventCollector()
            .apply(CheckstyleConfigs.minimalCheckstyleIgnore()))
            .analyze()!!

    override fun analyzeCpd() = CpdAnalyzer(config, 25, CpdMatchCollector()).analyze()!!

    override fun analyzePmd() = PmdAnalyzer(config, PmdViolationCollector()
            .apply(PmdConfigs.minimalPmdIgnore()))
            .withRulesets(*PmdConfigs.defaultPmdRulesets())
            .analyze()!!

    override fun analyzeFindBugs() = FindBugsAnalyzer(config, BugCollector()
            .apply(FindBugsConfigs.minimalFindBugsIgnore())
            .because("Maybe rethink this", In.clazz(ScaleChromatic.javaClass).ignore("NM_METHOD_NAMING_CONVENTION"))
            .because("Kotlin generated code",
                    In.clazz(D3.javaClass)
                            .ignore("SCRIPT_ENGINE_INJECTION", "RCN_REDUNDANT_NULLCHECK_WOULD_HAVE_BEEN_A_NPE", "SA_LOCAL_SELF_ASSIGNMENT"),
                    In.clazz(Color::class.java)
                            .ignore("BX_UNBOXED_AND_COERCED_FOR_TERNARY_OPERATOR")))
            .analyze()!!

    @Test
    fun ktlint() {
        val result = KtlintAnalyzer(config, KtlintCollector()
                .because("I think it's ok", In.everywhere().ignore("no-wildcard-imports")))
                .analyze()
        assertThat(result, hasNoKtlintIssues())
    }
}
