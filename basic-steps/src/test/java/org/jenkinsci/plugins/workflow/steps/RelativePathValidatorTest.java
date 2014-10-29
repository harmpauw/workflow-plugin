/*
 * The MIT License
 *
 * Copyright 2014 Jesse Glick.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.jenkinsci.plugins.workflow.steps;

import org.junit.Test;
import static org.junit.Assert.*;

public class RelativePathValidatorTest {

    @Test public void ok() {
        assertEquals("foo", RelativePathValidator.validate("foo"));
        assertEquals("foo/bar", RelativePathValidator.validate("foo/bar"));
    }

    @Test(expected=IllegalArgumentException.class) public void absolute() {
        RelativePathValidator.validate("/etc/shadow");
    }

    @Test(expected=IllegalArgumentException.class) public void windoze() {
        RelativePathValidator.validate("foo\\bar");
        RelativePathValidator.validate("C:\\Windows\\system32\\stuff.dll");
    }

    @Test(expected=IllegalArgumentException.class) public void sneaky1() {
        RelativePathValidator.validate("foo/../../bar");
    }

    @Test(expected=IllegalArgumentException.class) public void sneaky2() {
        RelativePathValidator.validate("../foo");
    }

    @Test(expected=IllegalArgumentException.class) public void sneaky3() {
        RelativePathValidator.validate("..");
    }

    @Test(expected=IllegalArgumentException.class) public void sneaky4() {
        RelativePathValidator.validate("../foo");
    }

}