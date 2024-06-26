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

package org.finos.legend.pure.m3.coreinstance.helper;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.ImmutableSet;
import org.finos.legend.pure.m3.coreinstance.meta.pure.metamodel._import.EnumStub;
import org.finos.legend.pure.m3.coreinstance.meta.pure.metamodel._import.ImportStub;
import org.finos.legend.pure.m3.coreinstance.meta.pure.metamodel._import.PropertyStub;
import org.finos.legend.pure.m3.coreinstance.meta.pure.tools.GrammarInfoStub;
import org.finos.legend.pure.m3.navigation.M3Paths;
import org.finos.legend.pure.m3.navigation.ProcessorSupport;
import org.finos.legend.pure.m4.coreinstance.CoreInstance;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * Any Stub Helper
 */
public class AnyStubHelper
{
    public static final ImmutableSet<String> STUB_CLASSES = Sets.immutable.with(M3Paths.EnumStub, M3Paths.GrammarInfoStub, M3Paths.ImportStub, M3Paths.PropertyStub);
    public static final Function<CoreInstance, CoreInstance> FROM_STUB_FN = AnyStubHelper::fromStub;

    private AnyStubHelper()
    {
    }

    public static CoreInstance fromStub(CoreInstance instance)
    {
        if (instance instanceof ImportStub)
        {
            return ImportStubHelper.fromImportStub((ImportStub) instance);
        }
        if (instance instanceof PropertyStub)
        {
            return PropertyStubHelper.fromPropertyStub((PropertyStub) instance);
        }
        if (instance instanceof EnumStub)
        {
            return EnumStubHelper.fromEnumStub((EnumStub) instance);
        }
        if (instance instanceof GrammarInfoStub)
        {
            return GrammarInfoStubHelper.fromGrammarInfoStub((GrammarInfoStub) instance);
        }
        return instance;
    }

    public static boolean isUnresolvedStub(CoreInstance instance)
    {
        if (instance instanceof ImportStub)
        {
            return ImportStubHelper.isUnresolved((ImportStub) instance);
        }
        if (instance instanceof PropertyStub)
        {
            return PropertyStubHelper.isUnresolved((PropertyStub) instance);
        }
        if (instance instanceof EnumStub)
        {
            return EnumStubHelper.isUnresolved((EnumStub) instance);
        }
        if (instance instanceof GrammarInfoStub)
        {
            return GrammarInfoStubHelper.isUnresolved((GrammarInfoStub) instance);
        }
        return false;
    }

    public static boolean isStub(CoreInstance instance)
    {
        return (instance instanceof ImportStub) ||
                (instance instanceof PropertyStub) ||
                (instance instanceof EnumStub) ||
                (instance instanceof GrammarInfoStub);
    }

    public static ImmutableSet<String> getStubClassPaths()
    {
        return STUB_CLASSES;
    }

    @Deprecated
    public static ImmutableSet<String> getStubClasses()
    {
        return getStubClassPaths();
    }

    public static MutableList<CoreInstance> getStubClasses(ProcessorSupport processorSupport)
    {
        return getStubClasses(processorSupport, false);
    }

    public static MutableList<CoreInstance> getStubClasses(ProcessorSupport processorSupport, boolean errorIfNotFound)
    {
        return getStubClasses(processorSupport, Lists.mutable.ofInitialCapacity(STUB_CLASSES.size()), errorIfNotFound);
    }

    public static <T extends Collection<? super CoreInstance>> T getStubClasses(ProcessorSupport processorSupport, T targetCollection)
    {
        return getStubClasses(processorSupport, targetCollection, false);
    }

    public static <T extends Collection<? super CoreInstance>> T getStubClasses(ProcessorSupport processorSupport, T targetCollection, boolean errorIfNotFound)
    {
        forEachStubClass(processorSupport, targetCollection::add, errorIfNotFound);
        return targetCollection;
    }

    public static void forEachStubClass(ProcessorSupport processorSupport, Consumer<? super CoreInstance> consumer)
    {
        forEachStubClass(processorSupport, consumer, false);
    }

    public static void forEachStubClass(ProcessorSupport processorSupport, Consumer<? super CoreInstance> consumer, boolean errorIfNotFound)
    {
        STUB_CLASSES.forEach(path ->
        {
            CoreInstance stubClass = processorSupport.package_getByUserPath(path);
            if (stubClass != null)
            {
                consumer.accept(stubClass);
            }
            else if (errorIfNotFound)
            {
                throw new RuntimeException("Could not find: " + path);
            }
        });
    }
}
