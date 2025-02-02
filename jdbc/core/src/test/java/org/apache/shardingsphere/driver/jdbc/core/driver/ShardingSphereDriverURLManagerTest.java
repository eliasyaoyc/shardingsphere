/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.driver.jdbc.core.driver;

import org.apache.shardingsphere.driver.jdbc.exception.syntax.DriverURLProviderNotFoundException;
import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public final class ShardingSphereDriverURLManagerTest {
    
    private final int fooDriverConfigLength = 999;
    
    @Test(expected = DriverURLProviderNotFoundException.class)
    public void assertNewConstructorWithEmptyURL() {
        ShardingSphereDriverURLManager.getContent("jdbc:shardingsphere:");
    }
    
    @Test
    public void assertToClasspathConfigurationFile() {
        byte[] actual = ShardingSphereDriverURLManager.getContent("jdbc:shardingsphere:classpath:config/driver/foo-driver-fixture.yaml");
        assertThat(actual.length, is(fooDriverConfigLength));
    }
    
    @Test
    public void assertToAbsolutePathConfigurationFile() {
        String absolutePath = Objects.requireNonNull(ShardingSphereDriverURLManagerTest.class.getClassLoader().getResource("config/driver/foo-driver-fixture.yaml")).getPath();
        byte[] actual = ShardingSphereDriverURLManager.getContent("jdbc:shardingsphere:absolutepath:" + absolutePath);
        assertThat(actual.length, is(fooDriverConfigLength));
    }
}
