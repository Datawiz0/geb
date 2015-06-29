/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package geb.module

import geb.error.InvalidModuleBaseException
import geb.test.GebSpecWithCallbackServer

class TextInputBaseSpec extends GebSpecWithCallbackServer {

    def "can base a text input on an empty navigator"() {
        given:
        html {
        }

        when:
        $("input").module(TextInput)

        then:
        noExceptionThrown()
    }

    def "basing a text input on anything other than an input throws an exception"() {
        given:
        html {
            div()
        }

        when:
        $("div").module(TextInput)

        then:
        InvalidModuleBaseException e = thrown()
        e.message == "Specified base element for ${TextInput.name} module was 'div' but only input is allowed as the base element."
    }

    def "creating the module for an input of type that is not text results in an exception"() {
        given:
        html {
            input(type: "checkbox")
        }

        when:
        $("input").module(TextInput)

        then:
        InvalidModuleBaseException e = thrown()
        e.message == "Specified base element for ${TextInput.name} module was an input of type 'checkbox' but only input of type text is allowed as the base element."
    }

    def "can create the module based on a text input"() {
        given:
        html {
            input()
        }

        when:
        $("input").module(TextInput)

        then:
        noExceptionThrown()
    }
}
