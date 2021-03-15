package com.levit.book_me.core_network.caching

enum class CachePolicies {
    /**
     * Use cache first, if not available, use network
     */
    CACHE_OR_NETWORK,
    /**
     * Use network first, if not available, use cache
     */
    NETWORK_OR_CACHE,
    /**
     * Always use network, cache values will be ignored
     */
    ALWAYS_NETWORK,
    ALWAYS_CACHE
}
