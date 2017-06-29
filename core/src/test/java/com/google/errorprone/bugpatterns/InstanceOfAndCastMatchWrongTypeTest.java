/* Copyright 2016 Google Inc. All Rights Reserved.
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

package com.google.errorprone.bugpatterns;

import com.google.errorprone.CompilationTestHelper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author sulku@google.com (Marsela Sulku)
 * @author mariasam@google.com (Maria Sam)
 */
@RunWith(JUnit4.class)
public class InstanceOfAndCastMatchWrongTypeTest {
  private CompilationTestHelper compilationHelper;

  @Before
  public void setUp() {
    compilationHelper =
        CompilationTestHelper.newInstance(InstanceOfAndCastMatchWrongType.class, getClass());
  }

  @Test
  public void testPositiveCase() throws Exception {
    compilationHelper.addSourceFile("InstanceOfAndCastMatchWrongTypePositiveCases.java").doTest();
  }

  @Test
  public void testNegativeCase() throws Exception {
    compilationHelper.addSourceFile("InstanceOfAndCastMatchWrongTypeNegativeCases.java").doTest();
  }

  @Ignore("regression for issue 651, not yet fixed")
  @Test
  public void regressionTestIssue651() throws Exception {
    compilationHelper
        .addSourceLines(
            "Foo.java",
            "class Foo {",
            "  void foo() {",
            "    Object[] values = null;",
            "    if (values[0] instanceof Integer) {",
            "      int x = (Integer) values[0];",
            "    } else if (values[0] instanceof Long) {",
            "      long y = (Long) values[0];",
            "    }",
            "  }",
            "}")
        .doTest();
  }
}
