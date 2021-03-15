package com.levit.book_me.core_network.model.converters

import com.levit.book_me.core_network.model.CacheModel
import com.levit.book_me.core_network.model.NetworkModel

@FunctionalInterface
interface NetworkToCacheModelConverter<N: NetworkModel, C: CacheModel> {
    fun convert(model: N): C
}
