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

import guru.nidi.d3.D3.invocable
import guru.nidi.d3.D3.invokeAsPeer

typealias ZeroToOne = Double
typealias ZeroToTwo = Double
typealias ZeroTo255 = Int
typealias ZeroTo150 = Int
typealias ZeroTo360 = Int
typealias PlusMinus160 = Int

class Color private constructor(private val c: Peer) {
    constructor(specifier: String) : this(invokeAsPeer("color", specifier))

    fun opacity() = c["opacity"].let { (it as? Int)?.toDouble() ?: it as Double }
    fun rgb() = invPeer("rgb").let { intArrayOf(it["r"] as Int, it["g"] as Int, it["b"] as Int) }
    fun hsl() = with(if (c["s"] == null) hsl(this) else this) { doubleArrayOf(c["h"] as Double, c["s"] as Double, c["l"] as Double) }
    fun lab() = with(if (c["a"] == null) lab(this) else this) { doubleArrayOf(c["l"] as Double, c["a"] as Double, c["b"] as Double) }
    fun hcl() = with(if (c["c"] == null) hcl(this) else this) { doubleArrayOf(c["h"] as Double, c["c"] as Double, c["l"] as Double) }
    fun rgbInt() = rgb().let { 0x10000 * it[0] + 0x100 * it[1] + it[2] }

    fun brighter(k: Double = 1.0) = Color(invPeer("brighter", k))
    fun darker(k: Double = 1.0) = Color(invPeer("darker", k))
    fun displayable() = inv("displayable") as Boolean
    fun hex() = inv("hex") as String
    override fun toString() = inv("toString") as String

    private fun <T> inv(name: String, vararg args: Any) = invocable.invokeMethod(c, name, *args) as T
    private fun invPeer(name: String, vararg args: Any): Peer = inv(name, *args)


    companion object {
        @JvmOverloads
        @JvmStatic
        fun rgb(r: ZeroTo255, g: ZeroTo255, b: ZeroTo255, opacity: Double = 1.0) = Color(invokeAsPeer("rgb", r, g, b, opacity))

        @JvmStatic
        fun rgb(specifier: String) = Color(invokeAsPeer("rgb", specifier))

        @JvmStatic
        fun rgb(color: Color) = Color(invokeAsPeer("rgb", color.c))

        @JvmOverloads
        @JvmStatic
        fun hsl(h: ZeroTo360, s: ZeroToOne, l: ZeroToOne, opacity: Double = 1.0) = Color(invokeAsPeer("hsl", h, s, l, opacity))

        @JvmStatic
        fun hsl(specifier: String) = Color(invokeAsPeer("hsl", specifier))

        @JvmStatic
        fun hsl(color: Color) = Color(invokeAsPeer("hsl", color.c))

        @JvmOverloads
        @JvmStatic
        fun lab(l: ZeroTo150, a: PlusMinus160, b: PlusMinus160, opacity: Double = 1.0) = Color(invokeAsPeer("lab", l, a, b, opacity))

        @JvmOverloads
        @JvmStatic
        fun gray(l: Int, opacity: Double = 1.0) = Color(invokeAsPeer("gray", l, opacity))

        @JvmStatic
        fun lab(specifier: String) = Color(invokeAsPeer("lab", specifier))

        @JvmStatic
        fun lab(color: Color) = Color(invokeAsPeer("lab", color.c))

        @JvmOverloads
        @JvmStatic
        fun hcl(h: ZeroTo360, c: ZeroTo255, l: ZeroTo150, opacity: Double = 1.0) = Color(invokeAsPeer("hcl", h, c, l, opacity))

        @JvmStatic
        fun hcl(specifier: String) = Color(invokeAsPeer("hcl", specifier))

        @JvmStatic
        fun hcl(color: Color) = Color(invokeAsPeer("hcl", color.c))

        @JvmOverloads
        @JvmStatic
        fun cubehelix(h: ZeroTo360, s: ZeroToTwo, l: ZeroToOne, opacity: Double = 1.0) = Color(invokeAsPeer("cubehelix", h, s, l, opacity))

        @JvmStatic
        fun cubehelix(specifier: String) = Color(invokeAsPeer("cubehelix", specifier))

        @JvmStatic
        fun cubehelix(color: Color) = Color(invokeAsPeer("cubehelix", color.c))

//        private fun hex(i: Int) = i.and(0xff).toString(16).let { if (it.length == 1) "0$it" else it }
    }
}

//data class Color(private val rgb: Int) {
//    fun rgbInt() = rgb
//    fun rgbString() = hex(rgb.shr(16)) + hex(rgb.shr(8)) + hex(rgb)
//
//    companion object {
//        private val D3_PATTERN = Regex("""rgb\((\d+), (\d+), (\d+)\)""")
//
//        fun ofD3(rgb: String) = D3_PATTERN.matchEntire(rgb)
//                ?.let {
//                    Color(0x10000 * it.groupValues[1].toInt() + 0x100 * it.groupValues[2].toInt() + it.groupValues[3].toInt())
//                }
//                ?: throw IllegalArgumentException("Unexpected input $rgb")
//
//        private fun hex(i: Int) = i.and(0xff).toString(16).let { if (it.length == 1) "0$it" else it }
//    }
//}
//