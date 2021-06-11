package com.levit.book_me.data_sources.profile.impl;

import com.google.gson.reflect.TypeToken;
import kotlin.jvm.JvmDefaultWithoutCompatibility;
import kotlin.jvm.JvmField;

import java.lang.reflect.Type;

@JvmDefaultWithoutCompatibility
public class TypeTokenAdapter<T> extends TypeToken<T> {

    public TypeTokenAdapter() {
        super();
    }

    @JvmField
    public Type adaptedType = getType();
}
