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

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ColorsTest {
    @Test
    fun simple() {
        assertEquals("#4682b4", Color("steelblue").hex())
        val rgb = Color("#123456")
        assertArrayEquals(intArrayOf(18, 52, 86), rgb.rgb())
        assertEquals(0x123456, rgb.rgbInt())
        assertEquals(1.0, rgb.opacity())
        assertEquals("#123456", rgb.hex())
        assertEquals(true, rgb.displayable())
        assertEquals("#0d243c", rgb.darker().hex())
        assertEquals("#1a4a7b", rgb.brighter().hex())
        assertEquals("#010203", Color.rgb(1, 2, 3).hex())
        assertEquals("#5c3e3d", Color.hsl(1, .2, .3).hex())
        assertArrayEquals(doubleArrayOf(207.27, .44, .49), Color.hsl("steelblue").hsl(), .01)
        assertEquals("#d83b00", Color.lab(50, 60, 80).hex())
        assertArrayEquals(doubleArrayOf(51.99, -8.36, -32.83), Color.lab("steelblue").lab(), .01)
        assertEquals("#ffa872", Color.hcl(50, 60, 80).hex())
        assertArrayEquals(doubleArrayOf(255.71, 33.88, 51.99), Color.hcl("steelblue").hcl(), .01)
    }

    @Test
    fun scheme() {
        val scheme = ScaleChromatic.Scheme
        assertEquals(10, scheme.category10().size)
        assertEquals(8, scheme.accent().size)
        assertEquals(8, scheme.dark2().size)
        assertEquals(12, scheme.paired().size)
        assertEquals(9, scheme.pastel1().size)
        assertEquals(8, scheme.pastel2().size)
        assertEquals(9, scheme.set1().size)
        assertEquals(8, scheme.set2().size)
        assertEquals(12, scheme.set3().size)
    }

    @Test
    fun interpolate() {
        val interpolate = ScaleChromatic.Interpolate
        assertEquals("#543005", interpolate.BrBG(0.0).hex())
        assertEquals("#40004b", interpolate.PRGn(0.0).hex())
        assertEquals("#8e0152", interpolate.PiYG(0.0).hex())
        assertEquals("#2d004b", interpolate.PuOr(0.0).hex())
        assertEquals("#67001f", interpolate.RdBu(0.0).hex())
        assertEquals("#67001f", interpolate.RdGy(0.0).hex())
        assertEquals("#a50026", interpolate.RdYlBu(0.0).hex())
        assertEquals("#a50026", interpolate.RdYlGn(0.0).hex())
        assertEquals("#9e0142", interpolate.spectral(0.0).hex())
        assertEquals("#f7fbff", interpolate.blues(0.0).hex())
        assertEquals("#f7fcf5", interpolate.greens(0.0).hex())
        assertEquals("#ffffff", interpolate.greys(0.0).hex())
        assertEquals("#fff5eb", interpolate.oranges(0.0).hex())
        assertEquals("#fcfbfd", interpolate.purples(0.0).hex())
        assertEquals("#fff5f0", interpolate.reds(0.0).hex())
        assertEquals("#440154", interpolate.viridis(0.0).hex())
        assertEquals("#000004", interpolate.inferno(0.0).hex())
        assertEquals("#000004", interpolate.magma(0.0).hex())
        assertEquals("#0d0887", interpolate.plasma(0.0).hex())
        assertEquals("#6e40aa", interpolate.warm(0.0).hex())
        assertEquals("#6e40aa", interpolate.cool(0.0).hex())
        assertEquals("#000000", interpolate.cubehelixDefault(0.0).hex())
        assertEquals("#f7fcfd", interpolate.BuGn(0.0).hex())
        assertEquals("#f7fcfd", interpolate.BuPu(0.0).hex())
        assertEquals("#f7fcf0", interpolate.GnBu(0.0).hex())
        assertEquals("#fff7ec", interpolate.OrRd(0.0).hex())
        assertEquals("#fff7fb", interpolate.PuBuGn(0.0).hex())
        assertEquals("#fff7fb", interpolate.PuBu(0.0).hex())
        assertEquals("#f7f4f9", interpolate.PuRd(0.0).hex())
        assertEquals("#fff7f3", interpolate.RdPu(0.0).hex())
        assertEquals("#ffffd9", interpolate.YlGnBu(0.0).hex())
        assertEquals("#ffffe5", interpolate.YlGn(0.0).hex())
        assertEquals("#ffffe5", interpolate.YlOrBr(0.0).hex())
        assertEquals("#ffffcc", interpolate.YlOrRd(0.0).hex())
        assertEquals("#6e40aa", interpolate.rainbow(0.0).hex())
        assertEquals("#ff4040", interpolate.sinebow(0.0).hex())
    }
}