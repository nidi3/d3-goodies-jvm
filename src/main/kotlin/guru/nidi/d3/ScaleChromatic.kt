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

import guru.nidi.d3.D3.invoke
import guru.nidi.d3.D3.property
import jdk.nashorn.api.scripting.ScriptObjectMirror

object ScaleChromatic {
    object Scheme {
        @JvmStatic
        fun category10() = arrayProperty("schemeCategory10")

        @JvmStatic
        fun accent() = arrayProperty("schemeAccent")

        @JvmStatic
        fun dark2() = arrayProperty("schemeDark2")

        @JvmStatic
        fun paired() = arrayProperty("schemePaired")

        @JvmStatic
        fun pastel1() = arrayProperty("schemePastel1")

        @JvmStatic
        fun pastel2() = arrayProperty("schemePastel2")

        @JvmStatic
        fun set1() = arrayProperty("schemeSet1")

        @JvmStatic
        fun set2() = arrayProperty("schemeSet2")

        @JvmStatic
        fun set3() = arrayProperty("schemeSet3")

        private fun arrayProperty(name: String) = (property(name) as ScriptObjectMirror).to(Array<String>::class.java).map { Color(it) }
    }

    object Interpolate {
        @JvmStatic
        fun BrBG(value: ZeroToOne) = color("interpolateBrBG", value)

        @JvmStatic
        fun PRGn(value: ZeroToOne) = color("interpolatePRGn", value)

        @JvmStatic
        fun PiYG(value: ZeroToOne) = color("interpolatePiYG", value)

        @JvmStatic
        fun PuOr(value: ZeroToOne) = color("interpolatePuOr", value)

        @JvmStatic
        fun RdBu(value: ZeroToOne) = color("interpolateRdBu", value)

        @JvmStatic
        fun RdGy(value: ZeroToOne) = color("interpolateRdGy", value)

        @JvmStatic
        fun RdYlBu(value: ZeroToOne) = color("interpolateRdYlBu", value)

        @JvmStatic
        fun RdYlGn(value: ZeroToOne) = color("interpolateRdYlGn", value)

        @JvmStatic
        fun spectral(value: ZeroToOne) = color("interpolateSpectral", value)

        @JvmStatic
        fun blues(value: ZeroToOne) = color("interpolateBlues", value)

        @JvmStatic
        fun greens(value: ZeroToOne) = color("interpolateGreens", value)

        @JvmStatic
        fun greys(value: ZeroToOne) = color("interpolateGreys", value)

        @JvmStatic
        fun oranges(value: ZeroToOne) = color("interpolateOranges", value)

        @JvmStatic
        fun purples(value: ZeroToOne) = color("interpolatePurples", value)

        @JvmStatic
        fun reds(value: ZeroToOne) = color("interpolateReds", value)

        @JvmStatic
        fun viridis(value: ZeroToOne) = color("interpolateViridis", value)

        @JvmStatic
        fun inferno(value: ZeroToOne) = color("interpolateInferno", value)

        @JvmStatic
        fun magma(value: ZeroToOne) = color("interpolateMagma", value)

        @JvmStatic
        fun plasma(value: ZeroToOne) = color("interpolatePlasma", value)

        @JvmStatic
        fun warm(value: ZeroToOne) = color("interpolateWarm", value)

        @JvmStatic
        fun cool(value: ZeroToOne) = color("interpolateCool", value)

        @JvmStatic
        fun cubehelixDefault(value: ZeroToOne) = color("interpolateCubehelixDefault", value)

        @JvmStatic
        fun BuGn(value: ZeroToOne) = color("interpolateBuGn", value)

        @JvmStatic
        fun BuPu(value: ZeroToOne) = color("interpolateBuPu", value)

        @JvmStatic
        fun GnBu(value: ZeroToOne) = color("interpolateGnBu", value)

        @JvmStatic
        fun OrRd(value: ZeroToOne) = color("interpolateOrRd", value)

        @JvmStatic
        fun PuBuGn(value: ZeroToOne) = color("interpolatePuBuGn", value)

        @JvmStatic
        fun PuBu(value: ZeroToOne) = color("interpolatePuBu", value)

        @JvmStatic
        fun PuRd(value: ZeroToOne) = color("interpolatePuRd", value)

        @JvmStatic
        fun RdPu(value: ZeroToOne) = color("interpolateRdPu", value)

        @JvmStatic
        fun YlGnBu(value: ZeroToOne) = color("interpolateYlGnBu", value)

        @JvmStatic
        fun YlGn(value: ZeroToOne) = color("interpolateYlGn", value)

        @JvmStatic
        fun YlOrBr(value: ZeroToOne) = color("interpolateYlOrBr", value)

        @JvmStatic
        fun YlOrRd(value: ZeroToOne) = color("interpolateYlOrRd", value)

        @JvmStatic
        fun rainbow(value: ZeroToOne) = color("interpolateRainbow", value)

        @JvmStatic
        fun sinebow(value: ZeroToOne) = color("interpolateSinebow", value)

        private fun color(name: String, value: Double) = Color(invoke(name, value) as String)
    }
}