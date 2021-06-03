package com.levit.book_me.roundcloudsview.core.utills

internal object RoundCloudsViewConstants {

    /**
     * We need maximum possible view width, that's why
     * we request too much view width.
     *
     * Similarly for height.
     */
    const val DEFAULT_MIN_VIEW_WIDTH = 1500
    const val DEFAULT_MIN_VIEW_HEIGHT = 1500

    /**
     * Default cloud's size in pixel.
     */
    const val DEFAULT_LARGE_CLOUD_SIZE = 85
    const val DEFAULT_SMALL_CLOUD_SIZE = 65

    /**
     * The relative cloud size relatively RoundCloudsView
     */
    const val RELATIVELY_LARGE_CLOUD_SIZE = 3
    const val RELATIVELY_SMALL_CLOUD_SIZE = 4

    const val RELATIVELY_CLOUD_MARGIN = 15

    /**
     * The permissible touch offset between action down x/y touch
     * event to action up x/y touch event.
     */
    const val PERMISSIBLE_TOUCH_CLICK_OFFSET = 5

    /**
     * The available addition scroll offset. Needed to have some
     * extra space around the edges of RoundCloudsView.
     */
    const val ADDITIONAL_SCROLL_AVAILABLE_HORIZONTAL_DISTANCE = 50
}