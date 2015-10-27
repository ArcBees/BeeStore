/*
 * Copyright 2015 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.beeshop.common.dto;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {
    @Test
    public void previousTests() {
        List<TestCase> cases = Lists.newArrayList(
                new TestCase(Product.SHIRT, Product.MUG),
                new TestCase(Product.BAG, Product.SHIRT),
                new TestCase(Product.THERMOS, Product.BAG),
                new TestCase(Product.PHONE_CASE, Product.THERMOS),
                new TestCase(Product.USB_KEY, Product.PHONE_CASE),
                new TestCase(Product.MUG, Product.USB_KEY)
        );

        for (TestCase testCase : cases) {
            assertThat(testCase.input.getPreviousProduct()).isEqualTo(testCase.expected);
        }
    }

    @Test
    public void nextTests() {
        List<TestCase> cases = Lists.newArrayList(
                new TestCase(Product.SHIRT, Product.BAG),
                new TestCase(Product.BAG, Product.THERMOS),
                new TestCase(Product.THERMOS, Product.PHONE_CASE),
                new TestCase(Product.PHONE_CASE, Product.USB_KEY),
                new TestCase(Product.USB_KEY, Product.MUG),
                new TestCase(Product.MUG, Product.SHIRT)
        );

        for (TestCase testCase : cases) {
            assertThat(testCase.input.getNextProduct()).isEqualTo(testCase.expected);
        }
    }

    private class TestCase {
        public final Product input;
        public final Product expected;

        public TestCase(Product input, Product expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
