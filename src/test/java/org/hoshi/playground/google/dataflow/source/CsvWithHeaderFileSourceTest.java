/**
 * The MIT License (MIT)
 *
 * Copyright (C) 2016 Luka Obradovic.
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.hoshi.playground.google.dataflow.source;

import com.google.cloud.dataflow.sdk.Pipeline;
import com.google.cloud.dataflow.sdk.io.Read;
import com.google.cloud.dataflow.sdk.testing.DataflowAssert;
import com.google.cloud.dataflow.sdk.testing.TestPipeline;
import com.google.cloud.dataflow.sdk.values.PCollection;
import org.testng.annotations.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class CsvWithHeaderFileSourceTest {
    @Test
    public void test_reading() throws Exception {
        final File file =
                new File(getClass().getResource("/sample.csv").toURI());
        assertThat(file.exists()).isTrue();

        final Pipeline pipeline = TestPipeline.create();

        final PCollection<String> output =
                pipeline.apply(Read.from(CsvWithHeaderFileSource.from(file.getAbsolutePath())));

        DataflowAssert
                .that(output)
                .containsInAnyOrder("a:1, b:2, c:3, ", "a:4, b:5, c:6, ");

        pipeline.run();
    }
}