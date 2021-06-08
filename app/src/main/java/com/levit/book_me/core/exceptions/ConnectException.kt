package com.levit.book_me.core.exceptions

import androidx.annotation.StringRes
import java.io.IOException

class ConnectException(@StringRes val stringId: Int): IOException()