package com.obelieve.watermarkview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.DrawableRes
import com.obelieve.watermarkview.library.R


class WatermarkView : View {

    companion object{
        val TAG = WatermarkView::class.java.simpleName
    }

    private val paint = Paint()
    private var degrees = 30F
    private var sinValue: Double = 0.5
    private var imageRes: Int = 0


    constructor(context: Context) : this(context , null)

    constructor(context: Context , attributeSet: AttributeSet?) : this(context , attributeSet , 0)

    constructor(context: Context , attributeSet: AttributeSet? , defStyle : Int) : super(context , attributeSet , defStyle){
        initView(attributeSet)
    }

    private fun initView(attrs: AttributeSet?) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.WatermarkView)
        degrees = typedArray.getFloat(R.styleable.WatermarkView_imageRotate, 30F)
        sinValue = Math.sin(Math.toRadians(degrees.toDouble()))
        if (sinValue == 0.0) {
            sinValue = 0.5
        }
        imageRes = typedArray.getResourceId(R.styleable.WatermarkView_imageResource, 0)
        setMarkResource(imageRes)
        typedArray.recycle()
    }


    fun setMarkRotate(degrees: Float) {
        this.degrees = degrees
        sinValue = Math.sin(Math.toRadians(degrees.toDouble()))
        if (sinValue == 0.0) {
            sinValue = 0.5
        }
        invalidate()
    }

    fun setMarkResource(@DrawableRes mark: Int) {
        val bitmap: Bitmap? = BitmapFactory.decodeResource(resources, mark)
        if(bitmap!=null){
            val shader: Shader = BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
            paint.shader = shader
            invalidate()
        }else{
            Log.i(TAG,"Mark Resource bitmap is null.")
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        val radius = Math.sqrt(Math.pow(width.toDouble(),2.0)+Math.pow(height.toDouble(),2.0))*0.5f
        canvas?.rotate(degrees,0.5F*width.toFloat(),0.5F*height.toFloat())
        canvas?.drawCircle(0.5F*width.toFloat(),0.5F*height.toFloat(),radius.toFloat(), paint)
        canvas?.restore()
    }
}