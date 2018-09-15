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

import javax.script.Invocable
import javax.script.ScriptEngineManager

internal object D3 {
    private val engine = ScriptEngineManager().getEngineByExtension("js").apply {
        eval(Thread.currentThread().contextClassLoader.getResourceAsStream("d3.v5.min.js")
                .bufferedReader().use { it.readText() })
    }
    private val d3 = engine.eval("d3")
    internal val invocable = engine as Invocable
    internal fun <T> invoke(name: String, vararg args: Any): T = invocable.invokeMethod(d3, name, *args) as T
    internal fun invokeAsPeer(name: String, vararg args: Any): Peer = invoke(name, *args)
    internal fun <T> property(name: String): T = (d3 as Map<String, *>)[name] as T
}

typealias Peer = Map<String, *>
