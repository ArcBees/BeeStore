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

package com.arcbees.beestore.common.dto;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTypeTest {
    @Test
    public void previousTests() {
        List<TestCase> cases = Lists.newArrayList(
                new TestCase(ProductType.SHIRT, ProductType.MUG),
                new TestCase(ProductType.BAG, ProductType.SHIRT),
                new TestCase(ProductType.THERMOS, ProductType.BAG),
                new TestCase(ProductType.PHONE_CASE, ProductType.THERMOS),
                new TestCase(ProductType.USB_KEY, ProductType.PHONE_CASE),
                new TestCase(ProductType.MUG, ProductType.USB_KEY)
        );

        for (TestCase testCase : cases) {
            assertThat(testCase.input.getPreviousProduct()).isEqualTo(testCase.expected);
        }
    }

    @Test
    public void nextTests() {
        List<TestCase> cases = Lists.newArrayList(
                new TestCase(ProductType.SHIRT, ProductType.BAG),
                new TestCase(ProductType.BAG, ProductType.THERMOS),
                new TestCase(ProductType.THERMOS, ProductType.PHONE_CASE),
                new TestCase(ProductType.PHONE_CASE, ProductType.USB_KEY),
                new TestCase(ProductType.USB_KEY, ProductType.MUG),
                new TestCase(ProductType.MUG, ProductType.SHIRT)
        );

        for (TestCase testCase : cases) {
            assertThat(testCase.input.getNextProduct()).isEqualTo(testCase.expected);
        }
    }

    private class TestCase {
        public final ProductType input;
        public final ProductType expected;

        public TestCase(ProductType input, ProductType expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
