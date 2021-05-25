package com.levit.book_me.roundcloudsview.core.enums

enum class RoundCloudSize {

    /**
     * Actual size of cloud would be 1/3 of available height
     * of view. For example:
     * Height of RoundCloudsView is 100dp. Then size of
     * @LARGE cloud would be 33dp to 33dp.
     *
     * @Important:
     * Under available height means view height with
     * padding. For example:
     * View height is 110dp and paddingTop = 10dp, paddingBottom = 10dp.
     * Then available height is 110dp - 10dp - 10dp = 90dp* @Important: Under height means view height with
     * padding. For example:
     * View height is 110dp and paddingTop = 10dp, paddingBottom = 10dp.
     * Then available height is 110dp - 10dp - 10dp = 90dp
     */
    LARGE,

    /**
     * Actual size of cloud would be 1/4 of available height
     * if view. For example:
     * Height of RoundCloudsView is 100dp. Then size of
     * @SMALL cloud would be 25dp to 25dp.
     *
     * @Important:
     * Under available height means view height with
     * padding. For example:
     * View height is 110dp and paddingTop = 10dp, paddingBottom = 10dp.
     * Then available height is 110dp - 10dp - 10dp = 90dp
     */
    SMALL,
}