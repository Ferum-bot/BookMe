package com.levit.book_me.roundcloudsview.ui

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import com.levit.book_me.roundcloudsview.R
import com.levit.book_me.roundcloudsview.core.enums.RoundCloudState
import com.levit.book_me.roundcloudsview.core.extensions.dpToPx
import com.levit.book_me.roundcloudsview.core.extensions.getLeftPadding
import com.levit.book_me.roundcloudsview.core.extensions.getRightPadding
import com.levit.book_me.roundcloudsview.core.interfaces.RoundCloudStateChangeListener
import com.levit.book_me.roundcloudsview.core.models.CloudModelSizeHolder
import com.levit.book_me.roundcloudsview.core.models.RoundCloud
import com.levit.book_me.roundcloudsview.core.models.RoundCloudModel
import com.levit.book_me.roundcloudsview.core.utills.RoundCloudsViewAttrs
import com.levit.book_me.roundcloudsview.core.utills.RoundCloudsViewConstants
import com.levit.book_me.roundcloudsview.entity.CloudCoordinateCalculator
import com.levit.book_me.roundcloudsview.entity.impl.ColumnsCloudCoordinateCalculator
import timber.log.Timber
import kotlin.math.abs
import kotlin.math.max

class RoundCloudsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): View(context, attrs, defStyleAttr) {

    @ColorInt
    private var checkedCloudColor: Int = Color.BLACK
    @ColorInt
    private var notCheckedCloudColor: Int = Color.GRAY

    @ColorInt
    private var checkedTextColor: Int = Color.WHITE
    @ColorInt
    private var notCheckedTextColor: Int = Color.BLACK

    @Dimension
    private var cloudMarginDp: Int = 8

    private var viewCenterPointPx = PointF(0f, 0f)

    private var currentXOffsetPx = 0

    private val checkedCloudPaint = Paint()
    private val notCheckedCloudPaint = Paint()

    private val checkedTextPaint = TextPaint()
    private val notCheckedTextPaint = TextPaint()

    private var clouds: List<RoundCloud> = listOf()
    private var cloudModels: List<RoundCloudModel> = listOf()

    private var cloudListener: RoundCloudStateChangeListener? = null

    private var startTouchPointPx: PointF? = null
    private var endTouchPointPx: PointF? = null

    private val coordinateCalculator: CloudCoordinateCalculator by lazy {
        //AndroidCloudCoordinateCalculator(this::getContext, this::dpToPx, this::pxToDp)
        //MockCloudCoordinateCalculator()
        ColumnsCloudCoordinateCalculator()
    }

    private var cloudSizeHolder = CloudModelSizeHolder()

    init {
        initPropertiesWithAttrs(attrs)
        configurePaints()
    }

    fun setCheckedCloudColor(@ColorInt rgbColor: Int) {
        checkedCloudColor = rgbColor
        configureCloudPaints()
        invalidate()
    }

    fun setNotCheckedCloudColor(@ColorInt rgbColor: Int) {
        notCheckedCloudColor = rgbColor
        configureCloudPaints()
        invalidate()
    }

    fun setCheckedTextColor(@ColorInt rgbColor: Int) {
        checkedTextColor = rgbColor
        configureTextPaints()
        invalidate()
    }

    fun setNotCheckedTextColor(@ColorInt rgbColor: Int) {
        notCheckedTextColor = rgbColor
        configureTextPaints()
        invalidate()
    }

    fun setClouds(clouds: List<RoundCloud>) {
        this.clouds = clouds.toList()

        cloudModels = coordinateCalculator.calculateCloudModels(
            clouds = this.clouds,
            sizeHolder = cloudSizeHolder,
        )
        invalidate()
    }

    fun setCloudStateChangeListener(listener: RoundCloudStateChangeListener?) {
        cloudListener = listener
    }

    fun setCloudMargin(@Dimension dp: Int) {
        cloudMarginDp = dp
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val neededWidth = calculateNeededWidth()
        val neededHeight = calculateNeededHeight()

        val actualWidth = resolveSize(neededWidth, widthMeasureSpec)
        val actualHeight = resolveSize(neededHeight, heightMeasureSpec)

        setMeasuredDimension(actualWidth, actualHeight)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        viewCenterPointPx = PointF(
            w.toFloat() / 2f,
            h.toFloat() / 2f,
        )
        cloudSizeHolder = CloudModelSizeHolder.getFromViewSize(
            viewHeightPx = h,
            viewWidthPx = w,
            cloudMarginPx = dpToPx(cloudMarginDp),
        )
        cloudModels = coordinateCalculator.calculateCloudModels(
            clouds = clouds,
            sizeHolder = cloudSizeHolder,
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        cloudModels.forEach { cloudModel ->
            drawCloud(canvas, cloudModel)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false
        performClick()

        when(event.action) {
            MotionEvent.ACTION_DOWN -> handleActionDownEvent(event)
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> handleActionUpEvent(event)
            MotionEvent.ACTION_MOVE -> handleActionMoveEvent(event)
        }

        return true
    }

    override fun performClick(): Boolean {
        super.performClick()
        return false
    }

    private fun handleActionDownEvent(event: MotionEvent) {
        val xCoordinate = event.x
        val yCoordinate = event.y

        startTouchPointPx = PointF(xCoordinate, yCoordinate)
    }

    private fun handleActionUpEvent(event: MotionEvent) {
        val startPoint = startTouchPointPx ?: return
        val endPoint = PointF(event.x, event.y)

        if (isClickEvent(startPoint, endPoint)) {
            val xClickCoordinate = abs(startPoint.x + endPoint.x) / 2
            val yClickCoordinate = abs(startPoint.y + endPoint.y) / 2
            val clickPoint = PointF(xClickCoordinate, yClickCoordinate)
            handleClickEvent(clickPoint)
        }
    }

    private fun handleActionMoveEvent(event: MotionEvent) {

    }

    private fun isClickEvent(startPoint: PointF, endPoint: PointF): Boolean {
        val startX = startPoint.x.toInt()
        val startY = startPoint.y.toInt()
        val endX = endPoint.x.toInt()
        val endY = endPoint.y.toInt()

        val xDiff = abs(startX - endX)
        val yDiff = abs(startY - endY)
        if (max(xDiff, yDiff) > RoundCloudsViewConstants.PERMISSIBLE_TOUCH_CLICK_OFFSET) {
            return false
        }
        return true
    }

    private fun handleClickEvent(clickPoint: PointF) {
        val clickedCloud = getClickedCloud(clickPoint) ?: return
        when(clickedCloud.state) {
            RoundCloudState.CHECKED -> {
                clickedCloud.state = RoundCloudState.NOT_CHECKED
                cloudListener?.onStateChanged(
                    RoundCloudState.NOT_CHECKED, clickedCloud.cloud
                )
            }
            RoundCloudState.NOT_CHECKED -> {
                clickedCloud.state = RoundCloudState.CHECKED
                cloudListener?.onStateChanged(
                    RoundCloudState.CHECKED, clickedCloud.cloud
                )
            }
        }
        invalidate()
    }

    private fun getClickedCloud(clickPoint: PointF): RoundCloudModel? {
        var resultCloud: RoundCloudModel? = null

        cloudModels.forEach { cloudModel ->
            if (cloudModel.containsPoint(clickPoint, viewCenterPointPx, currentXOffsetPx)) {
                resultCloud = cloudModel
                return@forEach
            }
        }

        return resultCloud
    }

    private fun initPropertiesWithAttrs(attrs: AttributeSet?) {
        attrs ?: return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundCloudsView)

        with(typedArray) {
            checkedCloudColor = getColor(
                RoundCloudsViewAttrs.CHECKED_CLOUD_COLOR,
                Color.BLACK,
            )
            notCheckedCloudColor = getColor(
                RoundCloudsViewAttrs.NOT_CHECKED_CLOUD_COLOR,
                Color.GRAY,
            )

            checkedTextColor = getColor(
                RoundCloudsViewAttrs.CHECKED_TEXT_COLOR,
                Color.WHITE,
            )
            notCheckedTextColor = getColor(
                RoundCloudsViewAttrs.NOT_CHECKED_TEXT_COLOR,
                Color.BLACK
            )
        }

        typedArray.recycle()
    }

    private fun configurePaints() {
        configureTextPaints()
        configureCloudPaints()
    }

    private fun configureTextPaints() {
        with(checkedTextPaint) {
            color = checkedTextColor
            isAntiAlias = true

        }

        with(notCheckedTextPaint) {
            color = notCheckedTextColor
            isAntiAlias = true
        }
    }

    private fun configureCloudPaints() {
        with(checkedCloudPaint) {
            color = checkedCloudColor
            style = Paint.Style.FILL
            isAntiAlias = true
        }

        with(notCheckedCloudPaint) {
            color = notCheckedCloudColor
            style = Paint.Style.FILL
            isAntiAlias = true
        }
    }

    private fun calculateNeededWidth(): Int {
        val leftPadding = getLeftPadding()
        val rightPadding = getRightPadding()

        return leftPadding + RoundCloudsViewConstants.DEFAULT_MIN_VIEW_WIDTH + rightPadding
    }

    private fun calculateNeededHeight(): Int {
        val topPadding = paddingTop
        val bottomPadding = paddingBottom

        return topPadding + RoundCloudsViewConstants.DEFAULT_MIN_VIEW_HEIGHT + bottomPadding
    }

    private fun drawCloud(canvas: Canvas, model: RoundCloudModel) {
        when(model.state) {
            RoundCloudState.CHECKED -> drawCheckedCloud(canvas, model)
            RoundCloudState.NOT_CHECKED -> drawNotCheckedCloud(canvas, model)
        }
    }

    private fun drawCheckedCloud(canvas: Canvas, model: RoundCloudModel) {
        val xCenter = model.getXCenterCoordinatePx(viewCenterPointPx.x.toInt())
            .toFloat()
        val yCenter = model.getYCenterCoordinatePx(viewCenterPointPx.y.toInt())
            .toFloat()
        val radius = model.radiusPx.toFloat()

        canvas.drawCircle(
            xCenter, yCenter,
            radius, checkedCloudPaint,
        )
        canvas.drawText(
            model.text, xCenter, yCenter, checkedTextPaint,
        )
    }

    private fun drawNotCheckedCloud(canvas: Canvas, model: RoundCloudModel) {
        val xCenter = model.getXCenterCoordinatePx(viewCenterPointPx.x.toInt())
            .toFloat()
        val yCenter = model.getYCenterCoordinatePx(viewCenterPointPx.y.toInt())
            .toFloat()
        val radius = model.radiusPx.toFloat()

        canvas.drawCircle(
            xCenter, yCenter,
            radius, notCheckedCloudPaint
        )
        canvas.drawText(
            model.text, xCenter, yCenter, notCheckedTextPaint,
        )
    }
}