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
package guru.nidi.d3;

import guru.nidi.d3.ScaleChromatic.Interpolate;
import guru.nidi.d3.ScaleChromatic.Scheme;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaColorsTest {
    @Test
    void simple() {
        assertEquals("#4682b4", new Color("steelblue").hex());
        Color rgb = new Color("#123456");
        assertArrayEquals(new int[]{18, 52, 86}, rgb.rgb());
        assertEquals(0x123456, rgb.rgbInt());
        assertEquals(1.0, rgb.opacity());
        assertEquals("#123456", rgb.hex());
        assertTrue(rgb.displayable());
        assertEquals("#0d243c", rgb.darker(1).hex());
        assertEquals("#1a4a7b", rgb.brighter(1).hex());
        assertEquals("#010203", Color.rgb(1, 2, 3).hex());
        assertEquals("#5c3e3d", Color.hsl(1, .2, .3).hex());
        assertArrayEquals(new double[]{207.27, .44, .49}, Color.hsl("steelblue").hsl(), .01);
        assertEquals("#d83b00", Color.lab(50, 60, 80).hex());
        assertArrayEquals(new double[]{51.99, -8.36, -32.83}, Color.lab("steelblue").lab(), .01);
        assertEquals("#ffa872", Color.hcl(50, 60, 80).hex());
        assertArrayEquals(new double[]{255.71, 33.88, 51.99}, Color.hcl("steelblue").hcl(), .01);
    }

    @Test
    void scheme() {
        assertEquals(10, Scheme.category10().size());
    }

    @Test
    void interpolate() {
        assertEquals("#543005", Interpolate.BrBG(0.0).hex());
    }
}
