/**
 * Copyright 2012 Hanborq Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rockstor.compact.mapreduce;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Mapper;

import com.rockstor.compact.Compactor;

public class CompactDataMapper extends
        Mapper<String, NullWritable, NullWritable, NullWritable> {

    /**
     * Called once for each key/value pair in the input split. Most applications
     * should override this, but the default is the identity function.
     */
    @Override
    protected void map(String key, NullWritable value, Context context)
            throws IOException, InterruptedException {
        try {
            Compactor.getInstance().compactData(key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }

    /**
     * Called once at the end of the task.
     */
    @Override
    protected void cleanup(Context context) throws IOException,
            InterruptedException {
        // NOTHING
    }
}
