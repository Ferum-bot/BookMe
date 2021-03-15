package com.levit.book_me.core_network.model.converters

import com.levit.book_me.core_network.model.CacheModel
import com.levit.book_me.core_network.model.NetworkModel

@FunctionalInterface
interface CacheToNetworkModelConverter<C: CacheModel, N: NetworkModel> {
    fun convert(model: C): N
}
