// Copyright 2020 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.pure.runtime.java.compiled.generation.processors.natives.essentials.date.operation;

import org.finos.legend.pure.m4.coreinstance.primitive.date.PureDate;
import org.finos.legend.pure.runtime.java.compiled.generation.processors.natives.AbstractNativeFunctionGeneric;

public class AdjustDate extends AbstractNativeFunctionGeneric
{
    public AdjustDate()
    {
        super("CoreGen.adjustDate", new Class[]{PureDate.class, Long.class, org.finos.legend.pure.m3.coreinstance.meta.pure.metamodel.type.Enum.class}, "adjust_Date_1__Integer_1__DurationUnit_1__Date_1_");
    }
}